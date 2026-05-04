package org.zigwheels.tests.scooters;

import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.SearchScooters;
import utilities.ExportScooterNames;
import utilities.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class TC_14ValidateTVSBrandScootersTest extends BaseTest {

    @Test
    public void validateAndExportTVSScooters() throws Exception {

        HomePage homePage = new HomePage(driver);
        SearchScooters scootersPage = new SearchScooters(driver);

        LogUtil.info("Navigating to Search New Scooters");

        homePage.hoverOnScootersMenu();
        homePage.clickSearchNewScooters();

        Assert.assertTrue(driver.getCurrentUrl().contains("scooters"),
                "Not navigated to Search New Scooters page");

        LogUtil.info("Selecting TVS brand");
        scootersPage.clickTVSBrand();

        List<WebElement> scooterElements = scootersPage.getScooterNames();
        Assert.assertTrue(scooterElements.size() > 0, "No TVS scooters listed");

        List<String> scooterNames = new ArrayList<>();

        for (WebElement scooter : scooterElements) {
            String name = scooter.getText();
            LogUtil.info("Scooter found: " + name);

            Assert.assertTrue(name.toLowerCase().contains("tvs"),
                    "Non‑TVS scooter found: " + name);

            scooterNames.add(name);
        }

        ExportScooterNames.writeScooterNames(scooterNames);

        LogUtil.info("TVS scooter names exported to Excel successfully");
    }
}