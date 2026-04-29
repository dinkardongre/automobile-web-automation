package org.zigwheels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtils;

public class SearchScooters extends BasePage{
    private WaitUtils waitUtils;
    // Scooters menu option in the header
//    @FindBy(xpath = "(//div[@id='headerNewVNavWrap']/nav/ul/li/span)[4]")
//    private WebElement scootersOptions;

    // Dropdown option: Search New Scooters
    @FindBy(linkText = "Search New Scooters")
    private WebElement searchNewScooters;

    // Dropdown option: Electric Scooters
    @FindBy(linkText = "Electric Scooters")
    private WebElement electricScooters;

    public SearchScooters(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Getter methods
//    public WebElement getScootersOptions() {
//        return waitUtils.waitForVisibility(scootersOptions);
//    }

    public WebElement getSearchNewScooters() {
        return waitUtils.waitForVisibility(searchNewScooters);
    }

    public WebElement getElectricScooters() {
        return waitUtils.waitForVisibility(electricScooters);
    }

    public void clickSearchNewScooters(){
        waitUtils.waitForClickable(searchNewScooters).click();
    }
}
