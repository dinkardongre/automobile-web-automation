package org.zigwheels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SearchScooters extends BasePage{

    @FindBy(xpath = "(//div[@id='headerNewVNavWrap']/nav/ul/li/span)[4]")
    private WebElement scootersOptions;

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

    public WebElement getScootersOptions() {
        return scootersOptions;
    }

    public WebElement getSearchNewScooters() {
        return searchNewScooters;
    }

    public WebElement getElectricScooters() {
        return electricScooters;
    }

    public void clickSearchNewScooters(){
        searchNewScooters.click();
    }
}
