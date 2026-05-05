package org.zigwheels.tests.usedcars;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UsedCarsPage;
import utilities.ConfigReader;
import utilities.LogUtil;

public class TC_23UsedCars_ValidateSortDropdown extends BaseTest {

    @Test
    public void validateSortDropdownLowToHighSelection() {

        LogUtil.info("Test Started: Validate Sort Dropdown");

        HomePage homePage = new HomePage(driver);
        homePage.openUsedCarsPage();
        LogUtil.info("Opened Used Cars page");

        UsedCarsPage usedCarsPage = new UsedCarsPage(driver);
        usedCarsPage.selectChennaiCity();
        LogUtil.info("Selected Chennai city");

        usedCarsPage.selectSortByLowToHigh();
        LogUtil.info("Applied sort: Price Low to High");

        String expectedSort =
                ConfigReader.getProperty("sort.lowToHigh");

        String actualSort =
                usedCarsPage.getSelectedSortOption();

        Assert.assertEquals(
                actualSort,
                expectedSort,
                "Sort dropdown value mismatch"
        );

        LogUtil.info("Sort dropdown validated successfully");

        LogUtil.info("Test Finished Successfully");
    }
}
