package Reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestScenarios extends BaseTest {

    @Test(priority = 1)
    public void loginWithValidUserDetails() {

        startTest("Login With Valid User Details", "Rohan Aher", "Smoke Test");

        try {
            //  Create Test steps
            extentTest.log(Status.INFO, "Step 1: Open a web browser.");

            extentTest.log(Status.INFO, "Step 2: Go to Swag Labs login page: https://www.saucedemo.com/");
            driver.navigate().to("https://www.saucedemo.com");

            extentTest.log(Status.INFO, "Step 3: Verify Swag Labs login page.");
            WebElement loginButton = driver.findElement(By.id("login-button"));

            extentTest.log(Status.INFO, "Step 4: Enter a valid username and password (e.g., standard_user / secret_sauce).");
            WebElement usernameField = driver.findElement(By.id("user-name"));
            WebElement passwordField = driver.findElement(By.id("password"));
            usernameField.sendKeys("standard_user");
            passwordField.sendKeys("secret_sauce");

            extentTest.log(Status.INFO, "Step 5: Click the 'Login' button.");
            loginButton.click();
            Assert.assertTrue(true); // Pass

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void loginWithInvalidUserName() {

        startTest("Login With Invalid User Name", "Vaibhav", "Sanity Test");

        try {
            //  Create Test steps
            extentTest.log(Status.INFO, "Step 1: Open a web browser.");

            extentTest.log(Status.INFO, "Step 2: Go to Swag Labs login page.");
            driver.navigate().to("https://www.saucedemo.com");

            extentTest.log(Status.INFO, "Step 3: Verify Swag Labs login page.");
            WebElement loginButton = driver.findElement(By.id("login-button"));
            Assert.assertTrue(false); // Expected failure

            extentTest.log(Status.INFO, "Step 4: Enter an invalid username (e.g., invalid_user) and a valid password.");
            WebElement usernameField = driver.findElement(By.id("user-name"));
            WebElement passwordField = driver.findElement(By.id("password"));
            usernameField.sendKeys("invalid_user");
            passwordField.sendKeys("secret_sauce");

            extentTest.log(Status.INFO, "Step 5: Click the 'Login' button.");
            loginButton.click();
            Assert.assertTrue(false); // Expected failure
            extentTest.warning("Please Enter All the Details.");

            // Capture screenshot and attach it to the Extent Report
            // extentTest.pass("Screenshot: LoginWithInvalidUserName", MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotAsBase64()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 3)
    public void loginWithInvalidPassword() {

        startTest("Login With Invalid Password", "Rohit Gaikwad", "Regression");

        try {
            //  Create Test steps
            extentTest.log(Status.INFO, "Step 1: Open a web browser.");

            extentTest.log(Status.INFO, "Step 2: Go to Swag Labs login page.");
            driver.navigate().to("https://www.saucedemo.com");

            extentTest.log(Status.INFO, "Step 3: Verify Swag Labs login page.");
            WebElement loginButton = driver.findElement(By.id("login-button"));
            Assert.assertTrue(false); // Expected failure

            extentTest.log(Status.INFO, "Step 4: Enter a valid username (e.g., standard_user) and an invalid password (e.g., invalid_password).");
            WebElement usernameField = driver.findElement(By.id("user-name"));
            WebElement passwordField = driver.findElement(By.id("password"));
            usernameField.sendKeys("standard_user");
            passwordField.sendKeys("invalid_password");

            extentTest.log(Status.INFO, "Step 5: Click the 'Login' button.");
            loginButton.click();
            Assert.assertTrue(false); // Expected failure
            extentTest.warning("Please Enter All the Details.");

            // Capture screenshot and attach it to the Extent Report
            //extentTest.pass("Screenshot: LoginWithInvalidPassword", MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotAsBase64()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 4)
    public void loginWithBlankUsernameAndPassword() {

        startTest("Login With Blank Username and Password", "Rohan Aher", "Regression");

        // Define a condition to skip the test
        boolean skipTest = true; // Set to true to skip, false to execute

        if (skipTest) {
            extentTest.log(Status.SKIP, "Test skipped as per request.");
            throw new SkipException("Skipping this test case with exception");
        } else {
            //  Create Test steps
            extentTest.log(Status.INFO, "Step 1: Open a web browser.");

            extentTest.log(Status.INFO, "Step 2: Go to Swag Labs login page.");
            driver.navigate().to("https://www.saucedemo.com");

            extentTest.log(Status.INFO, "Step 3: Verify Swag Labs login page.");
            WebElement loginButton = driver.findElement(By.id("login-button"));
            Assert.assertTrue(loginButton.isDisplayed()); // Verification

            extentTest.log(Status.INFO, "Step 4: Leave both username and password fields blank.");
        }
    }

    @Test(priority = 5)
    public void loginWithValidUsernameBlankPassword() {

        startTest("Login With Valid Username Blank Password", "Rohan Aher", "Smoke Test");

        try {
            //  Create Test steps
            extentTest.log(Status.INFO, "Step 1: Open a web browser.");

            extentTest.log(Status.INFO, "Step 2: Go to Swag Labs login page.");
            driver.navigate().to("https://www.saucedemo.com");

            extentTest.log(Status.INFO, "Step 3: Verify Swag Labs login page.");
            WebElement loginButton = driver.findElement(By.id("login-button"));
            Assert.assertTrue(loginButton.isDisplayed()); // Verification

            extentTest.log(Status.INFO, "Step 4: Enter a valid username (e.g., standard_user) and leave the password field blank.");
            WebElement usernameField = driver.findElement(By.id("user-name"));
            WebElement passwordField = driver.findElement(By.id("password"));
            usernameField.sendKeys("standard_user");

            extentTest.log(Status.INFO, "Step 5: Click the 'Login' button (even if disabled).");
            loginButton.click();
            Assert.assertTrue(false); // Expected failure
            extentTest.warning("Please Enter All the Details.");

            // Capture screenshot and attach it to the Extent Report
            //extentTest.pass("Screenshot: LoginWithValidUsernameBlankPassword", MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotAsBase64()).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO : We are failing this script to check null pointer exception in extent report.
    @Test(priority = 6)
    public void testNullPointerExcep(){
        startTest("Test null pointer exception.", "Rohan Aher", "Smoke Test");
        extentTest.log(Status.INFO, "Validate value should not null");
        String val = null;

        if(val.equals("Test")){
            extentTest.log(Status.PASS, "Value is Test");
        }
        else{
            extentTest.log(Status.FAIL, "Validate value is null");
        }
    }
    @Test(priority = 6)
    public void testArithmaticExcep(){
        startTest("test Arithmatic Exception.", "Rohan Aher", "Smoke Test");
        extentTest.log(Status.INFO, "take input value");
        int val = 10/0;
        System.out.println("value"+val);

    }
}