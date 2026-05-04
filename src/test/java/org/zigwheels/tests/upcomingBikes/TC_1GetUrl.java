package org.zigwheels.tests.upcomingBikes;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import utilities.ConfigReader;
import utilities.LogUtil;

public class TC_1GetUrl extends BaseTest {

    @Test
    public void verifyUpcomingBikesFlow() {

        LogUtil.info("Test Started: Verify Upcoming Bikes URL Flow");

        String expectedHomeUrl = ConfigReader.getProperty("url");
        LogUtil.info("Navigating to Home URL: " + expectedHomeUrl);

        driver.get(expectedHomeUrl);
        Assert.assertEquals(
                driver.getCurrentUrl(),
                expectedHomeUrl,
                "FAIL: Home URL mismatch"
        );
        LogUtil.info("PASS: Home URL verified successfully");

        HomePage hp = new HomePage(driver);
        LogUtil.info("Navigating to Upcoming Bikes from Home page");

        hp.openUpcomingBikes();
        String expectedUpcomingUrl = ConfigReader.getProperty("getbikesurl");
        String actualUpcomingUrl = driver.getCurrentUrl();

        Assert.assertEquals(
                actualUpcomingUrl,
                expectedUpcomingUrl,
                "FAIL: Upcoming Bikes URL mismatch"
        );
        LogUtil.info("PASS: Upcoming Bikes URL verified successfully");

        LogUtil.info("Test Completed: Verify Upcoming Bikes URL Flow");

    }
}