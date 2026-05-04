package org.zigwheels.tests.usedcars;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.UsedCarsPage;
import utilities.LogUtil;

public class TC_22UsedCars_ValidateScrollAndSearch extends BaseTest {
    @Test
    public void validateSearchButtonAfterScroll() {

        LogUtil.info("Test Started: Validate Scroll And Search");

        HomePage homePage = new HomePage(driver);
        LogUtil.info("Opening Used Cars page");

        homePage.openUsedCarsPage();
        LogUtil.info("Selecting Chennai city");

        UsedCarsPage usedCarsPage = new UsedCarsPage(driver);
        usedCarsPage.selectChennaiCity();
        LogUtil.info("Scrolling to search section");

        usedCarsPage.scrollToSearchButton();
        LogUtil.info("Validating autocomplete suggestions are displayed");

        Assert.assertTrue(
                usedCarsPage.areAutoCompleteSuggestionsDisplayed("Hyundai"),
                    "Autocomplete suggestions are NOT displayed"
            );
        LogUtil.info("Autocomplete suggestions displayed successfully");

        LogUtil.info("Test Finished Successfully");

        }
    }



