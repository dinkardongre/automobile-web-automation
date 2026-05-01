package org.zigwheels.tests.cng;

import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CngCarsPage;
import org.zigwheels.pages.HomePage;
import utilities.LogUtil;

import java.util.List;

public class TC_3CngCarsEmiRangeTest extends BaseTest {

    @Test
    public void verifyEmiRange() {
        HomePage homePage = new HomePage(driver);
        homePage.clickCngSection();

        CngCarsPage cngPage = new CngCarsPage(driver);
        cngPage.clickViewMore();

        List<WebElement> emiCars = cngPage.getEmiValues();

        Assert.assertFalse(emiCars.isEmpty(), "EMI list should not be empty");
        LogUtil.info("Collected " + emiCars.size() + " EMI values");

        int emiInRange = 0;
        for (WebElement emi : emiCars) {
            String emiText = emi.getText().trim();
            if (emiText.isEmpty()) continue;

            emiText = emiText.replace("EMI :", "")
                    .replace("₹", "")
                    .replace(",", "")
                    .trim();

            try {
                int emiValue = Integer.parseInt(emiText);
                if (emiValue >= 8000 && emiValue <= 15000) {
                    emiInRange++;
                    LogUtil.info("Car EMI in range: ₹" + emiValue);
                }
            } catch (NumberFormatException e) {
                LogUtil.warn("Skipped non-numeric EMI after cleanup: " + emiText);
            }
        }
        LogUtil.info("Total cars with EMI between 8000 and 15000: " + emiInRange);
        Assert.assertTrue(emiInRange > 0, "At least one car should have EMI between 8000–15000");
    }
}