package org.zigwheels.tests.scooters;

import basetest.BaseTest;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.SearchScooters;
import utilities.LogUtil;

public class TC_7_SearchNewScootersNavigationTest extends BaseTest {
    private SearchScooters scootersPage;

    @Test
    public void testSearchNewScootersNavigation() {
        LogUtil.info("Starting test: Validate navigation to Search New Scooters");

        // Initialize page object
        scootersPage = new SearchScooters(driver);

        // Hover over Scooters menu to reveal dropdown
        Actions actions = new Actions(driver);
        actions.moveToElement(scootersPage.getScootersOptions()).perform();
        LogUtil.info("Hovered over Scooters menu");

        // Click on Search New Scooters
        scootersPage.clickSearchNewScooters();
        LogUtil.info("Clicked on Search New Scooters option");

        // Validate navigation using hard assertion
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("scooters"),
                "Navigation to Search New Scooters failed. Current URL: " + currentUrl);

        LogUtil.info("Validation successful: Navigated to Search New Scooters page");
    }
}
