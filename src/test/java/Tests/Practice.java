package Tests;
import Pages.PracticePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import BasePackage.baseClass;

public class Practice extends baseClass{
    PracticePage assessmentPage;

    @BeforeClass
    public void loginSetup(String browserName) throws InterruptedException{
        baseClass.setUp(browserName);
        assessmentPage = new PracticePage(driver);
    }

    // Main Test
//    @Test
//    public void LoginToNeoSoft() throws InterruptedException {
//        assessmentPage.clickOnStartCollectingGoldButton();
//    }

    @AfterClass
    public static void tearDown(){
        baseClass.tearDown();
    }

}