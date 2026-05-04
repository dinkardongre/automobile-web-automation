package org.zigwheels.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utilities.CommonCode;
import utilities.ConfigReader;
import utilities.LogUtil;
import utilities.WaitUtils;
import java.util.List;

public class UsedCarsPage extends CommonCode {

    private WaitUtils waitUtils;

    @FindBy(xpath = "//div[@id='ctpgray']//li//a[starts-with(text(),'Chennai')]")
    private WebElement chennaiCity;

    @FindBy(xpath = "//h1")
    WebElement cityHeading;

    @FindBy(xpath = "//input[@id='price2']")
    WebElement under5LakhsOption;

    @FindBy(xpath = "//span[contains(@class,'zw-cmn-price')]")
    List<WebElement> carPrices;

    @FindBy(xpath = "//a[text()='Reset All']")
    private WebElement resetButton;

    @FindBy(id="websortbyusedcar")
    private WebElement sortDropdown;

    @FindBy(xpath = "//div[contains(@class,'zw-sr-paddingLeft')]")
    private List<WebElement> cars;

    @FindBy(className = "ucCounth")
    private WebElement heading;

    @FindBy(xpath = "//h1[contains(text(),'Used Cars in Chennai')]")
    private WebElement  Usedcarheading;

    @FindBy(xpath = "(//input[@class='ui-autocomplete-input usedCarMakeModel'])[1]")
    private WebElement searchInput;

    @FindBy(xpath = "//ul[contains(@class,'ui-autocomplete')]//li//a")
    private List<WebElement> autoCompleteOptions;

    public UsedCarsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.waitUtils = new WaitUtils(driver);
    }

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

    public void selectPriceUnder5Lakhs()  {
        jsClick(under5LakhsOption);
        waitForPricesToLoad();
    }

    public int convertPriceToNumber(String priceText) {
        priceText = priceText.toLowerCase()
                .replace("rs.", "")
                .replace(",","")
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

    public void scrollToSearchButton() {
        waitUtils.waitForVisibility(searchInput);
        scrollIntoView(searchInput);

        LogUtil.info("Scrolled to Search button");
    }

    public boolean areAutoCompleteSuggestionsDisplayed(String searchText) {

       try {
        scrollToSearchButton();
        searchInput.clear();
        searchInput.sendKeys(searchText);
        waitUtils.waitForCondition(driver ->
                autoCompleteOptions.size() > 0
        );

        LogUtil.info("Autocomplete suggestions count: " + autoCompleteOptions.size());
        return autoCompleteOptions.size() > 0;

    } catch (Exception e) {
        return false;
    }
}

    public void clickReset() {
        waitUtils.waitForClickable(resetButton);
        resetButton.click();
        waitForPricesToLoad();
    }

    public String getUsedCarsHeading() {
        waitUtils.waitForVisibility(Usedcarheading);
        String headingText = Usedcarheading.getText();
        return headingText;
    }

    public void selectSortByLowToHigh(){
        waitUtils.waitForVisibility(cityHeading);
        waitUtils.waitForVisibility(sortDropdown);
        Select select =new Select(sortDropdown);
        select.selectByVisibleText("Price : Low to High");
        waitForPricesToLoad();
    }

    public String getSelectedSortOption(){
        Select select=new Select(sortDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public boolean verifyPricesSortedLowToHigh() {
        waitForPricesToLoad();
        int previousPrice = 0;
        for (WebElement priceElement : carPrices) {

            String priceText = priceElement.getText();
            int currentPrice = convertPriceToNumber(priceText);

            if (currentPrice < previousPrice) {
                LogUtil.error(
                        "Sorting failed. Previous price: "
                                + previousPrice + " | Current price: "
                                + currentPrice
                );
                return false;
            }

            previousPrice = currentPrice;
        }
        return true;
    }

    public int getResultsCountFromHeading() {
        waitUtils.waitForVisibility(Usedcarheading);
        String text = heading.getText();
        return Integer.parseInt(text);
    }

    public int getVisibleCarCount() {
        return cars.size();
    }
}



