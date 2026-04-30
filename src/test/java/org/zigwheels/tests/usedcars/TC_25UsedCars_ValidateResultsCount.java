package org.zigwheels.tests.usedcars;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UsedCarsPage;
import utilities.LogUtil;

public class TC_25UsedCars_ValidateResultsCount extends BaseTest {

    @Test
    public void validateResultsCountConsistency() {

        HomePage homePage = new HomePage(driver);
        LogUtil.info("Opening Used Cars page");

        homePage.openUsedCarsPage();
        LogUtil.info("Selecting Chennai city");

        UsedCarsPage usedCarsPage = new UsedCarsPage(driver);
        usedCarsPage.selectChennaiCity();

        int totalResults = usedCarsPage.getResultsCountFromHeading();

        int visibleCars = usedCarsPage.getVisibleCarCount();
        LogUtil.info("Validate data exists");

        Assert.assertTrue(
                visibleCars > 0,
                "No cars displayed on Chennai page"
        );
        LogUtil.info("Validate logical consistency");

        Assert.assertTrue(
                totalResults >= visibleCars,
                "Visible cars exceed total results"
        );
    }
}