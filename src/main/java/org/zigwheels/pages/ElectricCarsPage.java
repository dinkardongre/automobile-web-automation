package org.zigwheels.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElectricCarsPage extends BasePage {

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

    public boolean isEvTagPresent() {
        return !evTags.isEmpty();
    }

    public boolean isNameAndPriceAvailableForCars() {
        return !carNames.isEmpty() && !carPrices.isEmpty();
    }

    public boolean hasDuplicateCarNames() {
        Set<String> uniqueNames = new HashSet<>();
        for (WebElement car : carNames) {
            if (!uniqueNames.add(car.getText().trim())) {
                return true;
            }
        }
        return false;
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

}