package org.zigwheels.tests.upcomingBikes;

import basetest.BaseTest;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UpcomingBikesPage;
import utilities.LogUtil;

public class TC_2BikesUnder5lkhs extends BaseTest {

    private HomePage newBike;

    @Test
    public void upcomingBikesRange() {

        LogUtil.info("Test Started: Verify Upcoming Bikes Under 5 Lakhs");

        newBike = new HomePage(driver);
        Actions actions = new Actions(driver);

        LogUtil.info("Hovering over New Bikes menu");
        actions.moveToElement(newBike.getNewBikesMenus()).perform();

        LogUtil.info("Opening Upcoming Bikes page");
        newBike.openUpcomingBikes();

        UpcomingBikesPage bp = new UpcomingBikesPage(driver);

        LogUtil.info("Selecting Upcoming Bikes Under 5 Lakhs filter");
        bp.upcomingBikesUndrer5lakhs();

        LogUtil.info("Validating navigation to Upcoming Bikes Under 5 Lakhs page");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("upcoming"),
                "FAIL: Upcoming Bikes Under 5 Lakhs page not displayed"
        );

        LogUtil.info("PASS: Upcoming Bikes Under 5 Lakhs page displayed successfully");
    }
}