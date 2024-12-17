package Pages;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import BasePackage.baseClass;
import utilities.commonFunctions;

public class PracticePage extends baseClass{

    commonFunctions commonFtns = new commonFunctions();
    public PracticePage(WebDriver driver) {
        baseClass.driver = driver;
    }

    // Main Functions

    public void clickOnStartCollectingGoldButton() throws InterruptedException {
        // Find the canvas element
        WebElement canvas = driver.findElement(By.xpath("//canvas[@id='skeletonAd']"));

        String canvasLocator = "//canvas[@id='skeletonAd']";

        // Wait for the button to appear
        Thread.sleep(1000);

        commonFtns.clickOnElement("xpath", canvasLocator);
        // Use Actions to click on the center of the Canvas where the button is
        new Actions(driver)
                .moveToElement(canvas)
                .click()
                .perform();
    }
}