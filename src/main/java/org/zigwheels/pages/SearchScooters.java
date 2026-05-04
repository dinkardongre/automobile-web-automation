package org.zigwheels.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.CommonCode;
import utilities.WaitUtils;

import java.util.List;


public class SearchScooters extends CommonCode {
    private WaitUtils waitUtils;


    @FindBy(xpath = "//h2[text()='Browse Scooters By Budget']")
    private WebElement scrollToScootersByBudget;

    @FindBy(xpath = "//a[contains(text(),'Scooters under 40000')]")
    private WebElement scootersUnder40000Filter;

    // Scooter prices
    @FindBy(xpath = "//span[@title=' Ex-Showroom Price']")
    private List<WebElement> scooterPrices;


    @FindBy(xpath = "//*[@id='manufacturers']/div/div/div/div/div[1]/ul/li[1]/div/a/div")
    private WebElement tvsBrand;

    //Scooter names after brand filter
    @FindBy(xpath = "//a[@data-track-label='launched-model-name']")
    private List<WebElement> scooterNames;


    // Search Scooters section
    @FindBy(xpath = "//*[@id='manufacturers']/h2")
    private WebElement searchScootersSection;


    // Make dropdown (HTML <select>)
    @FindBy(id = "byBrandMake")
    private WebElement makeDropdown;

    // Model dropdown (HTML <select>)
    @FindBy(id = "byBrandModel")
    private WebElement modelDropdown;

    // Search button
    @FindBy(xpath = "//button[text()='Search']")
    private WebElement searchButton;

    // Result page title (TVS Jupiter)
    @FindBy(xpath = "//h1")
    private WebElement scooterTitle;

    public SearchScooters(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickScootersUnder40000Filter() {
        scrollIntoView(scrollToScootersByBudget);
        waitUtils.waitForClickable(scootersUnder40000Filter).click();
    }

    public List<WebElement> getAllScooterPrices() {
        waitUtils.waitForCondition(driver -> scooterPrices.size() > 0);
        return scooterPrices;
    }

    public void clickTVSBrand() {
        scrollIntoView(tvsBrand);
        waitUtils.waitForClickable(tvsBrand);
        jsClick(tvsBrand);
    }

    public List<WebElement> getScooterNames() {
        waitUtils.waitForCondition(driver -> scooterNames.size() > 0);
        return scooterNames;
    }

    public void searchTVSJupiter() {
        //Scroll first
        scrollIntoView(searchScootersSection);

        //Select Make
        Select make = new Select(makeDropdown);
        make.selectByVisibleText("TVS");

        waitUtils.waitForCondition(driver ->
                new Select(modelDropdown).getOptions().size() > 1
        );

        //Select Model
        Select model = new Select(modelDropdown);
        model.selectByVisibleText("Jupiter");

        //Click Search
        waitUtils.waitForClickable(searchButton).click();
    }

    public String getScooterTitle() {
        return waitUtils.waitForVisibility(scooterTitle).getText();
    }





}
