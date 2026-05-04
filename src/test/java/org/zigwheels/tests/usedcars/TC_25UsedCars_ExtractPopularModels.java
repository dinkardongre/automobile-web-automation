package org.zigwheels.tests.usedcars;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UsedCarsPage;
import utilities.ExportVehicleDetails;
import utilities.LogUtil;
import java.io.IOException;
import java.util.List;

public class TC_25UsedCars_ExtractPopularModels extends BaseTest {
    @Test
    public void extractAndStorePopularModels() throws IOException {

        LogUtil.info("Test Started: Extract and Store popular model");

        HomePage homePage = new HomePage(driver);
        LogUtil.info("Opening Used Cars page");

        homePage.openUsedCarsPage();

        UsedCarsPage usedCarsPage = new UsedCarsPage(driver);
        LogUtil.info("Selecting Chennai city");

        usedCarsPage.selectChennaiCity();

        LogUtil.info("Extracting Popular Models");

        List<String> popularModels =
                usedCarsPage.getPopularModelsList();
        Assert.assertTrue(
                popularModels.size() > 0,
                "No popular models found"
        );
        LogUtil.info("Writing Popular Models to Excel");

        ExportVehicleDetails
                .writePopularModelsToExcel(popularModels);
        LogUtil.info("Popular Models extracted and saved successfully");

        LogUtil.info("Test Finished Successfully");

    }
}

