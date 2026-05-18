package qumu;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage() {

        PageFactory.initElements(driver, this);

    }

    // Locators of the page

    @FindBy(className = "checkout_summary_container")
    WebElement checkoutSummaryContainer;

    @FindBy(className = "inventory_item_price")
    List<WebElement> itemPrices;

    @FindBy(className = "summary_subtotal_label")
    WebElement itemTotalLabel;

    @FindBy(className = "summary_tax_label")
    WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    WebElement totalLabel;

    @FindBy(id = "finish")
    WebElement finishButton;

    // Validation

    public boolean isCheckoutOverviewPageDisplayed() {

        waitForElement(checkoutSummaryContainer);

        return checkoutSummaryContainer.isDisplayed();

    }

    // Calculate item total

    public double calculateItemTotal() {
    	
    	Log.logger.info("Calculating Item Total");

        double total = 0;

        for (WebElement item : itemPrices) {

            String price =
                    item.getText().replace("$", "").trim();

            Log.logger.info("Item Price Found: $" + price);
            
            total += Double.parseDouble(price);

        }
        Log.logger.info("Calculated Item Total: $" + total);

        return total;

    }

    // Displayed item total

    public double getDisplayedItemTotal() {

        String subtotal =
                itemTotalLabel.getText()
                .replace("Item total: $", "")
                .trim();

        Log.logger.info("Displayed Item Total: $" + subtotal);
        
        return Double.parseDouble(subtotal);

    }

    // Displayed Tax

    public double getDisplayedTax() {

        String tax =
                taxLabel.getText()
                .replace("Tax: $", "")
                .trim();

        Log.logger.info("Displayed Tax Amount: $" + tax);
        return Double.parseDouble(tax);

    }

    // Displayed total

    public double getDisplayedTotal() {

        String total =
                totalLabel.getText()
                .replace("Total: $", "")
                .trim();

        Log.logger.info("Displayed Final Total: $" + total);
        return Double.parseDouble(total);

    }

    // Click Finish

    public void clickFinishButton() {

    	Log.logger.info("Clicking Finish Button");
        click(finishButton);

    }

}