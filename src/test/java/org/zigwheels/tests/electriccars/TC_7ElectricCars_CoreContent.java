package org.zigwheels.tests.electriccars;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.ElectricCarsPage;
import org.zigwheels.pages.HomePage;
import utilities.LogUtil;

public class TC_7ElectricCars_CoreContent extends BaseTest {

    @Test
    public void verifyElectricCarsCoreContent() {
        LogUtil.info("Loading Electric Cars page");

        new HomePage(driver).openElectricCars();
        ElectricCarsPage electricCarsPage = new ElectricCarsPage(driver);
        LogUtil.info("Validating electric cars listing presence");

        Assert.assertTrue(
                electricCarsPage.getListedElectricCarsCount() > 0,
                "No electric cars are displayed on Electric Cars page"
        );
        LogUtil.info("Electric cars core content validated successfully");
    }
}