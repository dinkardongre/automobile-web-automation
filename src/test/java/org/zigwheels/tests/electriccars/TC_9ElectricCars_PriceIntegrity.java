package org.zigwheels.tests.electriccars;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.ElectricCarsPage;
import org.zigwheels.pages.HomePage;
import utilities.ExportVehicleDetails;
import utilities.LogUtil;
import java.io.IOException;
import java.util.List;

public class TC_9ElectricCars_PriceIntegrity extends BaseTest {

    @Test
    public void verifyElectricCarsPriceIntegrity() throws IOException {
        LogUtil.info("Loading Electric Cars page");

        HomePage homePage = new HomePage(driver);
        homePage.openElectricCars();
        ElectricCarsPage electricCarsPage = new ElectricCarsPage(driver);
        LogUtil.info("Fetching electric car names");

        List<String> carNames = electricCarsPage.getElectricCarNames();
        LogUtil.info("Fetching electric car prices");

        List<String> carPrices = electricCarsPage.getElectricCarPrices();
        LogUtil.info("Fetching electric car EMI values");

        List<String> carEmis = electricCarsPage.getElectricCarEmiValues();
        Assert.assertFalse(carNames.isEmpty(), "Electric car names list is empty");
        Assert.assertFalse(carPrices.isEmpty(), "Electric car prices list is empty");
        Assert.assertFalse(carEmis.isEmpty(), "Electric car EMI list is empty");
        Assert.assertEquals(
                carNames.size(),
                carPrices.size(),
                "Mismatch between car names and prices count"
        );
        Assert.assertEquals(
                carNames.size(),
                carEmis.size(),
                "Mismatch between car names and EMI count"
        );
        LogUtil.info("Exporting electric car details to Excel");

        ExportVehicleDetails.writeElectricCarDetails(
                carNames,
                carPrices,
                carEmis
        );
        LogUtil.info("Electric car price integrity validated and data exported successfully");
    }
}