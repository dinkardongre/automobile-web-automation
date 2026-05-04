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

public class TC_19ValidateEVBikes extends BaseTest {

    @Test
    public void validateEVTagsOnElectricBikesPage() throws IOException {

        LogUtil.info("Test Started: Validate EV tags on Electric Bikes page");

        HomePage homePage = new HomePage(driver);
        homePage.openElectricBikes();

        UpcomingBikesPage upcomingBikesPage = new UpcomingBikesPage(driver);

        List<String> evTags = upcomingBikesPage.getEVTagsText();
        ScreenshotUtil.captureScreenshot(driver, "EV_Tags.png");

        Assert.assertFalse(evTags.isEmpty(), "EV tags list is empty");

        for (String tag : evTags) {
            Assert.assertEquals(tag, "EV", "Non-EV tag found");
        }

        LogUtil.info("EV tags validation successful");
    }
}