package org.zigwheels.tests.usedcars;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UsedCarsPage;
import utilities.LogUtil;

public class TC_22UsedCars_ValidatePriceFilter extends BaseTest {
    @Test
    public void validatePriceFilterUnder5Lakhs() {

        HomePage homePage = new HomePage(driver);
        LogUtil.info("Opening Used Cars page");

        homePage.openUsedCarsPage();
        LogUtil.info("Selecting Chennai city");

        UsedCarsPage usedCarsPage = new UsedCarsPage(driver);
        usedCarsPage.selectChennaiCity();
        LogUtil.info("Applying price filter: Under 5 Lakhs");

        usedCarsPage.selectPriceUnder5Lakhs();
        LogUtil.info("Validating all prices of only displayed cars are under 5 Lakhs");

        boolean isValid = usedCarsPage.verifyPricesUnder(500000);

        Assert.assertTrue(
                isValid,
                "Some cars exceed 5 Lakhs"
        );
        LogUtil.info("Price filter validation successful");
    }
}
