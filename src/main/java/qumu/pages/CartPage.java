package qumu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qumu.baseclass.BasePage;
import qumu.utils.Log;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(className = "cart_quantity")
    private List<WebElement> quantityList;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public boolean verifyAllItemQuantityIsOne() {
        Log.logger.info("Verifying item quantities");
        for (WebElement qty : quantityList) {
            if (!qty.getText().equals("1")) {
                return false;
            }
        }
        return true;
    }

    public void removeItem(String itemName) {
        Log.logger.info("Removing item: " + itemName);
        String xpath =
                "//div[text()='" + itemName + "']" +
                "/ancestor::div[@class='cart_item']" +
                "//button[contains(text(),'Remove')]";
        WebElement removeButton = getDriver().findElement(By.xpath(xpath));
        click(removeButton);
    }

    public void clickCheckoutButton() {
        Log.logger.info("Proceeding to checkout");
        click(checkoutButton);
    }
}
