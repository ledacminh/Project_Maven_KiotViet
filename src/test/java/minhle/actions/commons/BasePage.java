package minhle.actions.commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    JavascriptExecutor javascriptExecutor;
    Actions actions;
    WebDriverWait explicit;
    public By getXpath(String locator) {
        return By.xpath(locator);
    }

    public By getDynamicXpath(String locator, String... params) {
       return By.xpath(String.format(locator, (Object[]) params));
    }

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getXpath(locator));
    }

    public List<WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(getXpath(locator));
    }

    public List<WebElement> getElements(WebDriver driver, String locator, String... params) {
        return driver.findElements(getDynamicXpath(locator, params));
    }
    public void clickToElement(WebDriver driver, String locator) {
        waitForElementIsVisible(driver, locator);
        waitForElementClickable(driver, locator);
        hoverToElement(driver, locator);
        getElement(driver, locator).click();

    }

    public void waitForElementIsVisible(WebDriver driver, String locator) {
        explicit = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        explicit.until(ExpectedConditions.visibilityOfElementLocated(getXpath(locator)));
    }

    public void waitForElementIsVisible(WebDriver driver, String locator, String... params) {
        explicit = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        explicit.until(ExpectedConditions.visibilityOfElementLocated(getDynamicXpath(locator, params)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        waitForElementIsVisible(driver, locator);
        explicit = new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        explicit.until(ExpectedConditions.elementToBeClickable(getXpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... params) {
        waitForElementIsVisible(driver, locator, params);
        explicit = new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        explicit.until(ExpectedConditions.elementToBeClickable(getDynamicXpath(locator, params)));
    }

    public void enterTextToElement(WebDriver driver, String locator, String value) {
        waitForElementIsVisible(driver, locator);
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(value);
    }

    public void enterTextToElement(WebDriver driver, String locator, String value, String... params) {
        WebElement element = getDynamicElement(driver, locator, params);
        element.clear();
        element.sendKeys(value);
    }

    public void enterTextToElementUsingActions(WebDriver driver, String locator, String value) {
        actions = new Actions(driver);
        waitForElementIsVisible(driver, locator);
        actions.sendKeys(getElement(driver, locator), value).perform();
    }

    public void enterTextToElementUsingActions(WebDriver driver, String locator, String value, String... params) {
        actions = new Actions(driver);
        waitForElementIsVisible(driver, locator, params);
        actions.sendKeys(getDynamicElement(driver, locator, params), value).perform();
    }
    public String getTextElement(WebDriver driver, String locator){
        waitForElementIsVisible(driver, locator);
        return getElement(driver, locator).getText().trim();
    }

    public String getTextElement(WebDriver driver, String locator, String... params) {
        WebElement element = getDynamicElement(driver, locator, params);
        return element.getText().trim();
    }
    public void hoverToElement(WebDriver driver, String locator){
        actions = new Actions(driver);
        waitForElementIsVisible(driver, locator);
        actions.clickAndHold(getElement(driver, locator)).perform();
    }

    public void hoverToElement(WebDriver driver, String locator, String... params) {
        actions = new Actions(driver);
        waitForElementIsVisible(driver,locator,params);
        actions.clickAndHold(getDynamicElement(driver,locator,params)).perform();
    }
    public void SleepInSecond(int second)   {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickToElementByJS(WebDriver driver, String locator) {
        waitForElementClickable(driver, locator);
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click;", getElement(driver, locator));
    }
    public String getDynamicLocator(String locator, String... params) {
        return String.format(locator, (Object[]) params);
    }

    public WebElement getDynamicElement(WebDriver driver, String locator, String... params) {
        locator = getDynamicLocator(locator,params);
        return getElement(driver,locator);
    }

    public boolean isDiplayElement(WebDriver driver, String locator ) {
        waitForElementIsVisible(driver,locator);
        return getElement(driver,locator).isDisplayed();
    }

    public boolean isDiplayElement(WebDriver driver, String locator, String... params) {
        waitForElementIsVisible(driver, locator, params);
        return getDynamicElement(driver, locator, params).isDisplayed();
    }

    public void getPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    public String getTextAlert(WebDriver driver) {
        return waitForAlertPresence(driver).getText().trim();
    }

    public void enterTextToAlert(WebDriver driver, String value) {
        waitForAlertPresence(driver).sendKeys(value);
    }

    public void switchWindowByID(WebDriver driver, String expectedID) {
        Set<String> allTabIDs = driver.getWindowHandles();
        for (String id: allTabIDs) {
            if(id.equals(expectedID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allTabIDs = driver.getWindowHandles();
        for (String id: allTabIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle().trim();
            if(actualTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public boolean closeAllWindowsWithoutParent(WebDriver driver, String parentId) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow: allWindows) {
            if(!runWindow.equals(parentId)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }

        driver.switchTo().window(parentId);
        return driver.getWindowHandles().size() == 1;
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText) {
        Select select = new Select(getElement(driver, locator));
        select.selectByVisibleText(itemText);
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText, String... params) {
        Select select = new Select(getDynamicElement(driver, locator, params));
        select.selectByVisibleText(itemText);
    }

    public String getFirstSelectedTextItem(WebDriver driver, String locator) {
        Select select = new Select(getElement(driver, locator));
        return select.getFirstSelectedOption().getText();

    }

    public String getFirstSelectedTextItem(WebDriver driver, String locator, String... params) {
        Select select = new Select(getDynamicElement(driver, locator, params));
        return select.getFirstSelectedOption().getText();

    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        Select select = new Select(getElement(driver, locator));
        return select.isMultiple();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator, String... params) {
        Select select = new Select(getDynamicElement(driver, locator, params));
        return select.isMultiple();
    }

    public String getElementAttributeValue(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementAttributeValue(WebDriver driver, String locator, String attributeName, String... params) {
        return getDynamicElement(driver, locator, params).getAttribute(attributeName);
    }

    public int getListElementSize(WebDriver driver, String locator) {
        return getElements(driver, locator).size();
    }

    public int getListElementSize(WebDriver driver, String locator, String... params) {
        return getElements(driver, locator, params).size();
    }

    public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        if(!element.isSelected()) {
            element.click();
        }
    }

    public void checkToCheckboxOrRadio(WebDriver driver, String locator, String... params) {
        WebElement element = getDynamicElement(driver, locator, params);
        if(!element.isSelected()) {
            element.click();
        }
    }

    public void unCheckToCheckbox(WebDriver driver, String locator) {
        WebElement element = getElement(driver, locator);
        if(element.isSelected()) {
            element.click();
        }
    }

    public void unCheckToCheckbox(WebDriver driver, String locator, String... params) {
        WebElement element = getDynamicElement(driver, locator, params);
        if(element.isSelected()) {
            element.click();
        }
    }

    public void setImplicitTime(WebDriver driver, long timeInSecond) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSecond));
    }

    public void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void rightClickOnElement(WebDriver driver, String locator) {
        actions = new Actions(driver);
        waitForElementClickable(driver, locator);
        actions.contextClick(getElement(driver, locator)).perform();
    }

    public void rightClickOnElement(WebDriver driver, String locator, String... params) {
        actions = new Actions(driver);
        waitForElementClickable(driver, locator, params);
        actions.contextClick(getDynamicElement(driver, locator, params)).perform();
    }

    public void doubleClickOnElement(WebDriver driver, String locator) {
        actions = new Actions(driver);
        waitForElementClickable(driver, locator);
        actions.doubleClick(getElement(driver, locator)).perform();
    }

    public void doubleClickOnElement(WebDriver driver, String locator, String... params) {
        actions = new Actions(driver);
        waitForElementClickable(driver, locator, params);
        actions.doubleClick(getDynamicElement(driver, locator, params)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
        waitForElementIsVisible(driver, sourceLocator);
        waitForElementIsVisible(driver, targetLocator);
        actions = new Actions(driver);
        actions.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys keys) {
        waitForElementIsVisible(driver, locator);
        actions = new Actions(driver);
        actions.sendKeys(getElement(driver, locator), keys).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys keys, String... params) {
        waitForElementIsVisible(driver, locator, params);
        actions = new Actions(driver);
        actions.sendKeys(getDynamicElement(driver, locator, params), keys).perform();
    }

    public boolean isDiplayElements(WebDriver driver, String locator ) {
        waitForElementIsVisible(driver,locator);
        return getElement(driver,locator).isDisplayed();
    }

    public boolean isDiplayElements(WebDriver driver, String locator, String... params ) {
        waitForElementIsVisible(driver,locator, params);
        return getDynamicElement(driver,locator, params).isDisplayed();
    }
}
