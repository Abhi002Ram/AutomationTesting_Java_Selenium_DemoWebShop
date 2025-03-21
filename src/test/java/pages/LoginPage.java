package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import base.BaseClass;

public class LoginPage {

    // Locators for login fields
    By emailField = By.id("Email");
    By passwordField = By.id("Password");
    By loginButton = By.xpath("//input[@value='Log in']");
    By errorMessage = By.xpath("//div[contains(@class, 'message-error')]"); // Error message locator

    // Method to perform login using credentials from data.properties
    public void login() throws InterruptedException {
        // Get email and password from data.properties using BaseClass
        String email = BaseClass.prop.getProperty("email");
        String password = BaseClass.prop.getProperty("password");

        // Enter the email and password
        BaseClass.driver.findElement(emailField).sendKeys(email);
        Thread.sleep(1000);
        BaseClass.driver.findElement(passwordField).sendKeys(password);
        Thread.sleep(1000);
        BaseClass.driver.findElement(loginButton).click();
    }

    // Method to check if login failed
    public boolean isLoginFailed() {
        try {
            WebElement error = BaseClass.driver.findElement(errorMessage);
            return error.isDisplayed();
        } catch (Exception e) {
            return false; // No error message means login was successful
        }
    }
}
