package stepDef;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	WebDriver driver = Hooks.driver;
	
    @Given("Browser is open")
    public void browser_is_open() {
        System.out.println("Browser is already opened by Hooks.");
    }

    @And("User navigates to Demo Web Shop")
    public void user_navigates_to_demo_web_shop() {
        driver.get("https://demowebshop.tricentis.com/");
    }

    @And("User is on the login page")
    public void user_is_on_the_login_page() {
        driver.findElement(By.linkText("Log in")).click();
    }

    @When("User enters {string} and {string}")
    public void user_enters_credentials(String username, String password) {
        driver.findElement(By.id("Email")).sendKeys(username);
        driver.findElement(By.id("Password")).sendKeys(password);
    }

    @And("Clicks on the login button")
    public void clicks_on_the_login_button() {
        driver.findElement(By.xpath("//input[@value='Log in']")).click();
    }

    @Then("User should see appropriate message")
    public void user_should_see_appropriate_message() {
        try {
            if (driver.findElement(By.xpath("//div[contains(@class, 'message-error')]")).isDisplayed()) {
                System.out.println("Login failed");
            } else {
                System.out.println("Login successful");
            }
        } catch (Exception e) {
            System.out.println("Login successful");
        } finally {
            driver.quit();
        }
    }
}
