package org.zigwheels.tests.cng;

import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CngCarsPage;
import org.zigwheels.pages.HomePage;
import utilities.ExportVehicleDetails;
import utilities.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class TC_5CngCarRatingGreater4 extends BaseTest {

    @Test
    public void verifyCngCarsRatingGreaterThan4() throws Exception {

        LogUtil.info("===== Test Started: Verify CNG Cars Rating > 4 =====");

        HomePage homePage = new HomePage(driver);
        homePage.clickCngSection();
        LogUtil.info("Navigated to CNG Cars section");

        CngCarsPage cngPage = new CngCarsPage(driver);
        cngPage.clickViewMore();
        LogUtil.info("Clicked View More on CNG Cars");

        List<WebElement> carNameElements = cngPage.getCarNames();
        List<WebElement> ratingElements  = cngPage.getCarRatings();

        List<String> filteredCarNames = new ArrayList<>();
        List<Double> filteredRatings  = new ArrayList<>();

        LogUtil.info("Filtering cars with rating > 4");

        for (int i = 0; i < ratingElements.size(); i++) {
            String ratingText = ratingElements.get(i).getText().trim();
            String carName = carNameElements.get(i).getText().trim();

            if (ratingText.isEmpty()) {
                // silently skip missing ratings (no log, no export)
                continue;
            }

            try {
                double ratingValue = Double.parseDouble(ratingText);

                Assert.assertTrue(ratingValue > 0,
                        "Invalid rating found for car: " + carName);

                if (ratingValue > 4.0) {
                    LogUtil.info("Valid Car: " + carName + " | Rating: " + ratingValue);
                    filteredCarNames.add(carName);
                    filteredRatings.add(ratingValue);
                } else {
                    LogUtil.info("Car skipped, rating <= 4: " + carName + " | Rating: " + ratingValue);
                }
            } catch (NumberFormatException e) {
                // silently skip invalid formats too
                continue;
            }
        }


        Assert.assertFalse(filteredCarNames.isEmpty(),
                "No CNG car found with rating greater than 4");

        ExportVehicleDetails.writeCngCarRatingDetails(
                filteredCarNames,
                filteredRatings
        );

        LogUtil.info("Excel exported successfully for CNG cars with rating > 4");
        LogUtil.info("===== Test Finished Successfully =====");
    }
}
