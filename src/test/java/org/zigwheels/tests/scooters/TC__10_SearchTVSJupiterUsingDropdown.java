package org.zigwheels.tests.scooters;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.SearchScooters;
import utilities.LogUtil;

public class TC__10_SearchTVSJupiterUsingDropdown extends BaseTest {

    @Test
    public void validateTVSJupiterSearch() {

        HomePage homePage = new HomePage(driver);
        SearchScooters searchScooters = new SearchScooters(driver);

        LogUtil.info("Navigate to Search New Scooters");
        homePage.hoverOnScootersMenu();
        homePage.clickSearchNewScooters();

        LogUtil.info("Search TVS Jupiter using dropdowns");
        searchScooters.searchTVSJupiter();

        String title = searchScooters.getScooterTitle();
        LogUtil.info("Result page title: " + title);

        Assert.assertTrue(title.contains("TVS"),
                "Brand validation failed");
        Assert.assertTrue(title.contains("Jupiter"),
                "Model validation failed");

        LogUtil.info("TVS + Jupiter validation successful");
    }
}