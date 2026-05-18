package qumu;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public static WebDriver driver;

    public static WebDriverWait wait;

    // Wait Method

    public void waitForElement(WebElement ele) {

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    // Type Method

    public void type(WebElement ele, String value) {

        waitForElement(ele);

        ele.clear();

        ele.sendKeys(value);
    }

    // Click Method

    public void click(WebElement ele) {

        waitForElement(ele);

        ele.click();
    }
    // Dropdown Method

    public void drop_Down_select_ele_by_text(WebElement ele, String text) {

        Select sc = new Select(ele);

        sc.selectByVisibleText(text);
    }

    // Mouse Hover

    public void mouser_over(WebElement ele) {

        Actions actions = new Actions(driver);

        actions.moveToElement(ele).perform();
    }

    // Double Click

    public void double_click() {

        Actions actions = new Actions(driver);

        actions.doubleClick().perform();
    }

    // Frame By Name

    public void freams_name(String item) {

        driver.switchTo().frame(item);
    }

    // Frame By Index

    public void freams_index(int item) {

        driver.switchTo().frame(item);
    }
}