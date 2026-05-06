package utilities;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> childTest = new ThreadLocal<>();

    private synchronized ExtentReports getExtentInstance() {

        if (extent == null) {

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
            extent.setSystemInfo("Execution Type", "Regression Suite");

            extent.setSystemInfo("Team",
                    "Syed Suhail Mohiddin, Dinkar Dongre, Prateek Mahajan, Priyadarshini Panda, Harshit Sinotiya");
        }

        return extent;
    }

    @Override
    public void onStart(ITestContext context) {

        ExtentReports ext = getExtentInstance();

        // ✅ Create Parent Node (Module Level)
        ExtentTest parent = ext.createTest(context.getName());

        parentTest.set(parent);
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest child = parentTest.get()
                .createNode(result.getMethod().getMethodName());

        childTest.set(child);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        childTest.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        childTest.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        childTest.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        getExtentInstance().flush();
    }
}