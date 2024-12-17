//package ExtentReporter;
//
//import java.io.File;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import org.testng.*;
//import org.testng.xml.XmlSuite;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
//
//public class ExtentSetup implements ITestNGListener {
//    private ExtentReports extent;
//    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
//                               String outputDirectory) {
//        extent = new ExtentReports(outputDirectory + File.separator
//                + "Extent.html", true);
//
//        for (ISuite suite : suites) {
//            Map<String, ISuiteResult> result = suite.getResults();
//
//            for (ISuiteResult r : result.values()) {
//                ITestContext context = r.getTestContext();
//
//                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
//                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
//                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
//            }
//        }
//
//        extent.flush();
//        extent.close();
//    }
//
//    private void buildTestNodes(IResultMap tests, LogStatus status) {
//        ExtentTest test;
//
//        if (tests.size() > 0) {
//            for (ITestResult result : tests.getAllResults()) {
//                test = extent.startTest(result.getMethod().getMethodName());
//
//                test.setStartedTime(getTime(result.getStartMillis()));
//                test.setEndedTime(getTime(result.getEndMillis()));
//
//                for (String group : result.getMethod().getGroups())
//                    test.assignCategory(group);
//
//                if (result.getThrowable() != null) {
//                    test.log(status, result.getThrowable());
//                } else {
//                    test.log(status, "Test " + status.toString().toLowerCase()
//                            + "ed");
//                }
//
//                extent.endTest(test);
//            }
//        }
//    }
//
//    private Date getTime(long millis) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(millis);
//        return calendar.getTime();
//    }
//
//}
package ExtentReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.commonFunctions;

import java.io.IOException;

public class ExtentSetup implements ITestListener {
    private ExtentReports extent;
    private ExtentTest test;


    @Override
    public void onStart(ITestContext context) {
        // Use ExtentSparkReporter instead of ExtentHtmlReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
                "src//Reports//extent-report_" + commonFunctions.getCurrentDateTime() + ".html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.fail(result.getThrowable()); // Log the exception/stack trace

        Object testInstance = result.getInstance(); // Get the test instance
        if (testInstance instanceof commonFunctions) {
            commonFunctions commonftns = (commonFunctions) testInstance;

            try {
                String screenshotPath = commonftns.takeScreenshot(result.getName());
                test.fail("Screenshot on failure:",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (Exception e) {
                e.printStackTrace();
                test.fail("Failed to attach screenshot due to exception.");
            }
        } else {
            test.fail("Could not take screenshot as test instance does not match.");
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Writes the report to the file
    }
}
