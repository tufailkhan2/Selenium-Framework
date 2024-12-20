package Pages;

import BasePackage.baseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.commonFunctions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task1SauceDemo extends baseClass {

    commonFunctions commonFtns = new commonFunctions();

    public Task1SauceDemo(WebDriver driver) {
        baseClass.driver = driver;
    }

    String userNameFieldId = "user-name";
    String passwordFieldId = "password";
    String loginBtnId = "login-button";
    String pricesTextClassLocator = "inventory_item_price";
    String addToCartBtnXpath = ".//button[normalize-space() = 'Add to cart']";
    String productsHeadingTextClass = "title";
    String loginErrorMessage = "//h3[contains(text(), 'Epic sadface:')]";
    String cartBtnLink = "shopping_cart_link";
    String hamburgerMenuBtnId = "react-burger-menu-btn";
    String homePageLinkId = "inventory_sidebar_link";
    String aboutPageLinkId = "about_sidebar_link";
    String companyLinkXpath = "//span[text() = 'Company']";
    String contactUsLinkXpath = "//span[text() = 'Contact us']";
    String contactSalesLink = "//h3[text() = 'Contact sales']";
    String contactUsEmailFieldId = "Email";
    String contactUsCompanyFieldId = "Company";
    String contactUsCommentsFieldId = "Sales_Contact_Comments__c";
    String facebookLinkXpath = "//a[contains(@href, 'facebook')]//span//img";
    String LoginErrorMessageText = "Epic sadface: Username and password do not match any user in this service";
    String errorMessageRecieved = "";
    String validUserNameTextValue = "standard_user";
    String validPasswordTextValue = "secret_sauce";
    String inValidPasswordTextValue = "secret_sauce11";
    String contactUsEmailTextValue = "test123@gmail.com";
    String contactUsCompanyTextValue = "Test Company 123";
    String contactUsCommentsTextValue = "Test Comment 123 !@#";


    public void LoginWithInvalidPassword(){
        commonFtns.addText("id", userNameFieldId, validUserNameTextValue);
        commonFtns.addText("id", passwordFieldId, inValidPasswordTextValue);
        commonFtns.clickOnElement("id", loginBtnId);
        errorMessageRecieved = commonFtns.getText("xpath",loginErrorMessage);
        Assert.assertEquals(LoginErrorMessageText, errorMessageRecieved);
//        System.out.println("The assertion is passed.");

    }

    public void LoginWithValidCreds(){
        commonFtns.createLocator("id", userNameFieldId).clear();
        commonFtns.addText("id" ,userNameFieldId, validUserNameTextValue);
        commonFtns.createLocator("id", passwordFieldId).clear();
        commonFtns.addText("id", passwordFieldId, validPasswordTextValue);
        commonFtns.clickOnElement("id", loginBtnId);
        String expectedTitleText = "Products";
        String receivedTitleText = commonFtns.getText("class", productsHeadingTextClass);
        Assert.assertEquals(expectedTitleText, receivedTitleText);
//        System.out.println("Landed on to the Dashboard");
    }


    public void sortTheItemsFromLowToHigh(){
        WebElement dropdown = driver.findElement(By.className("product_sort_container"));
        commonFtns.selectDropdownValue(dropdown, "Price (low to high)");
        commonFtns.addFirstTwoItemsToCart(pricesTextClassLocator, addToCartBtnXpath);
        commonFtns.clickOnElement("class", cartBtnLink);


        // Getting the items added into cart
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        int itemCount = cartItems.size();

        // Verify the number of items in the cart equals the count of items displayed
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        int displayedItemCount = Integer.parseInt(cartBadge.getText());

        Assert.assertEquals(itemCount, displayedItemCount, "Item count mismatch!");
//        System.out.println("Total items: " + itemCount);
    }

    public void selectmenu() {


        try {
            commonFtns.clickOnElement("id", hamburgerMenuBtnId);
//            Thread.sleep(5000);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void selectAbout() {
        commonFtns.I_AM_WAIT("id", aboutPageLinkId);

        try {
            commonFtns. clickOnElement("id", aboutPageLinkId);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//            Thread.sleep(5000);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void contactus() {
//        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//        JavascriptExecutor js = new JavascriptExecutor();
        commonFtns.scrollDown("xpath", contactUsLinkXpath);
        System.out.println("Waiting for element: " + contactUsLinkXpath);
        commonFtns.I_AM_WAIT("xpath", contactUsLinkXpath);
        System.out.println("Element found: " + contactUsLinkXpath);
        commonFtns.clickOnElement("xpath", contactUsLinkXpath);
        driver.get("https://saucelabs.com/contact-us");
        commonFtns.waitForPageLoad();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//        driver.get("https://saucelabs.com/contact-us");
        commonFtns.I_AM_WAIT("id", contactUsEmailFieldId);
        commonFtns.addText("id", contactUsEmailFieldId, contactUsEmailTextValue);
        commonFtns.addText("id", contactUsCompanyFieldId, contactUsCompanyTextValue);
        WebElement dropdown = driver.findElement(By.id("Solution_Interest__c"));
        commonFtns.selectDropdownValue(dropdown, "Mobile Application Testing");
        commonFtns.addText("id", contactUsCommentsFieldId, contactUsCommentsTextValue);
        commonFtns.goBack();
        commonFtns.scrollDown("xpath", facebookLinkXpath);
        commonFtns.clickOnElement("xpath", facebookLinkXpath);
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://www.facebook.com/saucelabs/", "The URL does not matches.");

    }


    public void contactFormSubmission() {
        commonFtns.clickOnElement("id", hamburgerMenuBtnId);
        commonFtns.I_AM_WAIT("id", homePageLinkId);
        commonFtns.clickOnElement("id", homePageLinkId);
        commonFtns.waitForPageLoad();
        commonFtns.I_AM_WAIT("id", hamburgerMenuBtnId);
        commonFtns.clickOnElement("id", hamburgerMenuBtnId);

        commonFtns.I_AM_WAIT("id", aboutPageLinkId);
//        driver.get("https://saucelabs.com/");
//        driver.get("https://saucelabs.com/contact-us");
//        Thread.sleep(3000);
        commonFtns.clickOnElement("id", aboutPageLinkId);
        commonFtns.waitForPageLoad();
        System.out.println(driver.getPageSource());
        commonFtns.scrollDown("xpath", contactUsLinkXpath);
        System.out.println("Waiting for element: " + contactUsLinkXpath);
        commonFtns.I_AM_WAIT("xpath", contactUsLinkXpath);
        System.out.println("Element found: " + contactUsLinkXpath);
        commonFtns.clickOnElement("xpath", contactUsLinkXpath);
        commonFtns.waitForPageLoad();
        commonFtns.I_AM_WAIT("id", contactUsEmailFieldId);
        commonFtns.addText("id", contactUsEmailFieldId, contactUsEmailTextValue);
        commonFtns.addText("id", contactUsCompanyFieldId, contactUsCompanyTextValue);
        WebElement dropdown = driver.findElement(By.id("Solution_Interest__c"));
        commonFtns.selectDropdownValue(dropdown, "Mobile Application Testing");
        commonFtns.addText("id", contactUsCommentsFieldId, contactUsCommentsTextValue);
        commonFtns.goBack();
        commonFtns.scrollDown("xpath", facebookLinkXpath);
        commonFtns.clickOnElement("xpath", facebookLinkXpath);
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "https://www.facebook.com/saucelabs/", "The URL does not matches.");

    }



}
