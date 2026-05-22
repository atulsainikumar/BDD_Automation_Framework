package qumu.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qumu.baseclass.BasePage;
import qumu.utils.Log;

public class CheckoutPage extends BasePage {

    public CheckoutPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(id = "first-name")
    private WebElement firstNameTextbox;

    @FindBy(id = "last-name")
    private WebElement lastNameTextbox;

    @FindBy(id = "postal-code")
    private WebElement zipCodeTextbox;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public void enterFirstName(String firstName) {
        type(firstNameTextbox, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameTextbox, lastName);
    }

    public void enterZipCode(String zipCode) {
        type(zipCodeTextbox, zipCode);
    }

    public void clickContinueButton() {
        Log.logger.info("Clicking continue button");
        click(continueButton);
    }
}
