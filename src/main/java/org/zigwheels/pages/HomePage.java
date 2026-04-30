package org.zigwheels.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonCode;
import utilities.WaitUtils;

public class HomePage extends CommonCode {

    private WaitUtils wait;

    @FindBy(xpath = "//span[text()='NEW CARS']")
    private WebElement newCarsMenu;

    @FindBy(xpath = "//a[text()='Electric Cars']")
    private WebElement electricCarsOption;

    @FindBy(xpath = "//*[@id='headerNewVNavWrap']/nav/ul/li[3]/span")
    private WebElement newBikesMenu;

    @FindBy(xpath = "//a[@title='Upcoming Bikes']")
    private WebElement upcomingBikesLink;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.wait = new WaitUtils(driver);
    }
    public WebElement getNewBikesMenus(){
        return newBikesMenu;
    }
    public void openElectricCars() {
        Actions actions = new Actions(driver);
        wait.waitForVisibility(newCarsMenu);
        actions.moveToElement(newCarsMenu).perform();
        wait.waitForVisibility(electricCarsOption);
        wait.waitForClickable(electricCarsOption);
        electricCarsOption.click();
    }

    public void openUpcomingBikes(){
        upcomingBikesLink.click();
   }
}
