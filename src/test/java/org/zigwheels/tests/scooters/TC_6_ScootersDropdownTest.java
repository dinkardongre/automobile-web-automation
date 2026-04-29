package org.zigwheels.tests.scooters;
import basetest.BaseTest;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.SearchScooters;
import utilities.LogUtil;

public class TC_6_ScootersDropdownTest extends BaseTest {

    @Test
    public void testDropdownVisibility() {
        SearchScooters scootersPage = new SearchScooters(driver);
        Actions actions = new Actions(driver);
        actions.moveToElement(scootersPage.getScootersOptions()).perform();

        Assert.assertTrue(scootersPage.getSearchNewScooters().isDisplayed(),
                "Search New Scooters option is not visible");
        LogUtil.info("Search New Scooters option is visible");

        Assert.assertTrue(scootersPage.getElectricScooters().isDisplayed(),
                "Electric Scooters option is not visible");
        LogUtil.info("Electric Scooters option is visible");

        System.out.println("Validation successful: Both dropdown options are displayed");
    }
}
