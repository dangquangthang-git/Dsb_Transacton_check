package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
    public static CustomerListPageObject getCustomerPage(WebDriver driver) {
        return new CustomerListPageObject(driver);
    }

    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static TransactionListPageObject getTransactionListPage(WebDriver driver) {
        return new TransactionListPageObject(driver);
    }
}
