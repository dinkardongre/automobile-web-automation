package org.zigwheels.tests.scooters;

import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.SearchScooters;
import utilities.LogUtil;
import utilities.ScreenshotUtil;
import java.io.IOException;
import java.util.List;

public class TC_13ValidateScootersUnder40000PriceFilter extends BaseTest {

    @Test
    public void validateScootersUnder40000PriceFilter() throws IOException {

        HomePage homePage = new HomePage(driver);
        SearchScooters scootersPage = new SearchScooters(driver);

        LogUtil.info("Navigating to Search New Scooters");

        homePage.hoverOnScootersMenu();
        homePage.clickSearchNewScooters();

        Assert.assertTrue(driver.getCurrentUrl().contains("scooters"),
                "Not navigated to Search New Scooters page");

        LogUtil.info("Applying price filter: Scooters under 40000");

        scootersPage.clickScootersUnder40000Filter();
        ScreenshotUtil.captureScreenshot(driver,"ScootersUnder40000_Loaded");
        List<WebElement> prices = scootersPage.getAllScooterPrices();
        Assert.assertTrue(prices.size() > 0, "No scooters displayed");

        for (WebElement priceElement : prices) {
            int price = Integer.parseInt(
                    priceElement.getText().replaceAll("[^0-9]", "")
            );

            Assert.assertTrue(price <= 40000,
                    "Scooter price exceeds 40000: Rs. " + price);
        }

        LogUtil.info("Price filter validated successfully");
    }
}