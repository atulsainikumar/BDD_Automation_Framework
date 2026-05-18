package qumu;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hook extends BasePage {

    BrowserSetup browsersetup = new BrowserSetup();

    
    @Before("@UI")
    public void initializeUITest() {
    	
    	Log.logger.info("UI Test Started");

        browsersetup.selectBrowser();
        
        Log.logger.info("Browser launched successfully");

        driver.manage().deleteAllCookies();

        new iniClass();
    }
    
    @Before("@API")
    public void initializeAPITest() {
    	
    	Log.logger.info("API Test Started");

        new iniClass();

        System.out.println("API Test Started");
    }

    @After("@UI")
    public void tearDownUITest(Scenario scenario) {
    	
    	Log.logger.info("Closing browser");

        if (scenario.isFailed()) {

            byte[] screenshot =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.BYTES);

            scenario.attach(
                    screenshot,
                    "image/png",
                    scenario.getName());
        }

        driver.quit();
        Log.logger.info("UI Test Completed");
    }


    @After("@API")
    public void tearDownAPITest() {

    	Log.logger.info("API Test Completed");
        System.out.println("API Test Completed");
    }
}