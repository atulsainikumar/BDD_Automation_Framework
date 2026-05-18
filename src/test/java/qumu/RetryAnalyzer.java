package qumu;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;

    private static final int MAX_RETRY = 2;

    @Override
    public boolean retry(ITestResult result) {

        if (count < MAX_RETRY) {

            count++;

            Log.logger.info(
                    "Retrying Test: "
                            + result.getName()
                            + " | Retry Count: "
                            + count);

            return true;
        }

        return false;
    }
}