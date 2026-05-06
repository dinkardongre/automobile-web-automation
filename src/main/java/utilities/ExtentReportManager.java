package utilities;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        ExtentSparkReporter spark =
                new ExtentSparkReporter(
                        System.getProperty("user.dir") + "/reports/ExtentReport.html"
                );

        spark.config().setDocumentTitle("ZigWheels Automation Report");
        spark.config().setReportName("Full Regression Suite");
        spark.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Project", "ZigWheels Automation");
        extent.setSystemInfo("Environment", "QA");

        extent.setSystemInfo("Team Member 1", "Syed Suhail Mohiddin");
        extent.setSystemInfo("Team Member 2", "Dinkar Dongre");
        extent.setSystemInfo("Team Member 3", "Prateek Mahajan");
        extent.setSystemInfo("Team Member 4", "Priyadarshini Panda");
        extent.setSystemInfo("Team Member 5", "Harshit Sinotiya");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(
                result.getTestClass().getName() + " :: " +
                        result.getMethod().getMethodName()
        );
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}