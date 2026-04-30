package utilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.interactions.Actions;
public class CommonCode {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public CommonCode(WebDriver driver)  {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected void actionMethod(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
    protected void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void jsClick(WebElement element) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
   }