package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class BaseTest {

    ExtentSparkReporter extentSparkReporter;
    protected ExtentReports extentReports;
    protected ExtentTest extentTest;
    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        // Initialize Extent Reports
        extentSparkReporter = new ExtentSparkReporter("./ExtentReportDemo.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);

        // Set System Information
        extentReports.setSystemInfo("Machine", "testpc1");
        extentReports.setSystemInfo("Os", "Windows10");
        extentReports.setSystemInfo("User", "Admin");
        extentReports.setSystemInfo("Browser", "Chrome");

        // Configure Report Settings
        extentSparkReporter.config().setDocumentTitle("Extent Report Demo");
        extentSparkReporter.config().setReportName("Test_Report");
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setTimeStampFormat("EEEE, MM dd, yyyy, hh:mm a (zzzz)");

        driver = new ChromeDriver();
    }

    @AfterMethod
    public void getTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Log test failure and attach a screenshot
            extentTest.log(Status.FAIL, "Test failed: " + result.getName());
            extentTest.log(Status.FAIL,"Test failed",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotAsBase64()).build());
            //ColorLabel
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Fail", ExtentColor.RED));

            extentTest.fail(result.getThrowable());



        } else if (result.getStatus() == ITestResult.SUCCESS) {
            // Log test failure and attach a screenshot
            extentTest.log(Status.PASS, "Tested Pass: " + result.getName());
            extentTest.log(Status.PASS,"Test Pass",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotAsBase64()).build());

            //ColorLabel
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Pass", ExtentColor.GREEN));
        } else if (result.getStatus() == ITestResult.SKIP) {
            // Log test skip and attach a screenshot
            extentTest.log(Status.SKIP, "Test skipped: " + result.getName());
            extentTest.log(Status.SKIP,"Test Skipped",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotAsBase64()).build());
            //ColorLabel
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Skip", ExtentColor.YELLOW));
        }
    }


    public void startTest(String testName, String author, String category) {
        // Create a new test in the Extent Report
        extentTest = extentReports.createTest(testName)
                .assignAuthor(author)
                .assignCategory(category)
                .assignDevice("Chrome");
    }

    public String getScreenshotAsBase64() {
        String imagePath ="";
        try {
            // Capture screenshot
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Convert the screenshot to Base64
            FileInputStream fileInputStream = new FileInputStream(screenshotFile);
            byte[] bytes = IOUtils.toByteArray(fileInputStream);
            imagePath = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  imagePath;
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        extentReports.flush();
    }
}
