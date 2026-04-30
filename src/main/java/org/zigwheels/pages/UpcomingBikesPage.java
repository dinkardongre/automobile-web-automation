package org.zigwheels.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonCode;
import utilities.WaitUtils;
import java.util.ArrayList;
import java.util.List;

public class UpcomingBikesPage extends CommonCode {

    private final WaitUtils waitUtils;

    @FindBy(xpath = "//span[text()='View More Bikes ']")
    private WebElement viewMoreBikes;

    @FindBy(xpath = "//a[text()='Honda']")
    private WebElement hondaManufacturer;

    @FindBy(xpath = "//div[contains(@class,'modelName')]")
    private List<WebElement> hondaBikeNames;

    @FindBy(xpath = "(//a[@class='lnk-c'])[4]")
    private WebElement upcmngBikesUndr5lkhs;

    public UpcomingBikesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.waitUtils = new WaitUtils(driver);
    }

    public void scrollToViewMoreBikes() {
        scrollIntoView(viewMoreBikes);
    }

    public void clickHondaManufacturer() {
        waitUtils.waitForClickable(hondaManufacturer).click();
    }

    public List<String> getHondaBikeNames() {
        List<String> names = new ArrayList<>();
        waitUtils.waitForAllVisible(hondaBikeNames);
        for (WebElement bike : hondaBikeNames) {
            names.add(bike.getText().trim());
        }
        return names;
    }
    public void upcomingBikesUndrer5lakhs(){
        scrollIntoView(upcmngBikesUndr5lkhs);
        waitUtils.waitForClickable(upcmngBikesUndr5lkhs).click();
    }
}