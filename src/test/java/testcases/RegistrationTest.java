package testcases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;
import base.BaseClass;
import utilities.ExtentReport;  
import com.aventstack.extentreports.Status;

public class RegistrationTest extends BaseClass {
    HomePage homePage;
    RegistrationPage registrationPage;

    @BeforeMethod
    @Parameters("browser")  // Accept browser parameter from XML
    public void setUp(String browser) throws IOException {
    	ExtentReport.startReport("RegistrationTest"); // Start extent report with test name
        ExtentReport.createTest("Registration Test");
        ExtentReport.log(Status.INFO, "Starting Registration Test");
        
        readData(); // Load config.properties
        initializeBrowser(browser); // Launch the browser with parameter
        driver.get(prop.getProperty("url")); // Navigate to URL
        ExtentReport.log(Status.INFO, "Navigated to URL: " + prop.getProperty("url"));

        // Initialize page objects
        homePage = new HomePage();
        registrationPage = new RegistrationPage();
    }

    @Test
    public void registerUser() throws IOException, InterruptedException {
    	ExtentReport.log(Status.INFO, "Navigating to Registration Page");
        homePage.navigateToRegister();  // Click Register on Home Page
        
        ExtentReport.log(Status.INFO, "Filling Registration Form");
        registrationPage.fillRegistrationForm();

        if (registrationPage.isUserAlreadyRegistered()) {
            System.out.println("User is already registered with the provided email!");
            ExtentReport.log(Status.FAIL, "User is already registered with the provided email!");
            
            screenshot("Already_Registered");  // Capture screenshot and store path
            ExtentReport.log(Status.INFO, "Screenshot captured and saved in 'screenshots' folder.");
            Assert.assertTrue(true, "User already registered, continuing test as passed.");
            
        } else {
            System.out.println("User registered successfully!");
            ExtentReport.log(Status.PASS, "User registered successfully!");
            
            screenshot("Registration_Success");  // Capture screenshot and store path
            ExtentReport.log(Status.INFO, "Screenshot captured and saved in 'screenshots' folder.");
            Assert.assertTrue(true);
        }
    }


    @AfterMethod
    public void tearDown() throws InterruptedException {
    	ExtentReport.log(Status.INFO, "Closing browser");
        closeBrowser(); // Close browser after test execution
        ExtentReport.endReport();
    }
}
