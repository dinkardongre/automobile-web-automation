package org.zigwheels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtils;

public class HomePage extends BasePage {
    private WaitUtils waitUtils;
    @FindBy(xpath = "//span[text()='NEW CARS']")
    private WebElement newCarsMenu;

    @FindBy(xpath = "//a[text()='Electric Cars']")
    private WebElement electricCarsOption;

    @FindBy(xpath = "(//div[@id='headerNewVNavWrap']/nav/ul/li/span)[4]")
    private WebElement scootersOptions;

    public HomePage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void openElectricCars() {
        newCarsMenu.click();
        electricCarsOption.click();
    }

    public WebElement getScootersOptions() {
        return waitUtils.waitForVisibility(scootersOptions);
    }
}
