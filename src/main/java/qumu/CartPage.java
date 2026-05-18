package qumu;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {

    public CartPage() {

        PageFactory.initElements(driver, this);

    }

    // Locators for cart page

    @FindBy(className = "cart_contents_container")
    WebElement cartContainer;

    @FindBy(className = "cart_quantity")
    List<WebElement> quantityList;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    // Validation Methods
    
    public boolean isCartPageDisplayed() {

        waitForElement(cartContainer);

        return cartContainer.isDisplayed();

    }

    // Verify Quantity = 1

    public boolean verifyAllItemQuantityIsOne() {
    	
    	Log.logger.info("Verifying item quantities");

        for (WebElement qty : quantityList) {

            if (!qty.getText().equals("1")) {

                return false;

            }
        }

        return true;

    }

    // Remove Item Dynamically

    public void removeItem(String itemName) {
    	Log.logger.info("Removing item: " + itemName);

        String xpath =
                "//div[text()='" + itemName + "']" +
                "/ancestor::div[@class='cart_item']" +
                "//button[contains(text(),'Remove')]";

        WebElement removeButton =
                driver.findElement(By.xpath(xpath));

        click(removeButton);

    }

    // Click Checkout

    public void clickCheckoutButton() {
    	Log.logger.info("Proceeding to Checkout");

        click(checkoutButton);

    }

}