package commons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.Duration;
import java.util.Random;

public class BaseTest {
    protected WebDriver driver;
    protected final Logger log;

    public WebDriver getDriver() {
        return driver;
    }

    public BaseTest() {
        log = LogManager.getLogger(getClass());
    }


    protected int generateRandomNumb() {
        return new Random().nextInt(9999);
    }

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("browser is invalid");
        }
        driver.get("https://id-uat.ipas.com.vn/login?&redirect-app=dsb-web");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("browser is invalid");
        }
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }


    protected void assertTrue(boolean condition) {
        Assert.assertTrue(verifyTrue(condition));
    }

    protected boolean verifyTrue(boolean condition) {
        boolean status = true;
        try {
            Assert.assertTrue(condition);
            log.info("-----------------------Passed------------------------");
        } catch (Throwable e) {
            status = false;
            log.info("-----------------------Failed------------------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean status = true;
        try {
            Assert.assertFalse(condition);
            log.info("-----------------------Passed------------------------");
        } catch (Throwable e) {
            status = false;
            log.info("-----------------------Failed------------------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("-----------------------Passed------------------------");
        } catch (Throwable e) {
            status = false;
            log.info("-----------------------Failed------------------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    @BeforeSuite
    private void deleteReportFolder() {
        deleteAllFileInFolder("htmlReportNG");
    }

    private void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }


}
