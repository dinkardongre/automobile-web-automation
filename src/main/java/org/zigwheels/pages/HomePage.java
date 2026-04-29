package org.zigwheels.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtils;

public class HomePage extends BasePage {

    private final WaitUtils waitUtils;
    private final Actions actions;

    @FindBy(xpath = "//span[text()='NEW CARS']")
    private WebElement newCarsMenu;

    @FindBy(xpath = "//a[text()='Electric Cars']")
    private WebElement electricCarsOption;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.waitUtils = new WaitUtils(driver);
        this.actions = new Actions(driver);
    }

    public void openElectricCars() {

        WebElement menu = waitUtils.waitForVisibility(newCarsMenu);
        actions.moveToElement(menu).pause(800).perform();
        WebElement evCars = waitUtils.waitForClickable(electricCarsOption);
        actions.moveToElement(evCars).click().perform();
    }
}
