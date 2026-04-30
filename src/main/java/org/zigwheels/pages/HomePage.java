package org.zigwheels.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonCode;
import utilities.WaitUtils;

public class HomePage extends CommonCode {

    private WaitUtils wait;
    CommonCode actionCode = new CommonCode(driver);

    @FindBy(xpath = "//span[text()='NEW CARS']")
    private WebElement newCarsMenu;

    @FindBy(xpath = "//a[text()='Electric Cars']")
    private WebElement electricCarsOption;

    @FindBy(xpath = "//*[@id='headerNewVNavWrap']/nav/ul/li[3]/span")
    private WebElement newBikesMenu;

    @FindBy(xpath = "//a[@title='Upcoming Bikes']")
    private WebElement upcomingBikesLink;

    @FindBy(xpath = "(//div[@id='headerNewVNavWrap']/nav/ul/li/span)[4]")
    private WebElement scootersMenu;

    @FindBy(linkText = "Search New Scooters")
    private WebElement searchNewScooters;

    @FindBy(linkText = "Electric Scooters")
    private WebElement electricScooters;

    @FindBy(xpath = "//span[text()='MORE']")
    private WebElement moreMenu;

    @FindBy(xpath = "//a[normalize-space()='Used Cars']")
    private WebElement usedCarsLink;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.wait = new WaitUtils(driver);
    }
    public WebElement getNewBikesMenus(){
        return newBikesMenu;
    }

    public void openElectricCars() {
        actionCode.actionMethod(newCarsMenu);
        wait.waitForVisibility(electricCarsOption);
        electricCarsOption.click();
    }

    public void openUpcomingBikes(){
        upcomingBikesLink.click();
   }

    public void hoverOnScootersMenu() {
        actionMethod(wait.waitForVisibility(scootersMenu));
    }

    public void clickSearchNewScooters() {
        wait.waitForClickable(searchNewScooters).click();
    }

    public void clickElectricScooters() {
        wait.waitForClickable(electricScooters).click();
    }

    public boolean isSearchNewScootersVisible() {
        return wait.waitForVisibility(searchNewScooters).isDisplayed();
    }

    public boolean isElectricScootersVisible() {
        return wait.waitForVisibility(electricScooters).isDisplayed();
    }

    public void openUsedCarsPage() {
        actionCode.actionMethod(moreMenu);
        wait.waitForVisibility(usedCarsLink);
        usedCarsLink.click();
    }

}
