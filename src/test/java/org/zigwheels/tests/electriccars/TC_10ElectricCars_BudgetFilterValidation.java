package org.zigwheels.tests.electriccars;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.ElectricCarsPage;
import org.zigwheels.pages.HomePage;
import utilities.LogUtil;
import java.util.List;

public class TC_10ElectricCars_BudgetFilterValidation extends BaseTest {

    @Test
    public void verifyCarsUnder20Lakh() {
        LogUtil.info("Opening Electric Cars page");

        new HomePage(driver).openElectricCars();
        ElectricCarsPage electricCarsPage = new ElectricCarsPage(driver);
        LogUtil.info("Selecting Under 20 Lakh budget filter");

        electricCarsPage.selectUnder20LakhBudget();
        LogUtil.info("Fetching car prices after applying filter");

        List<String> prices = electricCarsPage.getElectricCarPricesUnder20Lakhs();
        Assert.assertFalse(prices.isEmpty(), "No cars displayed after applying budget filter");
        for (String price : prices) {
            double priceValue = extractPriceInLakhs(price);
            Assert.assertTrue(
                    priceValue <= 20,
                    "Car price exceeds 20 Lakh: " + price
            );
        }
        LogUtil.info("All displayed cars are under 20 Lakh");
    }
    private double extractPriceInLakhs(String priceText) {
        return Double.parseDouble(
                priceText.replace("₹", "")
                        .replace("Lakh", "")
                        .trim()
        );
    }
}