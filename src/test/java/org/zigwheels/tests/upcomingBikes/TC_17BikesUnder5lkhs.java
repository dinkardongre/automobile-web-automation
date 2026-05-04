package org.zigwheels.tests.upcomingBikes;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UpcomingBikesPage;
import utilities.LogUtil;
import utilities.ScreenshotUtil;

import java.io.IOException;

public class TC_17BikesUnder5lkhs extends BaseTest {
    @Test
    public void upcomingBikesRange() throws IOException {

        LogUtil.info("Test Started: Verify Upcoming Bikes Under 5 Lakhs");

        HomePage newBike = new HomePage(driver);
        LogUtil.info("Navigating to Upcoming Bikes from Home page");

        newBike.openUpcomingBikes();
        UpcomingBikesPage bp = new UpcomingBikesPage(driver);

        LogUtil.info("Selecting Upcoming Bikes Under 5 Lakhs filter");
        bp.upcomingBikesUndrer5lakhs();

        ScreenshotUtil.captureScreenshot(driver, "BikesUnder5Lakhs_page.png");

        LogUtil.info("Validating navigation to Upcoming Bikes Under 5 Lakhs page");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("upcoming"),
                "FAIL: Upcoming Bikes Under 5 Lakhs page not displayed"
        );

        LogUtil.info("PASS: Upcoming Bikes Under 5 Lakhs page displayed successfully");
    }
}