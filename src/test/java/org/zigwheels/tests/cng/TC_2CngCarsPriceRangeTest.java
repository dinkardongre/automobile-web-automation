package org.zigwheels.tests.cng;

import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CngCarsPage;
import org.zigwheels.pages.HomePage;
import utilities.LogUtil;

import java.util.List;

public class TC_2CngCarsPriceRangeTest extends BaseTest {

    @Test
    public void verifyCarsInPriceRange() {
        HomePage homePage = new HomePage(driver);
        homePage.clickCngSection();

        CngCarsPage cngPage = new CngCarsPage(driver);
        cngPage.clickViewMore();

        List<WebElement> carNames = cngPage.getCarNames();
        List<WebElement> carPrices = cngPage.getCarPrices();

        int carsInRange = 0;
        for (int i = 0; i < carNames.size(); i++) {
            String name = carNames.get(i).getText().trim();
            String priceText = carPrices.get(i).getText().trim();

            if (name.isEmpty() || priceText.isEmpty()) continue;

            priceText = priceText.replace("₹", "").replace("Lakh", "").trim();

            try {
                double price = Double.parseDouble(priceText);
                if (price >= 5 && price <= 15) {
                    carsInRange++;
                    LogUtil.info("Car in price range: " + name + " - " + price + " Lakh");
                }
            } catch (NumberFormatException e) {
                LogUtil.warn("Skipped non-numeric price for: " + name + " (" + priceText + ")");
            }
        }
        Assert.assertTrue(carsInRange > 0, "At least one car should be in 5–15 Lakh range");
    }
}