package qumu;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {

    public CheckoutPage() {

        PageFactory.initElements(driver, this);

    }

    // Locators for checkout page

    @FindBy(id = "first-name")
    WebElement firstNameTextbox;

    @FindBy(id = "last-name")
    WebElement lastNameTextbox;

    @FindBy(id = "postal-code")
    WebElement zipCodeTextbox;

    @FindBy(id = "continue")
    WebElement continueButton;

    // Actions/methods

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
    	Log.logger.info("Clicking Continue Button");

        click(continueButton);

    }

    // Reusable Checkout Info

    public void enterCheckoutInformation(
            String firstName,
            String lastName,
            String zipCode) {

        enterFirstName(firstName);

        enterLastName(lastName);

        enterZipCode(zipCode);

    }

}