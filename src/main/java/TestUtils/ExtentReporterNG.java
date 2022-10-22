package TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    static ExtentReports extentReports;

    public static ExtentReports getReporterObject(){
//      ExtentReports , ExtentSparkReporter
        String reportPath = System.getProperty("user.dir")+"//Reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Web automation with appium reports");
        reporter.config().setDocumentTitle("Test Results");
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter); // attach the report to the report we created above
        extentReports.setSystemInfo("Tester Name","Mazen Ahmed");
        return extentReports;
    }
}
