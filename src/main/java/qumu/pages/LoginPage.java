package qumu.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qumu.baseclass.BasePage;
import qumu.utils.Log;

public class LoginPage extends BasePage {

    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(id = "user-name")
    private WebElement usernameTextbox;

    @FindBy(id = "password")
    private WebElement passwordTextbox;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    private void enterUsername(String username) {
        Log.logger.info("Entering username");
        type(usernameTextbox, username);
    }

    private void enterPassword(String password) {
        Log.logger.info("Entering password");
        type(passwordTextbox, password);
    }

    private void clickLoginButton() {
        Log.logger.info("Clicking login button");
        click(loginButton);
    }

    public void loginToApplication(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        Log.logger.info("Login completed successfully");
    }
}
