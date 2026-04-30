package org.zigwheels.tests.usedcars;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UsedCarsPage;
import utilities.LogUtil;

public class TC_24UsedCars_ValidateResetFilterTest extends BaseTest {
    @Test
    public void validateResetFilter() {

        HomePage homePage = new HomePage(driver);
        LogUtil.info("Opening Used Cars page");

        homePage.openUsedCarsPage();
        LogUtil.info("Selecting Chennai city");

        UsedCarsPage usedCarsPage = new UsedCarsPage(driver);
        usedCarsPage.selectChennaiCity();
        LogUtil.info("Applying price filter: Under 5 Lakhs");

        usedCarsPage.selectPriceUnder5Lakhs();
        LogUtil.info("Clicking Reset All filter");

        usedCarsPage.clickReset();
        LogUtil.info("Validating filter reset");

        Assert.assertTrue(
                usedCarsPage.isFilterReset(),
                "Filters not reset properly"
        );
    }
}
