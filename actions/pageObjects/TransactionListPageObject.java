package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageUIs.TransactionListPageUI;

public class TransactionListPageObject extends BasePage {
    private WebDriver driver;

    public TransactionListPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToAccountNoTextBox(String accountNo) {
        waitForElementClickable(driver, TransactionListPageUI.ACCOUNTNO_TEXTBOX);
        sendkeyToElement(driver, TransactionListPageUI.ACCOUNTNO_TEXTBOX, accountNo);
    }
    public void amountVerify(String amountVerify){
        waitForElementVisible(driver,TransactionListPageUI.AMOUNT_VERIFY);
        Assert.assertEquals(getElementText(driver,TransactionListPageUI.AMOUNT_VERIFY),amountVerify);
    }

}
