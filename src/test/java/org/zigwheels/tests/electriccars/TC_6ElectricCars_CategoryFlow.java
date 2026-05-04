package org.zigwheels.tests.electriccars;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.ElectricCarsPage;
import org.zigwheels.pages.HomePage;
import utilities.LogUtil;
import utilities.ScreenshotUtil;

import java.io.IOException;

public class TC_6ElectricCars_CategoryFlow extends BaseTest {

    @Test
    public void verifyElectricCarsCategoryFlow() throws IOException {
        LogUtil.info("Starting TC_1ElecCarsCategoryFlow");

        HomePage homePage = new HomePage(driver);
        LogUtil.info("Navigating to Electric Cars from Home page");

        homePage.openElectricCars();
        ScreenshotUtil.captureScreenshot(driver, "ElectricCarsPage_Loaded");
        ElectricCarsPage electricCarsPage = new ElectricCarsPage(driver);
        LogUtil.info("Validating Electric Cars page load");

        Assert.assertTrue(
                electricCarsPage.isPageLoaded(),
                "Electric Cars page did not load successfully"
        );
        LogUtil.info("Electric Cars page loaded successfully");
    }
}