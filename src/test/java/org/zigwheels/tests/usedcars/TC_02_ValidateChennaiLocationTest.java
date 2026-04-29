package org.zigwheels.tests.usedcars;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.UsedCarsPage;
import utilities.LogUtil;

public class TC_02_ValidateChennaiLocationTest extends BaseTest {

    @Test
    public void validateChennaiLocationSelected() {

        UsedCarsPage usedCarsPage = new UsedCarsPage(driver);

        LogUtil.info("Opening Used Cars page");
        usedCarsPage.openUsedCarsPage();

        LogUtil.info("Selecting Chennai city");
        usedCarsPage.selectChennaiCity();

        LogUtil.info("Validating Chennai location");

            //UI validation
            Assert.assertTrue(
                    usedCarsPage.isChennaiPageLoaded(),
                    "Chennai page not loaded correctly"
            );






    }
}