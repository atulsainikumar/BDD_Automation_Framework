package qumu;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage {
	
	 public InventoryPage() {

	        PageFactory.initElements(driver, this);

	    }

	    @FindBy(className = "shopping_cart_badge")
	    WebElement cartBadge;

	    @FindBy(className = "shopping_cart_link")
	    WebElement cartIcon;

	    @FindBy(className = "inventory_item")
	    List<WebElement> inventoryItems;

	    // Add Item To Cart Dynamically

	    public void addItemToCart(String itemName) {
	    	
	    	Log.logger.info("Adding item to cart: " + itemName);

	        String xpath =
	                "//div[@class='inventory_item_name ' and text()='"
	                + itemName +
	                "']/ancestor::div[@class='inventory_item']" +
	                "//button";

	        WebElement addButton =
	                driver.findElement(By.xpath(xpath));

	        waitForElement(addButton);

	        addButton.click();

	    }

	    // Cart Badge Count

	    public String getCartBadgeCount() {
	    	
	    	Log.logger.info("Fetching cart item count");

	        waitForElement(cartBadge);

	        return cartBadge.getText();

	    }

	    // Click Cart Icon

	    public void clickCartIcon() {
	    	
	    	Log.logger.info("Opening shopping cart");

	        click(cartIcon);

	    }

}
