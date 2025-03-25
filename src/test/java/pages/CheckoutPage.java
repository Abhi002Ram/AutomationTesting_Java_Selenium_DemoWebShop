package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;

public class CheckoutPage {
	WebDriverWait wait = new WebDriverWait(BaseClass.driver, Duration.ofSeconds(10));
	
    // Locators
    By cartItems = By.xpath("//table[@class='cart']//tr");
    By billingAddressDropdown = By.id("billing-address-select");
    By billingContinueButton = By.xpath("//input[@title='Continue']");
    By shippingContinueButton = By.xpath("//input[@title='Continue' and contains(@onclick, 'Shipping.save')]");
    By shippingMethodContinueButton = By.xpath("//input[contains(@class, 'shipping-method-next-step-button')]");
    By paymentMethodContinueButton = By.xpath("//input[contains(@class, 'payment-method-next-step-button')]");
    By paymentInfoContinueButton = By.xpath("//input[contains(@class, 'payment-info-next-step-button')]");
    By confirmOrderButton = By.xpath("//input[@value='Confirm']");
    By orderSuccessMessage = By.xpath("//strong[contains(text(), 'Your order has been successfully processed!')]");
    By orderDetailsLink = By.xpath("//a[contains(text(),'Click here for order details')]");


    // Complete Billing Address
    public void completeBillingAddress(String country, String city, String address, String postalCode, String phone) throws InterruptedException {
        // Check if a saved billing address exists
        List<WebElement> addressDropdown = BaseClass.driver.findElements(billingAddressDropdown);

        if (addressDropdown.size() > 0) { // Address dropdown is present → Existing address available
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(billingContinueButton)).click();
            return;
        }

        // If no saved address, enter new address
        WebElement countryDropdown = BaseClass.driver.findElement(By.id("BillingNewAddress_CountryId"));
        countryDropdown.click();
        countryDropdown.sendKeys(country);
        Thread.sleep(1000);

        BaseClass.driver.findElement(By.id("BillingNewAddress_City")).sendKeys(city);
        Thread.sleep(1000);
        BaseClass.driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys(address);
        Thread.sleep(1000);
        BaseClass.driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys(postalCode);
        Thread.sleep(1000);
        BaseClass.driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys(phone);
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(billingContinueButton)).click();
        Thread.sleep(2000);
    }

    // Continue through checkout steps
    public void continueCheckout() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(shippingContinueButton)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinueButton)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(paymentMethodContinueButton)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(paymentInfoContinueButton)).click();
        Thread.sleep(2000);

    }

    // Confirm Order
    public void confirmOrder() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton)).click();
        Thread.sleep(1000);
    }

    // Verify if order is successful
    public boolean isOrderSuccessful() {
        return BaseClass.driver.findElement(orderSuccessMessage).isDisplayed();
    }

    // View Order Details
    public void viewOrderDetails() throws InterruptedException {
    	WebElement orderDetails = wait.until(ExpectedConditions.elementToBeClickable(orderDetailsLink));
        orderDetails.click();
    }
}
