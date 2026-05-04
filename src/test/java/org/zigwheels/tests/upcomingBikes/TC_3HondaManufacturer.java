package org.zigwheels.tests.upcomingBikes;
import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UpcomingBikesPage;
import utilities.LogUtil;
import utilities.ScreenshotUtil;

import java.io.IOException;
import java.util.List;

public class TC_3HondaManufacturer extends BaseTest {
    @Test
    public void verifyHondaUpcomingBikes() throws IOException {
        LogUtil.info("Test Started: Verify Honda Upcoming Bikes");

        HomePage newBike = new HomePage(driver);
        LogUtil.info("Navigating to Upcoming Bikes from Home page");

        newBike.openUpcomingBikes();

        UpcomingBikesPage bp = new UpcomingBikesPage(driver);
        LogUtil.info("Selecting Honda manufacturer");

        bp.clickHondaManufacturer();
        ScreenshotUtil.captureScreenshot(driver, "Only_HondaBikes_Page.png");
        LogUtil.info("Fetching Honda bike names");

        List<String> hondaBikes = bp.getHondaBikeNames();
        for (String bikeName : hondaBikes) {
            String name = bikeName.toLowerCase();
            Assert.assertTrue(name.contains("honda"),
                    "Found a bike that is not Honda: " + bikeName);
        }

        LogUtil.info("All bikes are Honda manufacturer");
    }
}