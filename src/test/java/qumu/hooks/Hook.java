package qumu.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import qumu.baseclass.BasePage;
import qumu.baseclass.PageObjectFactory;
import qumu.utils.BrowserSetup;
import qumu.utils.Log;

public class Hook extends BasePage {

    BrowserSetup browserSetup = new BrowserSetup();

    @Before("@UI")
    public void initializeUITest() {
        Log.logger.info("UI test started");
        browserSetup.selectBrowser();
        Log.logger.info("Browser launched successfully");
        getDriver().manage().deleteAllCookies();
        new PageObjectFactory();
    }

    @Before("@API")
    public void initializeAPITest() {
        Log.logger.info("API test started");
        new PageObjectFactory();
    }

    @After("@UI")
    public void tearDownUITest(Scenario scenario) {
        Log.logger.info("Closing browser");
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        getDriver().quit();
        Log.logger.info("UI test completed");
    }

    @After("@API")
    public void tearDownAPITest() {
        Log.logger.info("API test completed");
    }
}
