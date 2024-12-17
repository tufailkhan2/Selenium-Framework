package utilities;

import BasePackage.baseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class commonFunctions extends baseClass {

    // This function requires two parameters
    // locatorType such as id, xpath etc and the actual locator where the element is.
    public WebElement createLocator(String locatorType, String locatorValue) {
        By locator = null;

        switch (locatorType.toLowerCase()) {
            case "id":
                locator = By.id(locatorValue);
                break;
            case "name":
                locator = By.name(locatorValue);
                break;
            case "xpath":
                locator = By.xpath(locatorValue);
                break;
            case "css":
                locator = By.cssSelector(locatorValue);
                break;
            case "class":
                locator = By.className(locatorValue);
                break;
            case "tag":
                locator = By.tagName(locatorValue);
                break;
            case "linktext":
                locator = By.linkText(locatorValue);
                break;
            case "partiallinktext":
                locator = By.partialLinkText(locatorValue);
                break;
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }

        return driver.findElement(locator);
    }


    //The below function will get webElement and click on that.
    public void clickOnElement(String locatorType, String locatorValue){
        createLocator(locatorType, locatorValue).click();
    }

    // This function will require two arguments, text and elemenet.
    public void addText(String locatorType, String locatorValue, String text){
        createLocator(locatorType, locatorValue).sendKeys(text);
    }

    // This function will return a String of the text it is getting
    public String getText(String locatorType, String locatorValue){
        return createLocator(locatorType, locatorValue).getText();
    }

    // Method to get all dropdown values as a list of Strings
    public List<String> getDropdownValues(WebElement dropdownElement) {
        Select dropdown = new Select(dropdownElement);
        List<WebElement> options = dropdown.getOptions();
        List<String> dropdownValues = new ArrayList<>();

        for (WebElement option : options) {
            dropdownValues.add(option.getText());
        }
        return dropdownValues; // Return the list of dropdown values
    }

    // Method to select an item from the dropdown
    public void selectDropdownValue(WebElement dropdownElement, String valueToSelect) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(valueToSelect); // Select by visible text
    }

    public void goBack(){
        driver.navigate().back();
    }

    public void I_AM_WAIT(String locatorType, String locatorValue){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30) );
        WebElement locator = this.createLocator(locatorType,locatorValue);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void scrollDown(String locatorType, String locatorValue){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement locator = this.createLocator(locatorType,locatorValue);
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        js.executeScript("arguments[0].scrollIntoView(true);", locator);
    }

    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        try {
            // Wait for the document ready state to be 'complete'
            wait.until(webDriver ->
                    ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
            );
            System.out.println("Page fully loaded.");
        } catch (TimeoutException e) {
            System.out.println("Page did not fully load within the timeout.");
            throw e; // Rethrow the exception to fail the test if needed
        }
    }

    public static String getCurrentDateTime(){
        DateFormat customFormat = new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss");
        Date currentDate = new Date();

        return customFormat.format(currentDate);
    }

    public void addFirstTwoItemsToCart(String priceItemLocator, String addToCartBtnLocator) {
        // Locate all the inventory items (assuming each item has a class 'inventory_item')
        List<WebElement> inventoryItems = driver.findElements(By.xpath(priceItemLocator));

        // Click the "Add to Cart" button for the first item
        WebElement firstItemAddToCartButton = driver.findElement(By.xpath(addToCartBtnLocator));
        firstItemAddToCartButton.click();

        // Click the "Add to Cart" button for the second item
        WebElement secondItemAddToCartButton = driver.findElement(By.xpath(addToCartBtnLocator));
        secondItemAddToCartButton.click();
    }

    // Utility to take a screenshot
    public String takeScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(src, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath; // Return the path to attach to the report
    }

}
