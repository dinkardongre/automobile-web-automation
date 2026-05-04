package org.zigwheels.tests.scooters;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import utilities.LogUtil;

public class TC_11ScootersDropdownTest extends BaseTest {
    private HomePage homePage;
    @Test
    public void testDropdownVisibility() {
        LogUtil.info("Starting test: Validate Scooters dropdown visibility");
        homePage = new HomePage(driver);

        homePage.hoverOnScootersMenu();
        LogUtil.info("Hovered over Scooters menu");

        Assert.assertTrue(
                homePage.isSearchNewScootersVisible(),
                "Search New Scooters option is not visible"
        );
        LogUtil.info("Search New Scooters option is visible");

        Assert.assertTrue(
                homePage.isElectricScootersVisible(),
                "Electric Scooters option is not visible"
        );
        LogUtil.info("Electric Scooters option is visible");
        LogUtil.info("Validation successful: Both dropdown options are displayed");
    }
}