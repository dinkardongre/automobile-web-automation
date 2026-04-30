package org.zigwheels.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonCode;
import utilities.WaitUtils;

import java.util.List;


public class SearchScooters extends CommonCode {
    private WaitUtils waitUtils;


    @FindBy(xpath = "//a[contains(text(),'Scooters under 40000')]")
    private WebElement scootersUnder40000Filter;

    // Scooter prices
    @FindBy(xpath = "//span[@title=' Ex-Showroom Price']")
    private List<WebElement> scooterPrices;

    public SearchScooters(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickScootersUnder40000Filter() {
        scrollIntoView(scootersUnder40000Filter);   //USING EXISTING METHOD
        waitUtils.waitForClickable(scootersUnder40000Filter).click();
    }

    public List<WebElement> getAllScooterPrices() {
        waitUtils.waitForCondition(driver -> scooterPrices.size() > 0);
        return scooterPrices;
    }


}
