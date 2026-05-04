package org.zigwheels.tests.electriccars;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.ElectricCarsPage;
import org.zigwheels.pages.HomePage;
import utilities.LogUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TC_8ElectricCars_DuplicateNameValidation extends BaseTest {

    @Test
    public void verifyNoDuplicateElectricCarNames() {
        LogUtil.info("Loading Electric Cars page");

        HomePage homePage = new HomePage(driver);
        homePage.openElectricCars();
        ElectricCarsPage electricCarsPage = new ElectricCarsPage(driver);
        LogUtil.info("Fetching electric car names");

        List<String> carNames = electricCarsPage.getElectricCarNames();
        Assert.assertFalse(
                carNames.isEmpty(),
                "Electric car names list is empty"
        );
        LogUtil.info("Validating duplicate electric car names");

        Set<String> uniqueCarNames = new HashSet<>(carNames);
        Assert.assertEquals(
                uniqueCarNames.size(),
                carNames.size(),
                "Duplicate electric car names found on Electric Cars page"
        );
        LogUtil.info("No duplicate electric car names found");
    }
}