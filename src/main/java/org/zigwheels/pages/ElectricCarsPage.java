package org.zigwheels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElectricCarsPage extends BasePage {

    // Page header
    @FindBy(xpath = "//h1[text()= 'Electric Cars']")
    private WebElement electricCarsHeader;

    // Electric car cards
    @FindBy(xpath = "//div[contains(@class,'card')]")
    private List<WebElement> electricCarCards;

    // Electric car names
    @FindBy(xpath = "//a[@data-track-label='-model-name']")
    private List<WebElement> carNames;

    // Electric car prices
    @FindBy(xpath = "//span[@title=' Ex-Showroom Price']")
    private List<WebElement> carPrices;

    // EV tag/icon
    @FindBy(css = ".rw-l-Ev")
    private List<WebElement> evTags;

    public ElectricCarsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isElectricCarsPageDisplayed() {
        return electricCarsHeader.isDisplayed();
    }

    public int getElectricCarsCount() {
        return electricCarCards.size();
    }

    public boolean areEvTagsDisplayed() {
        return !evTags.isEmpty();
    }

    public boolean areCarNamesAndPricesDisplayed() {
        return !carNames.isEmpty() && !carPrices.isEmpty();
    }

    public boolean hasDuplicateElectricCars() {
        Set<String> uniqueNames = new HashSet<>();

        for (WebElement e : carNames) {
            String name = e.getText().trim();
            if (!uniqueNames.add(name)) {
                return true;
            }
        }
        return false;
    }
}