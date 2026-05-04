package org.zigwheels.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    @FindBy(css = ".lnk-hvr.block.of-hid.h-height.txt-ulne")
    private List<WebElement> hondaBikeNames;

    @FindBy(xpath = "//h2[text()='Upcoming Honda Bikes in India ']")
    private WebElement hondaBikeHeader;

    @FindBy(xpath = "//a[text()='Upcoming Bikes Under 5 Lakhs']")
    private WebElement upcmngBikesUndr5lkhs;

    public UpcomingBikesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.waitUtils = new WaitUtils(driver);
    }

    public void clickHondaManufacturer() {
        scrollIntoView(hondaManufacturer);
        try {
            waitUtils.waitForClickable(hondaManufacturer).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hondaManufacturer);
        }
    }

    public List<String> getHondaBikeNames() {
        waitUtils.waitForVisibility(hondaBikeHeader);
        waitUtils.waitForVisibility(hondaBikeNames.get(0));
        return hondaBikeNames.stream()
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .toList();
    }

    public void upcomingBikesUndrer5lakhs(){
        scrollIntoView(upcmngBikesUndr5lkhs);
        jsClick(upcmngBikesUndr5lkhs);
    }
}