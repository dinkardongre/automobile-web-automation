package basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;
import utilities.LogUtil;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        LogUtil.info("Starting browser setup");

        ChromeOptions chromeOptions = new ChromeOptions();

        // Required Chrome options
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--start-maximized");

        // chromeOptions.addArguments("--headless=new");

        driver = new ChromeDriver(chromeOptions);

        LogUtil.info("Launching application URL");
        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            LogUtil.info("Closing browser");
            driver.quit();
        }
    }
}