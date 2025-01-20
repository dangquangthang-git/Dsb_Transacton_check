package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    public void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentpage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitAlertPresent(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver) {
//       Chi switch ko wait
//       driver.switchTo().alert().accept();
//       wait cho xuat hien Alert roi switch vao
        waitAlertPresent(driver).accept();
    }

    public void dismissToAlert(WebDriver driver) {
        waitAlertPresent(driver).dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return waitAlertPresent(driver).getText();
    }

    public void senkeyToAlert(WebDriver driver, String keyToSend) {
        waitAlertPresent(driver).sendKeys(keyToSend);
    }

    public void switchToWindowbyID(String windowId, WebDriver driver) {
        Set<String> allId = driver.getWindowHandles();
        for (String id : allId) {
            if (!id.equals(windowId)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowsbyTitle(String expectedTitle, WebDriver driver) {
        Set<String> allId = driver.getWindowHandles();
        for (String id : allId) {
            driver.switchTo().window(id);
            String getTitle = driver.getTitle();
            if (getTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public void closeWindowsButParent(String parentId, WebDriver driver) {
        Set<String> allId = driver.getWindowHandles();
        for (String id : allId) {
            if (!id.equals(parentId)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(id);
        }
    }

    public void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String keyToSend) {
        getElement(driver, locator).sendKeys(keyToSend);
    }

    public void selectItemInDropdownList(WebDriver driver, String locator, String textItem) {
        new Select(getElement(driver, locator)).selectByVisibleText(textItem);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSecond(2);
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));
        sleepInSecond(2);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }

    public void sleepInSecond(long sleep) {
        try {
            Thread.sleep(sleep * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
        return getElement(driver, locator).getCssValue(propertyName);
    }

    public void getHexaColorFromRGBA(WebDriver driver, String locator, String rgbaValue) {
        Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(By.xpath(locator));
    }

    public int getListElementNumber(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator) {
        if (!getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    public boolean isElementEnable(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }


    public void switchToIframe(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    public void switchToDefaultPage(WebDriver driver, String locator) {
        driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }

    public void doubleClick(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }

    public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
        new Actions(driver).sendKeys(getElement(driver, locator), key).perform();
    }

    public void scrollToElement(WebDriver driver, String locator) {
        new Actions(driver).scrollToElement(getElement(driver, locator));
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public void clickToMyaccountByAction(WebDriver driver, String locator) {
        new Actions(driver).click(getElement(driver, locator)).perform();
    }

    public void releaseMouse(WebDriver driver) {
        new Actions(driver).release().perform();
    }

    public void waitForElementPresent(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    public void waitForElementSelected(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeSelected(getByXpath(locator)));
    }

    public By getByXpath(String locator) {
        return By.xpath(locator);
    }


}
