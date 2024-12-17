package Tests;

import BasePackage.baseClass;
import Pages.Task1SauceDemo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;
import utilities.commonFunctions;

public class Task1SauceDemoTest extends baseClass {
    Task1SauceDemo task1;
    commonFunctions commonFtns;
    @BeforeClass
    @Parameters({"browserName"})
    public void loginSetup(String browserName) throws InterruptedException{
        baseClass.setUp(browserName);
        task1 = new Task1SauceDemo(driver);
        commonFtns = new commonFunctions();
    }
    @Test(priority = 1)
    public void login_With_InValid_Password_Test(){
        task1.LoginWithInvalidPassword();
    }

    @Test(priority = 2)
    public void login_With_Valid_Password_Test(){
        task1.LoginWithValidCreds();
        task1.sortTheItemsFromLowToHigh();

    }

    @Test(priority = 3)
    public void contact_Us_Form_Submission() {
        task1.selectmenu();
        task1.selectAbout();
        task1.contactus();
//        task1.contactFormSubmission();
    }

    @AfterClass
    public static void tearDown(){
        baseClass.tearDown();
    }

}
