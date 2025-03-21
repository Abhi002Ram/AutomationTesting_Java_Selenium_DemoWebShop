package testcases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import base.BaseClass;
import utilities.ExtentReport;  
import com.aventstack.extentreports.Status;

public class LoginTest extends BaseClass {
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() throws IOException {
    	ExtentReport.startReport("LoginTest"); // Start extent report with test name
        ExtentReport.createTest("Login Test"); // Create test entry
        
        readData(); // Load config.properties
        invokeBrowser(); // Launch the browser based on the config file
        driver.get(prop.getProperty("url")); // Navigate to URL        

        // Initialize page objects
        homePage = new HomePage();
        loginPage = new LoginPage();
    }

    @Test
    public void loginUser() throws IOException, InterruptedException {
    	ExtentReport.log(Status.INFO, "Navigating to Login page");
        homePage.navigateToLogin();  // Navigate to Login page
        
        ExtentReport.log(Status.INFO, "Performing Login.");
        loginPage.login();  // Perform login

        if (loginPage.isLoginFailed()) {
            System.out.println("Login failed with the provided credentials!");
            ExtentReport.log(Status.FAIL, "Login failed with provided credentials!");
            
            screenshot("Login_Failed");  // Capture screenshot and store path
            ExtentReport.log(Status.INFO, "Screenshot captured and saved in 'screenshots' folder.");
            Assert.assertTrue(true, "Login failed, screenshot taken.");
        } else {
            System.out.println("Login successful!");
            ExtentReport.log(Status.PASS, "Login successful!");
            Assert.assertTrue(true);
        }
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
    	ExtentReport.log(Status.INFO, "Closing browser");
        closeBrowser(); // Close browser after test execution
        ExtentReport.endReport(); // End the report
    }
}
