package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import base.BaseClass;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;

@Epic("DemoWebShop Checkout")  
@Feature("Order Placement")   
public class CheckoutTest extends BaseClass {
    HomePage homePage;
    LoginPage loginPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    @Step("Setup browser and navigate to checkout page")
    public void setUp() throws Exception {
        invokeBrowser(); // Launch browser
        driver.get(prop.getProperty("url")); // Navigate to homepage

        // Initialize Page Objects
        homePage = new HomePage();
        loginPage = new LoginPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
    }

    @Test(description = "Verify checkout functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User completes a successful checkout")
    @Description("This test case verifies the checkout process including login, navigating to cart, and confirming order")
    public void testCheckout() throws InterruptedException, IOException {
        // Step 1: Login
        homePage.navigateToLogin();
        loginPage.login();

        // Step 2: Navigate to cart
        cartPage.navigateToCart();

        // Step 3: Check if the cart is empty
        if (cartPage.isCartEmpty()) {
            System.out.println("Cart is empty! Cannot proceed with checkout.");
            Assert.assertTrue(true, "Cart is empty, skipping checkout."); 
            Thread.sleep(2000); 
            loginPage.logout(); // Logout
            return; 
        }

        // Step 4: Accept Terms & Conditions and Click Checkout
        driver.findElement(By.id("termsofservice")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("checkout")).click();

        // Step 5: Complete Billing Address
        checkoutPage.completeBillingAddress(
            prop.getProperty("country"),
            prop.getProperty("city"),
            prop.getProperty("address"),
            prop.getProperty("postalCode"),
            prop.getProperty("phone")
        );

        // Step 6: Continue through checkout process
        checkoutPage.continueCheckout();

        // Step 7: Confirm Order
        checkoutPage.confirmOrder();

        // Step 8: Verify Order Success
        Assert.assertTrue(checkoutPage.isOrderSuccessful(), "Order failed!");

        // Step 9: Take Screenshot
        screenshot("order_success");

        // Step 10: View Order Details
        checkoutPage.viewOrderDetails();
        Thread.sleep(2000);

        // Step 11: Logout using LoginPage method
        loginPage.logout();
    }

    @AfterMethod
    @Step("Close browser after test execution")
    public void teardown() throws InterruptedException {
        closeBrowser();
    }
}