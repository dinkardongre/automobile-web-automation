package org.zigwheels.tests.usedcars;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UsedCarsPage;
import utilities.LogUtil;

public class TC_21UsedCars_ValidateChennaiLocation extends BaseTest {

    @Test
    public void validateChennaiLocationSelected() {

        HomePage homePage = new HomePage(driver);

        LogUtil.info("Opening Used Cars page");

        homePage.openUsedCarsPage();
        LogUtil.info("Selecting Chennai city");

        UsedCarsPage usedCarsPage = new UsedCarsPage(driver);
        usedCarsPage.selectChennaiCity();
        LogUtil.info("Validating Chennai location");

            Assert.assertTrue(
                    usedCarsPage.isChennaiPageLoaded(),
                    "Chennai page not loaded correctly"
            );
    }
}