package qumu;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

	public LoginPage() {

        PageFactory.initElements(driver, this);

    }

    // Locators for login page

    @FindBy(id = "user-name")
    WebElement usernameTextbox;

    @FindBy(id = "password")
    WebElement passwordTextbox;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;

    // Actions/methods

    public void enterUsername(String username) {
    	
    	Log.logger.info("Entering Username");

        type(usernameTextbox, username);

    }

    public void enterPassword(String password) {
    	
    	Log.logger.info("Entering Password");

        type(passwordTextbox, password);

    }

    public void clickLoginButton() {
    	
    	Log.logger.info("Clicking Login Button");

        click(loginButton);
        
        Log.logger.info("Login completed successfully");

    }

    // Reusable Login Method

    public void loginToApplication(String username, String password) {

        enterUsername(username);

        enterPassword(password);

        clickLoginButton();
        
        Log.logger.info("Login completed successfully");

    }

    // Validation Methods

    public String getErrorMessage() {

        waitForElement(errorMessage);

        return errorMessage.getText();

    }

    public boolean isLoginButtonDisplayed() {

        waitForElement(loginButton);

        return loginButton.isDisplayed();

    }
	}
