package org.zigwheels.tests.usedcars;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UsedCarsPage;
import utilities.ConfigReader;
import utilities.LogUtil;

public class TC_23UsedCars_ValidateSortLowToHigh extends BaseTest {

    @Test
    public void validateSortByPriceLowToHigh() {

        HomePage homePage = new HomePage(driver);
        LogUtil.info("Opening Used Cars page");

        homePage.openUsedCarsPage();
        LogUtil.info("Selecting Chennai city");

        UsedCarsPage usedCarsPage = new UsedCarsPage(driver);
        usedCarsPage.selectChennaiCity();
        LogUtil.info("Applying sort: Price Low to High");

        usedCarsPage.selectSortByLowToHigh();
        LogUtil.info("Validating selected sort option");

        String expectedSort =
                ConfigReader.getProperty("sort.lowToHigh");

        String actualSort =
                usedCarsPage.getSelectedSortOption();

        Assert.assertEquals(
                actualSort,
                expectedSort,
                "Sort option is not set correctly"
        );
        LogUtil.info("Sort By Price Low to High validated successfully");
    }
}
