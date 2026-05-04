package listeners;
import basetest.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.LogUtil;
import utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        if (currentClass instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) currentClass;
            try {
                ScreenshotUtil.captureScreenshot(
                        baseTest.driver,
                        result.getName()
                );
                LogUtil.error(
                        "Screenshot captured for failed test: " + result.getName()
                );
            } catch (Exception e) {
                LogUtil.error("Failed to capture screenshot for: " + result.getName());
            }
        }
    }
}
