package org.zigwheels.pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonCode;
import utilities.WaitUtils;
import java.util.ArrayList;
import java.util.List;

public class ElectricCarsPage extends CommonCode {

    private final WaitUtils waitUtils;

    @FindBy(xpath = "//h1[text()='Electric Cars']")
    private WebElement electricCarsHeader;

    @FindBy(xpath = "//div[contains(@class,'card')]")
    private List<WebElement> electricCarCards;

    @FindBy(xpath = "//a[@data-track-label='-model-name']")
    private List<WebElement> carNames;

    @FindBy(xpath = "//span[@title=' Ex-Showroom Price']")
    private List<WebElement> carPrices;

    @FindBy(css = ".rw-l-Ev")
    private List<WebElement> evTags;

    @FindBy(css = "span.clr-bl.fr")
    private List<WebElement> emiValues;

    @FindBy(xpath = "//a[@title='Electric Cars Under 20 Lakh in India']")
    private WebElement under20LakhFilter;

    @FindBy(xpath = "//h1")
    private WebElement evCarsUnder20LakhsHeader;

    public ElectricCarsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.waitUtils = new WaitUtils(driver);
    }

    public boolean isPageLoaded() {
        return waitUtils.waitForVisibility(electricCarsHeader).isDisplayed();
    }

    public int getListedElectricCarsCount() {
        waitUtils.waitForVisibility(electricCarsHeader);
        return electricCarCards.size(); // initial visible cards only
    }

    public List<String> getElectricCarNames() {
        waitUtils.waitForVisibility(electricCarsHeader);
        waitUtils.waitForVisibility(carNames.get(0));
        return carNames.stream()
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .toList();
    }

    public List<String> getElectricCarPrices() {
        waitUtils.waitForVisibility(electricCarsHeader);
        waitUtils.waitForVisibility(carPrices.get(0));
        return carPrices.stream()
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .toList();
    }
    public List<String> getElectricCarPricesUnder20Lakhs() {
        waitUtils.waitForVisibility(evCarsUnder20LakhsHeader);
        waitUtils.waitForVisibility(carPrices.get(0));
        return carPrices.stream()
                .map(e -> e.getText().trim())
                .filter(text -> !text.isEmpty())
                .toList();
    }
    public List<String> getElectricCarEmiValues() {

        waitUtils.waitForVisibility(electricCarsHeader);
        if (emiValues.isEmpty()) {
            return List.of();
        }
        waitUtils.waitForVisibility(emiValues.get(0));

        List<String> emis = new ArrayList<>();
        for (WebElement emi : emiValues) {
            String text = emi.getText().trim();
            if (!text.isEmpty()) {
                emis.add(text);
            }
        }
        return emis;
    }
    public void selectUnder20LakhBudget() {

        waitUtils.scrollIntoView(under20LakhFilter);
        waitUtils.waitForClickable(under20LakhFilter);
        try {
            under20LakhFilter.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", under20LakhFilter);
        }
    }
}