package qumu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qumu.baseclass.BasePage;
import qumu.utils.Log;

public class InventoryPage extends BasePage {

    public InventoryPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    public void addItemToCart(String itemName) {
        Log.logger.info("Adding item to cart: " + itemName);
        String xpath =
                "//div[@class='inventory_item_name ' and text()='"
                + itemName +
                "']/ancestor::div[@class='inventory_item']//button";
        WebElement addButton = getDriver().findElement(By.xpath(xpath));
        waitForElement(addButton);
        addButton.click();
    }

    public String getCartBadgeCount() {
        Log.logger.info("Fetching cart item count");
        waitForElement(cartBadge);
        return cartBadge.getText();
    }

    public void clickCartIcon() {
        Log.logger.info("Opening shopping cart");
        click(cartIcon);
    }
}
