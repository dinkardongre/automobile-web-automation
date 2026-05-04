package org.zigwheels.tests.upcomingBikes;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UpcomingBikesPage;
import utilities.LogUtil;
import utilities.ScreenshotUtil;

import java.io.IOException;

public class TC_20ElectricBikesUnder50K extends BaseTest {

    @Test
    public void verifyAllBikePricesAreUnder50K() throws IOException {

        LogUtil.info("Test Started: Validate all bike prices are under 50,000");

        // Navigate to Electric Bikes (or Upcoming Bikes based on your flow)
        HomePage homePage = new HomePage(driver);
        homePage.openElectricBikes();

        UpcomingBikesPage bp = new UpcomingBikesPage(driver);

        LogUtil.info("Applying price filter: Under 50,000");
        bp.priceUnder50K();

        ScreenshotUtil.captureScreenshot(driver, "After_Applying_Under_50K_Filter.png");

        LogUtil.info("Validating all displayed prices are under 50,000");
        boolean arePricesValid = bp.areAllPricesUnder50K();

        Assert.assertTrue(
                arePricesValid,
                "Some bikes have Ex-Showroom price greater than 50,000"
        );

        LogUtil.info("All bike prices are successfully validated under 50,000");
    }
}