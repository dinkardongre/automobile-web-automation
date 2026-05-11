package utilities;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(
                            System.getProperty("user.dir") + "/reports/ExtentReport.html"
                    );

            spark.config().setDocumentTitle("ZigWheels Report");
            spark.config().setReportName("Automation Execution");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Project", "ZigWheels");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Team",
                    "Syed Suhail Mohiddin, Dinkar Dongre, Prateek Mahajan, Priyadarshini Panda, Harshit Sinotiya");
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(
                result.getTestClass().getName() + " :: " +
                        result.getMethod().getMethodName()
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}