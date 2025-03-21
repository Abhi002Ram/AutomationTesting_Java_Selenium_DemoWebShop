package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import base.BaseClass;

public class RegistrationPage extends BaseClass {

    // Locators for registration fields
    By maleGender = By.id("gender-male");
    By firstNameField = By.id("FirstName");
    By lastNameField = By.id("LastName");
    By emailField = By.id("Email");
    By passwordField = By.id("Password");
    By confirmPasswordField = By.id("ConfirmPassword");
    By registerButton = By.id("register-button");
    By errorMessage = By.xpath("//div[contains(@class, 'validation-summary-errors')]"); // Error Message Locator

    // Method to fill out registration form
    public void fillRegistrationForm () throws InterruptedException {
    	driver.findElement(maleGender).click();        
        Thread.sleep(500);
        
        // Fetching values from config.properties
        String firstName = BaseClass.prop.getProperty("firstName");
        String lastName = BaseClass.prop.getProperty("lastName");
        String email = BaseClass.prop.getProperty("email");
        String password = BaseClass.prop.getProperty("password");
        
        driver.findElement(firstNameField).sendKeys(firstName);
        Thread.sleep(1000);
        driver.findElement(lastNameField).sendKeys(lastName);
        Thread.sleep(1000);
        driver.findElement(emailField).sendKeys(email);
        Thread.sleep(1000);
        driver.findElement(passwordField).sendKeys(password);
        Thread.sleep(1000);
        driver.findElement(confirmPasswordField).sendKeys(password);
        Thread.sleep(1000);
        driver.findElement(registerButton).click();
    }

    // Method to check if user is already registered
    public boolean isUserAlreadyRegistered() {
        try {
            WebElement error = driver.findElement(errorMessage);
            return error.isDisplayed(); // Returns true if error message is present
        } catch (Exception e) {
            return false; // No error message means registration is successful
        }
    }
}
