package pages;

import org.openqa.selenium.By;
import base.BaseClass;

public class HomePage {
    // Locators for elements on the home page
    By registerLink = By.linkText("Register");
    By loginLink = By.linkText("Log in");
    By computersCategory = By.linkText("Computers");

    // Method to navigate to the Registration page
    public void navigateToRegister() {
        BaseClass.driver.findElement(registerLink).click();
    }

    // Method to navigate to the Login page
    public void navigateToLogin() {
        BaseClass.driver.findElement(loginLink).click();
    }

    // Method to navigate to the Computers category
    public void navigateToComputers() {
        BaseClass.driver.findElement(computersCategory).click();
    }
}
