package org.zigwheels.tests.electriccars;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.ElectricCarsPage;
import org.zigwheels.pages.HomePage;
import utilities.LogUtil;

public class TC_1ElectricCars_CategoryFlow extends BaseTest {

    @Test
    public void verifyElectricCarsCategoryFlow() {

        LogUtil.info("Starting TC_1ElecCarsCategoryFlow");
        HomePage homePage = new HomePage(driver);
        LogUtil.info("Navigating to Electric Cars from Home page");
        homePage.openElectricCars();
        ElectricCarsPage electricCarsPage = new ElectricCarsPage(driver);
        LogUtil.info("Validating Electric Cars page load");
        Assert.assertTrue(
                electricCarsPage.isPageLoaded(),
                "Electric Cars page did not load successfully"
        );
        LogUtil.info("Electric Cars page loaded successfully");
    }
}