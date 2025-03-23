package testcases;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductSearchPage;
import utilities.ExtentReport;
import com.aventstack.extentreports.Status;

import java.io.IOException;

public class ProductSearchTest extends BaseClass {
    HomePage homePage;
    LoginPage loginPage;
    CartPage cartPage;
    ProductSearchPage productSearchPage;

    @BeforeMethod
    public void setup() throws IOException {
    	ExtentReport.startReport("ProductSearchTest"); // Start extent report with test name
        ExtentReport.createTest("Product Search Test"); // Create test entry
        ExtentReport.log(Status.INFO, "Starting Product Search Test");
        
        invokeBrowser(); // Launch browser
        driver.get(prop.getProperty("url")); // Navigate to homepage

        // Initialize page objects
        homePage = new HomePage();
        loginPage = new LoginPage();
        cartPage = new CartPage();
        productSearchPage = new ProductSearchPage(driver);
    }

    @Test
    public void testProductSearchAndAddToCart() throws InterruptedException, IOException {
    	// Navigate to login page and perform login
    	ExtentReport.log(Status.INFO, "Navigating to Login Page");
    	homePage.navigateToLogin(); 
        loginPage.login(); 

        // Search for Simple Computer
        ExtentReport.log(Status.INFO, "Searching for product: Simple Computer");
        productSearchPage.searchProduct("Simple Computer");

        // Verify product list is displayed
        Assert.assertTrue(productSearchPage.isProductListDisplayed(), "No products displayed in search results");
        ExtentReport.log(Status.PASS, "Product list displayed successfully");

        // Select "Simple Computer"
        ExtentReport.log(Status.INFO, "Selecting product: Simple Computer");
        productSearchPage.selectSimpleComputer();

        // Add to Cart
        ExtentReport.log(Status.INFO, "Adding product to cart");
        productSearchPage.addToCart();
        
        // Navigate to the cart page and verify the product is present
        ExtentReport.log(Status.INFO, "Navigating to cart page");
        cartPage.navigateToCart();
        
        Assert.assertTrue(cartPage.isProductInCart("Simple Computer"), "Product is NOT in the cart!");
        ExtentReport.log(Status.PASS, "Product successfully added to cart");
        
        /// Take Screenshot after adding product to cart
        screenshot("ProductAddedToCart");
        ExtentReport.log(Status.INFO, "Screenshot captured and saved in 'screenshots' folder.");
        
        System.out.println("Product successfully added to cart and verified!");
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
    	ExtentReport.log(Status.INFO, "Closing browser");
        closeBrowser();
        ExtentReport.endReport(); // End report
    }
}

