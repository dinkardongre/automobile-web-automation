package org.zigwheels.tests.scooters;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.SearchScooters;
import utilities.LogUtil;

public class TC_7_SequentialScootersNavigationTest extends BaseTest {
    private SearchScooters scootersPage;

    @Test
    public void testSequentialNavigationScootersOptions() {
        LogUtil.info("Starting test: Validate sequential navigation for Scooters dropdown options");
        scootersPage = new SearchScooters(driver);
        Actions actions = new Actions(driver);

        // Navigate to Search New Scooters
        actions.moveToElement(scootersPage.getScootersOptions()).perform();
        LogUtil.info("Hovered over Scooters menu");

        scootersPage.clickSearchNewScooters();
        LogUtil.info("Clicked on Search New Scooters option");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("scooters"),
                "Failed to navigate to Search New Scooters. Current URL: " + currentUrl);

        WebElement heading1 = driver.findElement(By.xpath("//h1[contains(text(),'Scooters')]"));
        Assert.assertTrue(heading1.isDisplayed(), "Scooters page heading not displayed");

        driver.navigate().back();
        LogUtil.info("Navigated back to home page");

        // Navigate to Electric Scooters
        actions.moveToElement(scootersPage.getScootersOptions()).perform();
        LogUtil.info("Hovered over Scooters menu again");

        scootersPage.clickElectricScooters();
        LogUtil.info("Clicked on Electric Scooters option");

        currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("electric-scooters"),
                "Failed to navigate to Electric Scooters. Current URL: " + currentUrl);

        WebElement heading2 = driver.findElement(By.xpath("//h1[contains(text(),'Electric Scooters')]"));
        Assert.assertTrue(heading2.isDisplayed(), "Electric Scooters page heading not displayed");

        LogUtil.info("Validation successful: Sequential navigation for both dropdown options passed");
    }
}
