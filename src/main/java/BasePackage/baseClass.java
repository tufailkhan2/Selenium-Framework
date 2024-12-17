package BasePackage;

import configProperties.configLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.commonFunctions;

import java.io.File;

public class baseClass {
    protected static WebDriver driver;
    protected static configLoader config;


    public static void setUp(String browserName) {
        config = new configLoader();
//        String browser = config.getProperty("browser");
        String baseUrl = config.getProperty("baseUrl");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
