package qumu.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qumu.baseclass.BasePage;
import qumu.utils.Log;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(className = "summary_subtotal_label")
    private WebElement itemTotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    public double calculateItemTotal() {
        Log.logger.info("Calculating item total");
        double total = 0;
        for (WebElement item : itemPrices) {
            String price = item.getText().replace("$", "").trim();
            Log.logger.info("Item price found: $" + price);
            total += Double.parseDouble(price);
        }
        Log.logger.info("Calculated item total: $" + total);
        return total;
    }

    public double getDisplayedItemTotal() {
        String subtotal = itemTotalLabel.getText().replace("Item total: $", "").trim();
        Log.logger.info("Displayed item total: $" + subtotal);
        return Double.parseDouble(subtotal);
    }

    public double getDisplayedTax() {
        String tax = taxLabel.getText().replace("Tax: $", "").trim();
        Log.logger.info("Displayed tax amount: $" + tax);
        return Double.parseDouble(tax);
    }
}
