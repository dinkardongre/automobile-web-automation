package org.zigwheels.tests.usedcars;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UsedCarsPage;
import utilities.LogUtil;
import utilities.ScreenshotUtil;
import java.io.IOException;

public class TC_21UsedCars_ValidateChennaiLocation extends BaseTest {
    @Test
    public void validateChennaiLocationSelected() throws IOException {

        LogUtil.info("Test Started : Validate Chennai Location");

        HomePage homePage = new HomePage(driver);
        LogUtil.info("Navigating to Used Cars page");

        homePage.openUsedCarsPage();
        UsedCarsPage usedCarsPage = new UsedCarsPage(driver);
        LogUtil.info("Selecting city as Chennai");

        usedCarsPage.selectChennaiCity();
        LogUtil.info("Capturing screenshot after Chennai selection");

        ScreenshotUtil.captureScreenshot(driver, "UsedCars_ChennaiPageLoaded.png");
        LogUtil.info("Verifying Chennai page is loaded successfully");

        Assert.assertTrue(
                usedCarsPage.isChennaiPageLoaded(),
                "Chennai page not loaded correctly"
        );
        LogUtil.info("Chennai location validated successfully");

        LogUtil.info("Test Finished Successfully");
    }
}