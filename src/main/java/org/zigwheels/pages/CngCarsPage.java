package org.zigwheels.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CngCarsPage {

    private WebDriver driver;
    private JavascriptExecutor js;

    public CngCarsPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='carModels']/div/span")
    private WebElement viewMore;

    @FindBy(xpath = "//*[@id='modelList']/li/div/div[2]/div[1]")
    private List<WebElement> carNames;

    @FindBy(xpath = "//*[@id='modelList']/li/div/div/span[1]")
    private List<WebElement> carPrices;

    @FindBy(xpath = "//span[contains(@class,'clr-bl fr')]")
    private List<WebElement> emiValues;


    public void clickViewMore() {
        try {
            js.executeScript("arguments[0].scrollIntoView(true);", viewMore);
            js.executeScript("arguments[0].click();", viewMore);
        } catch (Exception ignored) {}
    }


    public List<WebElement> getCarNames() {
        if (!carNames.isEmpty()) {
            js.executeScript("arguments[0].scrollIntoView(true);", carNames.get(0));
        }
        return carNames;
    }

    public List<WebElement> getCarPrices() {
        return carPrices;
    }

    public List<WebElement> getEmiValues() {
        if (!emiValues.isEmpty()) {
            js.executeScript("arguments[0].scrollIntoView(true);", emiValues.get(0));
        }
        return emiValues;
    }
}
