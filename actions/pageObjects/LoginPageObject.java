package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToUserName(String userName) {
        waitForElementClickable(driver, LoginPageUI.USERNAME_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, userName);
    }

    public void enterToPassword(String password) {
        waitForElementClickable(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public CustomerListPageObject clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getCustomerPage(driver);
    }

}
