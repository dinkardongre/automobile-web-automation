package org.zigwheels.tests.upcomingBikes;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UpcomingBikesPage;
import utilities.LogUtil;
import utilities.ScreenshotUtil;

import java.io.IOException;
import java.util.List;

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

        List<String> prices = bp.getUpcomingBikesPrices();
        Assert.assertFalse(prices.isEmpty(), "No bike displayed after applying budget filter");
        for (String price : prices) {
            double priceValue = extractPriceInLakhs(price);
            Assert.assertTrue(
                    priceValue <= 5,
                    "Bike price exceeds 5 Lakh: " + price
            );
        }
        LogUtil.info("All displayed Bikes are under 5 Lakh");
    }
    private double extractPriceInLakhs(String priceText) {
        return Double.parseDouble(
                priceText
                        .replace("Rs.", "")
                        .replace("Lakh", "")
                        .trim()
        );
    }
}