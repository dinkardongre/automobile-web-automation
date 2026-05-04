package org.zigwheels.tests.cng;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.CngCarsPage;
import utilities.LogUtil;
import java.util.List;
import org.openqa.selenium.WebElement;

public class TC_1CngCarsNavigationTest extends BaseTest {

    @Test
    public void verifyCngCarsNavigation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickCngSection();

        CngCarsPage cngPage = new CngCarsPage(driver);
        cngPage.clickViewMore();

        List<WebElement> carNames = cngPage.getCarNames();
        List<WebElement> carPrices = cngPage.getCarPrices();

        Assert.assertFalse(carNames.isEmpty(), "Car names list should not be empty");
        Assert.assertFalse(carPrices.isEmpty(), "Car prices list should not be empty");
        LogUtil.info("Collected " + carNames.size() + " car names and " + carPrices.size() + " car prices");
    }
}