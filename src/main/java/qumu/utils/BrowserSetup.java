package qumu.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import qumu.baseclass.BasePage;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BrowserSetup extends BasePage {

    /**
     * Reads browser type from config.properties and initialises the corresponding WebDriver.
     * Supported values: Chrome | ChromeHeadless | Firefox | Edge
     */
    public void selectBrowser() {
        String browser = LoadProp.getProperty("Browser");

        if (browser.equalsIgnoreCase("Chrome")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--incognito");
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            setDriver(new ChromeDriver(options));

        } else if (browser.equalsIgnoreCase("ChromeHeadless")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");

            setDriver(new ChromeDriver(options));

        } else if (browser.equalsIgnoreCase("Firefox")) {

            WebDriverManager.firefoxdriver().setup();
            setDriver(new FirefoxDriver());

        } else if (browser.equalsIgnoreCase("Edge")) {

            WebDriverManager.edgedriver().setup();
            setDriver(new EdgeDriver());

        } else {
            throw new IllegalArgumentException("Invalid browser name in config.properties: " + browser);
        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }
}
