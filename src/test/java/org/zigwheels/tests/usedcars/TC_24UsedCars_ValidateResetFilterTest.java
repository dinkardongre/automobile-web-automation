package org.zigwheels.tests.usedcars;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UsedCarsPage;
import utilities.LogUtil;
import utilities.ScreenshotUtil;
import java.io.IOException;

public class TC_24UsedCars_ValidateResetFilterTest extends BaseTest {
    @Test
    public void validateResetFilter() throws IOException {

        LogUtil.info("Test Started: Validate Reset Filter");

        HomePage homePage = new HomePage(driver);

        LogUtil.info("Opening Used Cars page");

        homePage.openUsedCarsPage();
        LogUtil.info("Selecting Chennai city");

        UsedCarsPage usedCarsPage = new UsedCarsPage(driver);
        usedCarsPage.selectChennaiCity();
        LogUtil.info("Capturing heading before reset");

        String headingBeforeReset = usedCarsPage.getUsedCarsHeading();
        LogUtil.info("Applying price filter: Under 5 Lakhs");

        usedCarsPage.selectPriceUnder5Lakhs();
        ScreenshotUtil.captureScreenshot(driver, "UsedCarsUnder5Lakhs_loaded.png");
        LogUtil.info("Clicking Reset All filter");

        usedCarsPage.clickReset();
        LogUtil.info("Capturing heading after reset");

        String headingAfterReset = usedCarsPage.getUsedCarsHeading();

        Assert.assertEquals(
                headingAfterReset,
                headingBeforeReset,
                "Page heading did not reset correctly after Reset All"
        );
        LogUtil.info("Reset filter validated successfully");

        LogUtil.info("Test Finished Successfully");

    }
}