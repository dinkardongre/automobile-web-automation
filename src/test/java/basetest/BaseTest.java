package basetest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;
import utilities.LogUtil;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        LogUtil.info("Starting browser setup");

        String browserName = ConfigReader.getProperty("browser").toLowerCase();

        switch (browserName) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-notifications");
                edgeOptions.addArguments("--disable-popup-blocking");
                edgeOptions.addArguments("--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browserName
                );
        }

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
