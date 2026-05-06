package org.zigwheels.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonCode;
import java.util.List;

public class CngCarsPage extends CommonCode {

    public CngCarsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@id='carModels']/div/span")
    private WebElement viewMore;

    @FindBy(xpath = "//a[@data-track-label='-model-name']")
    private List<WebElement> carNames;

    @FindBy(xpath = "//span[contains(@class,'b fnt-black fnt-14 fl')]")
    private List<WebElement> carPrices;

    @FindBy(xpath = "//span[contains(@class,'clr-bl fr')]")
    private List<WebElement> emiValues;

    @FindBy(xpath = "//ul[@id='modelList']/li/div/div/a")
    private List<WebElement> carLinks;

    @FindBy(xpath = "//table[contains(@class,'new-price-container-rhs')]//tbody/tr")
    private List<WebElement> rhsPriceRows;

    @FindBy(xpath = "//div[contains(@class,'r-w fnt-12 rel i-b rt-lg')]")
    private List<WebElement> carRatings;

    public List<WebElement> getCarRatings() {
        if (!carRatings.isEmpty()) {
            scrollIntoView(carRatings.get(0));
        }
        return carRatings;
    }

    public void clickViewMore() {
        try {
            scrollIntoView(viewMore);
            jsClick(viewMore);
        } catch (Exception ignored) {}
    }

    public List<WebElement> getCarNames() {
        if (!carNames.isEmpty()) {
            scrollIntoView(carNames.get(0));
        }
        return carNames;
    }

    public int getCarCount() {
        return carLinks.size();
    }

    public void clickCarByIndex(int index) {
        WebElement car = carLinks.get(index);
        scrollIntoView(car);
        jsClick(car);
    }

    public boolean clickViewMoreIfAvailable() {
        try {
            if (viewMore.isDisplayed()) {
                scrollIntoView(viewMore);
                jsClick(viewMore);
                return true;
            }
        } catch (Exception ignored) {}
        return false;
    }

    public String findLowestCityPriceFromRhs() {

        String lowestCity = "";
        double lowestPrice = Double.MAX_VALUE;

        for (WebElement row : rhsPriceRows) {

            String city = row.findElement(By.xpath("./td[1]")).getText().trim();
            String priceText = row.findElement(By.xpath("./td[2]")).getText().trim();

            double price = Double.parseDouble(
                    priceText.replace("Rs.", "")
                            .replace("Lakh", "")
                            .trim()
            );

            if (price < lowestPrice) {
                lowestPrice = price;
                lowestCity = city;
            }
        }

        return lowestCity + " | Rs. " + lowestPrice + " Lakh";
    }

    public List<WebElement> getCarPrices() {
        return carPrices;
    }

    public List<WebElement> getEmiValues() {
        if (!emiValues.isEmpty()) {
            scrollIntoView(emiValues.get(0));
        }
        return emiValues;
    }
}