package org.zigwheels.pages;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utilities.ConfigReader;
import utilities.LogUtil;
import utilities.WaitUtils;

import java.util.List;

public class UsedCarsPage extends CommanCode {

    private WaitUtils waitUtils;

    @FindBy(xpath = "//span[text()='MORE']")
    private WebElement moreMenu;

    // Used Cars option
    @FindBy(xpath = "//a[normalize-space()='Used Cars']")
    private WebElement usedCarsLink;

    @FindBy(xpath = "//div[@id='ctpgray']//li//a[starts-with(text(),'Chennai')]")
    private WebElement chennaiCity;

    @FindBy(xpath = "//h1[contains(text(),'Used Cars in')]")
    WebElement cityHeading;
////label[@for='price2']
    @FindBy(xpath = "//label[contains(text(),'Under 5 Lakhs')]")
    WebElement under5LakhsOption;

    @FindBy(xpath = "//span[contains(@class,'zw-cmn-price')]")
    List<WebElement> carPrices;

    @FindBy(xpath = "//a[text()='Reset All']")
    private WebElement resetButton;

    @FindBy(id="websortbyusedcar")
    private WebElement sortDropdown;

    public UsedCarsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.waitUtils = new WaitUtils(driver);
    }

    // Open Used Cars page
    public void openUsedCarsPage() {

        moreMenu.click();
        waitUtils.waitForClickable(usedCarsLink).click();
    }

    // Select Chennai city
    public void selectChennaiCity() {

        waitUtils.waitForVisibility(chennaiCity);
        scrollIntoView(chennaiCity);
        jsClick(chennaiCity);
    }

    public boolean isChennaiPageLoaded() {
            try {
                String expectedCity = ConfigReader.getProperty("city.chennai");
                wait.until(ExpectedConditions.textToBePresentInElement(
                        cityHeading, expectedCity));
                return true;
            } catch (Exception e) {
                return false;
            }
    }

    public void selectPriceUnder5Lakhs() {

        waitUtils.waitForClickable(under5LakhsOption);
        scrollIntoView(under5LakhsOption);
        under5LakhsOption.click();
        waitForPriceFilterUpdate();
    }

    public int convertPriceToNumber(String priceText) {

        priceText = priceText.toLowerCase()
                .replace("rs.", "")
                .trim();

        if (priceText.contains("lakh")) {
            double value = Double.parseDouble(priceText.replace("lakh", "").trim());
            return (int) (value * 100000);
        }

        if (priceText.contains("crore")) {
            double value = Double.parseDouble(priceText.replace("crore", "").trim());
            return (int) (value * 10000000);
        }
        return Integer.parseInt(priceText);
    }
    public boolean waitForPricesToLoad() {
        try {
            waitUtils.waitForAllVisible(carPrices);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

public boolean verifyPricesUnder(int maxPrice) {

    waitForPricesToLoad();

    for (WebElement priceElement : carPrices) {
        String priceText = priceElement.getText();
        int price = convertPriceToNumber(priceText);

        LogUtil.info("Price found: " + price);

        if (price > maxPrice) {
            return false;
        }
    }
    return true;
}

    public void waitForPriceFilterUpdate() {

       WebElement oldFirstCar = carPrices.get(0);
        waitUtils.waitForCondition(driver -> {
            try {
                oldFirstCar.isDisplayed();
                return false;
            } catch (StaleElementReferenceException e) {
                return true;
            }
        });
    }

    public void clickReset() {
        waitUtils.waitForClickable(resetButton);
        resetButton.click();
        waitForPriceFilterUpdate();
    }

    public boolean isFilterReset() {
        // 1. No price filter should be selected
        boolean noFilterSelected = !under5LakhsOption.isSelected();
        // 2. Car list should still be present
        boolean carsDisplayed = carPrices.size() > 0;
        return noFilterSelected && carsDisplayed;
    }

    public void selectSortByLowToHigh(){
        waitUtils.waitForVisibility(sortDropdown);
        Select select =new Select(sortDropdown);
        select.selectByVisibleText("Price : Low to High");
        waitForPricesToLoad();
    }

    public String getSelectedSortOption(){
        Select select=new Select(sortDropdown);
        return select.getFirstSelectedOption().getText();
    }



}



