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

    public static WebDriver driver;

    @BeforeMethod
    public void setUp() {

        LogUtil.info("Starting browser setup");

        String browserName = ConfigReader.getProperty("browser").toLowerCase();

        switch (browserName) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-infobars");
                chromeOptions.addArguments("--disable-gpu");

                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
              //  edgeOptions.addArguments("--headless=new");
                edgeOptions.addArguments("--disable-notifications");
                edgeOptions.addArguments("--disable-popup-blocking");
                edgeOptions.addArguments("--disable-infobars");
                edgeOptions.addArguments("--disable-gpu");

                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browserName
                );
        }

        LogUtil.info("Launching application URL");
        driver.manage().window().maximize();
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
