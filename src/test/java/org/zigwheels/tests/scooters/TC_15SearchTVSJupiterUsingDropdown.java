package org.zigwheels.tests.scooters;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.SearchScooters;
import utilities.LogUtil;
import utilities.ScreenshotUtil;
import java.io.IOException;

public class TC_15SearchTVSJupiterUsingDropdown extends BaseTest {

    @Test
    public void validateTVSJupiterSearch() throws IOException {

        HomePage homePage = new HomePage(driver);
        SearchScooters searchScooters = new SearchScooters(driver);

        LogUtil.info("Navigate to Search New Scooters");
        homePage.hoverOnScootersMenu();
        homePage.clickSearchNewScooters();

        LogUtil.info("Search TVS Jupiter using dropdowns");
        searchScooters.searchTVSJupiter();

        ScreenshotUtil.captureScreenshot(driver,"TvsJupiter_Loaded");

        String title = searchScooters.getScooterTitle();
        LogUtil.info("Result page title: " + title);

        Assert.assertTrue(title.contains("TVS"),
                "Brand validation failed");
        Assert.assertTrue(title.contains("Jupiter"),
                "Model validation failed");

        LogUtil.info("TVS + Jupiter validation successful");
    }
}