package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentSparkReporter spark;

    // Initialize Extent Report for each test case separately
    public static void startReport(String testName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); // Timestamp
        String reportPath = "./target/ExtentReport_" + testName + "_" + timeStamp + ".html"; // Separate report file

        spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Add system details
        extent.setSystemInfo("Tested By", "Abhiram S");
        extent.setSystemInfo("Test Name", testName);
        extent.setSystemInfo("Date", timeStamp);
    }

    // Create a new test entry in the report
    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    // Log test steps and results
    public static void log(Status status, String message) {
        if (test != null) {
            test.log(status, message);
        }
    }

    // End Report and flush data
    public static void endReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
