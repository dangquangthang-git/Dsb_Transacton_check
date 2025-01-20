package com.dsb.transaction;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.CustomerListPageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGenerator;
import pageObjects.TransactionListPageObject;
import reportConfigs.ExtentManager;

import java.io.File;
import java.time.Duration;

import java.lang.reflect.Method;


public class Make_Transaction_Request extends BaseTest {
    private WebDriver driver;
    private LoginPageObject loginPage;
    private CustomerListPageObject customerList;
    private TransactionListPageObject transactionList;
    String fileName, uploadFilePath, userName, password, browserName, expectedItemBank, customerId, amount, accountNo;
    private String projectPath;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        projectPath = System.getProperty("user.dir");
        fileName = "aaa.jpg";
        uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator + fileName;
        userName = "ipa\\a.ae";
        password = "Dsb@2024";
        customerId = "0002015332";
        accountNo = "0002014733";
        amount = "2,222,222,222";
        expectedItemBank = "NH TMCP CONG THUONG VIET NAM (VIETINBANK)";

    }

    @Test
    public void Step_01_Login(Method method) {
        ExtentManager.startTest(method.getName() + "-" + browserName, "TC_01_NewCustomer");
        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP01: Open Register Page");
        loginPage = PageGenerator.getLoginPage(driver);
        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP01: Open Register Page");
        loginPage.enterToUserName(userName);
        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP01: Open Register Page");
        loginPage.enterToPassword(password);
    }

    @Test(dependsOnMethods = "Step_01_Login")
    public void Step_02_Make_Transaction_Request(Method method) {
        customerList = PageGenerator.getLoginPage(driver).clickToLoginButton();
        customerList.clickToT0CustomerList();
        customerList.enterToCustomerIdTextBox(customerId);
        customerList.clickToSearch();
        customerList.moveMouseToTransactionRequestPopUp();
        customerList.enterToAccountNo(accountNo);
        customerList.enterToAmount(amount);
        customerList.inputBankSelected(expectedItemBank);
        customerList.uploadFile(uploadFilePath);
        customerList.clickToMakeRequestButton();
    }

    @Test(dependsOnMethods = "Step_02_Make_Transaction_Request")
    public void Step_03_Transaction_List_Check(Method method) {
        transactionList = PageGenerator.getCustomerPage(driver).closeCompletedPopUp();
        transactionList.enterToAccountNoTextBox(accountNo);
        //check assert qua outerText
        transactionList.amountVerify(amount);

    }


}
