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
import java.util.ArrayList;
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

    @FindBy(id = "websortbyusedcar")
    private WebElement sortDropdown;

    @FindBy(xpath = "//h1[contains(text(),'Used Cars in Chennai')]")
    private WebElement Usedcarheading;

    @FindBy(xpath = "(//input[@class='ui-autocomplete-input usedCarMakeModel'])[1]")
    private WebElement searchInput;

    @FindBy(xpath = "//ul[contains(@class,'ui-autocomplete')]//li//a")
    private List<WebElement> autoCompleteOptions;

    @FindBy(xpath = "//ul[contains(@class,'popularModels')]//label")
    private List<WebElement> popularModels;

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

    public void selectPriceUnder5Lakhs() {
        jsClick(under5LakhsOption);
        waitForPricesToLoad();
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

    public void selectSortByLowToHigh() {
        waitUtils.waitForVisibility(sortDropdown);

        String sortValue =
                ConfigReader.getProperty("sort.lowToHigh");

        Select select = new Select(sortDropdown);
        select.selectByVisibleText(sortValue);

        waitForPricesToLoad();
    }

    public String getSelectedSortOption() {
        Select select = new Select(sortDropdown);
        return select.getFirstSelectedOption().getText().trim();
    }

    public List<String> getPopularModelsList() {
        List<String> popularModelsList = new ArrayList<>();

        for (WebElement model : popularModels) {
            popularModelsList.add(model.getText().trim());
        }

        return popularModelsList;
    }
}




