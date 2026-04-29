package org.zigwheels.tests.scooters;

import basetest.BaseTest;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.SearchScooters;
import utilities.LogUtil;
import org.zigwheels.pages.HomePage;

public class TC_7_SearchNewScootersNavigationTest extends BaseTest {
    private SearchScooters scootersPage;
    private HomePage homePage;

    @Test
    public void testSearchNewScootersNavigation() {
        LogUtil.info("Starting test: Validate navigation to Search New Scooters");
        scootersPage = new SearchScooters(driver);
        homePage = new HomePage(driver);
        Actions actions = new Actions(driver);
        actions.moveToElement(homePage.getScootersOptions()).perform();
        LogUtil.info("Hovered over Scooters menu");

        scootersPage.clickSearchNewScooters();
        LogUtil.info("Clicked on Search New Scooters option");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("scooters"),
                "Navigation to Search New Scooters failed. Current URL: " + currentUrl);

        LogUtil.info("Validation successful: Navigated to Search New Scooters page");
    }
}
