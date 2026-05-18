package qumu;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class BrowserSetup extends BasePage {

    public static String browser = null;
//    private static final String CHROME_WIN = "src\\test\\java\\BrowserDirectory\\chromedriver.exe";
//    private static final String EDGE = "src\\test\\java\\BrowserDirectory\\MicrosoftWebDriver.exe";
//    private static final String FIREFOX_WIN = "src\\test\\java\\BrowserDirectory\\geckodriver.exe";
//    private static final String CHROME_MAC = "src/test/java/BrowserDirectory/chromedriver-Mac";


    /**
     * Browser property location /src/test/java/TestData/TestData.properties
     */


    /**
     * Function for multi browser
     */
    public void selectBrowser() {
    	browser = LoadProp.getproperty("Browser");

        if (browser.equalsIgnoreCase("Chrome")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--remote-allow-origins=*");

            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

            // Disable Chrome Password Manager Popup

            options.addArguments("--disable-save-password-bubble");

            options.addArguments("--disable-notifications");

            options.addArguments("--disable-popup-blocking");

            options.addArguments("--incognito");

            Map<String, Object> prefs = new HashMap<>();

            prefs.put("credentials_enable_service", false);

            prefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);

        }

        else if (browser.equalsIgnoreCase("chromeHeadless")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");

            driver = new ChromeDriver(options);

        }

        else if (browser.equalsIgnoreCase("Firefox")) {

            WebDriverManager.firefoxdriver().setup();

            driver = new FirefoxDriver();

        }

        else if (browser.equalsIgnoreCase("Edge")) {

            WebDriverManager.edgedriver().setup();

            driver = new EdgeDriver();

        }

        else if (browser.equalsIgnoreCase("api")) {

            // No browser required for API execution

        }

        else {

            Assert.fail(MessageFormat.format("Invalid Browser Name: {0}", browser));

        }

        // Common Browser Settings

        if (!browser.equalsIgnoreCase("api")) {

            driver.manage().window().maximize();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        } 
    }
}
