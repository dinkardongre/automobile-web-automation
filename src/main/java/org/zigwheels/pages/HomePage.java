package org.zigwheels.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

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
    }
    public WebElement getNewBikesMenus(){
        return newBikesMenu;
    }

    public void openElectricCars() {
        newCarsMenu.click();
        electricCarsOption.click();
    }

   public void openUpcomingBikes(){
//        newBikesMenu.click();
        upcomingBikesLink.click();
   }
}
