package org.zigwheels.tests.scooters;
import basetest.BaseTest;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.SearchScooters;
import utilities.LogUtil;

public class TC_6_ScootersDropdownTest extends BaseTest {
    private HomePage homePage;
    @Test
    public void testDropdownVisibility() {
        LogUtil.info("Starting test: Validate Scooters dropdown visibility");
        homePage = new HomePage(driver);


        // Hover on Scooters menu
        homePage.hoverOnScootersMenu();
        LogUtil.info("Hovered over Scooters menu");


        // Validate Search New Scooters option
        Assert.assertTrue(
                homePage.isSearchNewScootersVisible(),
                "Search New Scooters option is not visible"
        );
        LogUtil.info("Search New Scooters option is visible");

        // Validate Electric Scooters option
        Assert.assertTrue(
                homePage.isElectricScootersVisible(),
                "Electric Scooters option is not visible"
        );
        LogUtil.info("Electric Scooters option is visible");
        LogUtil.info("Validation successful: Both dropdown options are displayed");
    }
}