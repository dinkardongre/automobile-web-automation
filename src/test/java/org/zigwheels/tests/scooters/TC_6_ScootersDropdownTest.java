package org.zigwheels.tests.scooters;

import basetest.BaseTest;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.SearchScooters;
import utilities.LogUtil;

public class TC_6_ScootersDropdownTest extends BaseTest {
    private SearchScooters scootersPage;

    @Test(priority = 1)
    public void testDropdownVisibility() {
        // Initialize page object
        scootersPage = new SearchScooters(driver);

        // Hover over Scooters menu to reveal dropdown
        Actions actions = new Actions(driver);
        actions.moveToElement(scootersPage.getScootersOptions()).perform();

        // Validate dropdown options are visible
        Assert.assertTrue(scootersPage.getSearchNewScooters().isDisplayed(),
                "Search New Scooters option is not visible");
        LogUtil.info("Search New Scooters option is visible");

        Assert.assertTrue(scootersPage.getElectricScooters().isDisplayed(),
                "Electric Scooters option is not visible");
        LogUtil.info("Electric Scooters option is visible");

        System.out.println("Validation successful: Both dropdown options are displayed");
    }
}
