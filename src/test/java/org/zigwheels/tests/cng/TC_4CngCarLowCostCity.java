package org.zigwheels.tests.cng;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CngCarsPage;
import org.zigwheels.pages.HomePage;
import utilities.LogUtil;

public class TC_4CngCarLowCostCity extends BaseTest {
    @Test
    public void LowCostCity() {

        HomePage homePage = new HomePage(driver);
        CngCarsPage cngCarsPage = new CngCarsPage(driver);

        LogUtil.info("Navigating to CNG Cars section");
        homePage.clickCngSection();

        int index = 0;

        while (true) {

            int carCount = cngCarsPage.getCarCount();
            LogUtil.info("Current visible car count: " + carCount);

            if (index >= carCount) {
                LogUtil.info("Reached end of list, attempting View More");
                if (cngCarsPage.clickViewMoreIfAvailable()) {
                    LogUtil.info("View More clicked, loading more cars");
                    continue;
                } else {
                    LogUtil.info("No more cars available, exiting loop");
                    break;
                }
            }
            LogUtil.info("Clicking car at index: " + index);
            cngCarsPage.clickCarByIndex(index);

            String pageTitle = driver.getTitle();
            LogUtil.info("Navigated to car detail page: " + pageTitle);

            String lowestCityResult = cngCarsPage.findLowestCityPriceFromRhs();
            LogUtil.info("Lowest city price info: " + lowestCityResult);

            Assert.assertNotNull(
                    lowestCityResult,
                    "Lowest city price result should not be null"
            );
            Assert.assertTrue(
                    lowestCityResult.contains("Rs."),
                    "Lowest city price should contain price information"
            );
            LogUtil.info("Navigating back to CNG list page");
            driver.navigate().back();
            index++;
        }

        LogUtil.info("TC_4CngCarLowCostCity execution completed successfully");
    }
}