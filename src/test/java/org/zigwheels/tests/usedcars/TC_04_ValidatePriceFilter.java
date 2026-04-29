package org.zigwheels.tests.usedcars;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.UsedCarsPage;
import utilities.LogUtil;

public class TC_04_ValidatePriceFilter extends BaseTest {
    @Test
    public void validatePriceFilterUnder5Lakhs() {
        UsedCarsPage usedCarsPage = new UsedCarsPage(driver);

        LogUtil.info("Opening Used Cars page");
        usedCarsPage.openUsedCarsPage();

        LogUtil.info("Selecting Chennai city");
        usedCarsPage.selectChennaiCity();

        Assert.assertTrue(
                usedCarsPage.isChennaiPageLoaded(),
                "Chennai page not loaded"
        );

        LogUtil.info("Applying price filter: Under 5 Lakhs");
        usedCarsPage.selectPriceUnder5Lakhs();

        LogUtil.info("Validating all prices are under 5 Lakhs");
        boolean isValid = usedCarsPage.verifyPricesUnder(500000);

        Assert.assertTrue(
                isValid,
                "Some cars exceed 5 Lakhs"
        );
        LogUtil.info("Price filter validation successful");
    }
}
