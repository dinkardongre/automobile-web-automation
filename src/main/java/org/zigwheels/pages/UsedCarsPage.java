package org.zigwheels.pages;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    @FindBy(xpath = "//label[@for='price2']")
    WebElement under5LakhsOption;

    @FindBy(xpath = "//span[contains(@class,'zw-cmn-price')]")
    List<WebElement> carPrices;


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

        LogUtil.info("Waiting for Chennai heading");
        return waitUtils.waitForCondition(driver ->
                cityHeading.getText().toLowerCase().contains("chennai")
        );
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

    public boolean verifyPricesUnder(int maxPrice) {

        waitUtils.waitForCondition(driver -> carPrices.size() > 0);

        for (WebElement priceElement : carPrices) {
            String priceText = priceElement.getText();
            // Example: "Rs. 4.5 Lakh"
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

}



