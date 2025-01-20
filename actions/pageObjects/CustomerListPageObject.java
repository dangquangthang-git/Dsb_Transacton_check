package pageObjects;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pageUIs.CustomerListPageUI;

public class CustomerListPageObject extends BasePage {
    private WebDriver driver;

    public CustomerListPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToT0CustomerList() {
        waitForElementClickable(driver, CustomerListPageUI.T0_BUTTON);
        clickToElement(driver, CustomerListPageUI.T0_BUTTON);
    }

    public void enterToCustomerIdTextBox(String customerId) {
        waitForElementClickable(driver, CustomerListPageUI.CUSTOMER_TEXTBOX);
        sendkeyToElement(driver, CustomerListPageUI.CUSTOMER_TEXTBOX, customerId);
    }

    public void clickToSearch() {
        waitForElementClickable(driver, CustomerListPageUI.SEARCH_BUTTON);
        clickToElement(driver, CustomerListPageUI.SEARCH_BUTTON);
    }

    public void moveMouseToTransactionRequestPopUp() {
        waitForElementVisible(driver, CustomerListPageUI.CUSTID_INFO);
        hoverToElement(driver, CustomerListPageUI.CUSTID_INFO);
        hoverToElement(driver, CustomerListPageUI.TRANSACTION_ICON);
        hoverToElement(driver, CustomerListPageUI.TRANSACTION_BUTTON);
        clickToElement(driver, CustomerListPageUI.TRANSACTION_BUTTON);
    }

    public void enterToAccountNo(String expectedItem) {
        waitForElementClickable(driver, CustomerListPageUI.ACCOUNT_TEXTBOX);
        sendkeyToElement(driver, CustomerListPageUI.ACCOUNT_TEXTBOX, expectedItem);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER);
    }

    public void enterToAmount(String amount) {
        waitForElementClickable(driver, CustomerListPageUI.AMOUNT_TEXTBOX);
        sendkeyToElement(driver, CustomerListPageUI.AMOUNT_TEXTBOX, amount);
    }

    public void inputBankSelected(String expectedItem) {
        waitForElementClickable(driver, CustomerListPageUI.BANK_TEXTBOX);
        selectItemInCustomDropdown(driver, CustomerListPageUI.BANK_TEXTBOX, CustomerListPageUI.BANK_CHECKLIST, expectedItem);
        pressKeyToElement(driver, CustomerListPageUI.BANK_CHECKLIST, Keys.ENTER);
    }

    public void uploadFile(String uploadFilePath) {
        sendkeyToElement(driver, CustomerListPageUI.FILETOSEND, uploadFilePath);
    }

    public void clickToMakeRequestButton() {
        waitForElementClickable(driver, CustomerListPageUI.REQUEST_BUTTON);
        clickToElement(driver, CustomerListPageUI.REQUEST_BUTTON);
    }

    public TransactionListPageObject closeCompletedPopUp() {
        waitForElementVisible(driver, CustomerListPageUI.CLOSE_POPUP);
        clickToElement(driver, CustomerListPageUI.CLOSE_POPUP);
        return PageGenerator.getTransactionListPage(driver);
    }

}
