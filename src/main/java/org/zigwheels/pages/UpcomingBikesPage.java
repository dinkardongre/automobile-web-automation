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

    @FindBy(xpath = "//h2[text()='Upcoming Bikes Between 2 To 5 Lakhs in 2026 ']")
    private WebElement under5LakhBikePageHeader;

    @FindBy(xpath = "//div[@class='zw-B-bodyWrap']//a//following-sibling::div[contains(@class,'b fnt-')]")
    private List<WebElement> bikePrices;

    @FindBy(xpath = "//a[text()='Electric Bikes']")
    private WebElement electricBikes;

    @FindBy(xpath = "//span[text()='EV']")
    private List<WebElement> evTags;

    @FindBy(xpath = "//a[text()='Under 50,000']")
    private WebElement priceFilterUnder50K;

    @FindBy(xpath = "//span[@title='Ex-Showroom Price']")
    private List<WebElement> priceTags;

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

    public List<String> getUpcomingBikesPrices() {
        waitUtils.waitForVisibility(under5LakhBikePageHeader);
        waitUtils.waitForVisibility(bikePrices.get(0));
        return bikePrices.stream()
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .toList();
    }

    public void upcomingBikesUndrer5lakhs(){
        scrollIntoView(upcmngBikesUndr5lkhs);
        jsClick(upcmngBikesUndr5lkhs);
    }

    public List<String> getEVTagsText() {
        waitUtils.waitForVisibility(evTags.get(0));
        return evTags.stream()
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .toList();
    }

    public void priceUnder50K() {
        scrollIntoView(priceFilterUnder50K);
        jsClick(priceFilterUnder50K);
    }

    public boolean areAllPricesUnder50K() {

        for (WebElement priceElement : priceTags) {
            String priceText = priceElement.getText().trim();

            if (priceText.isEmpty()) {
                continue;
            }

            int price = Integer.parseInt(
                    priceText
                            .replace("Rs.", "")
                            .replace(",", "")
                            .trim()
            );

            if (price > 50000) {
                return false;
            }
        }
        return true;
    }
}