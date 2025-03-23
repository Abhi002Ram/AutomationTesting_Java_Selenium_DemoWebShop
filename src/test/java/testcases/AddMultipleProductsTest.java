package testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import pages.CategoryPage;
import pages.HomePage;
import pages.LoginPage;
import pages.CartPage;
import utilities.ExtentReport;
import base.BaseClass;

public class AddMultipleProductsTest extends BaseClass {
    HomePage homePage;
    LoginPage loginPage;
    CartPage cartPage;
    CategoryPage categoryPage;

    @BeforeMethod
    public void setUp() throws Exception {
        ExtentReport.startReport("AddMultipleProductsTest"); // Start extent report
        ExtentReport.createTest("Add Multiple Products Test"); // Create test entry
        ExtentReport.log(Status.INFO, "Starting Add Multiple Products Test");
        invokeBrowser(); // Launch browser
        driver.get(prop.getProperty("url")); // Navigate to homepage

        // Initialize Page Objects
        homePage = new HomePage();
        loginPage = new LoginPage();
        cartPage = new CartPage();
        categoryPage = new CategoryPage(driver);
    }

    @Test
    public void testAddingMultipleProducts() throws InterruptedException {
    	ExtentReport.log(Status.INFO, "Navigating to Login Page");
        homePage.navigateToLogin();
        loginPage.login();

        // Navigate to Category Page and select Computers
        ExtentReport.log(Status.INFO, "Navigating to Computers category");
        categoryPage.selectComputers();
        Thread.sleep(1000);
        
        // Add a product from Desktops
        ExtentReport.log(Status.INFO, "Adding product from Desktops");
        categoryPage.selectDesktops();
        Thread.sleep(1000);
        addProductToCart("Build your own cheap computer");

        // Add a product from Notebooks
        ExtentReport.log(Status.INFO, "Adding product from Notebooks");
        categoryPage.selectNotebooks();
        Thread.sleep(1000);
        addProductToCart("14.1-inch Laptop");

        // Add a product from Accessories
        ExtentReport.log(Status.INFO, "Adding product from Accessories");
        categoryPage.selectAccessories();
        Thread.sleep(1000);
        addProductToCart("TCP Instructor Led Training");
        
        // Navigate to the cart page and verify the products are present
        ExtentReport.log(Status.INFO, "Navigating to cart page");
        cartPage.navigateToCart();
                
        Assert.assertTrue(cartPage.isProductInCart("Build your own cheap computer"), "Product 'Build your own cheap computer' is NOT in the cart!");
        Assert.assertTrue(cartPage.isProductInCart("14.1-inch Laptop"), "Product '14.1-inch Laptop' is NOT in the cart!");
        Assert.assertTrue(cartPage.isProductInCart("TCP Instructor Led Training"), "Product 'TCP Instructor Led Training' is NOT in the cart!");

        ExtentReport.log(Status.PASS, "All added products successfully verified in the cart");
        System.out.println("Multiple products added successfully and verified in the cart.");
    }
    
    private void addProductToCart(String productName) throws InterruptedException {
        driver.findElement(By.linkText(productName)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Add to cart']")).click();
        Thread.sleep(1000);

        // Using Fluent Wait to wait for the success message
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10)) // Maximum wait time
                .pollingEvery(Duration.ofSeconds(1)) // Polling interval
                .ignoring(Exception.class); // Ignore exceptions during polling

        // Wait until the success message is visible
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("content")));

        System.out.println("Success Message: " + successMessage.getText());
        ExtentReport.log(Status.INFO, "Success Message: " + successMessage.getText());

        // Navigate back to the category page
        driver.navigate().back();
        Thread.sleep(1000);
        driver.navigate().back();
        Thread.sleep(1000);
    }
    
    @AfterMethod
    public void teardown() throws InterruptedException {   	
    	ExtentReport.log(Status.INFO, "Closing browser");
        closeBrowser();        
        ExtentReport.endReport(); // End report
    }

}
