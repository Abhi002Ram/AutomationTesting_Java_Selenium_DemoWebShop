package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductSearchPage {
    WebDriver driver;

    // Locators using PageFactory
    @FindBy(id = "small-searchterms") 
    WebElement searchBox;

    @FindBy(xpath = "//input[@value='Search']")
    WebElement searchButton;

    @FindBy(linkText = "Simple Computer")
    WebElement simpleComputer;

    @FindBy(xpath = "//input[@type='button' and @value='Add to cart']") 
    WebElement addToCartButton;

    @FindBy(className = "product-item")
    WebElement productList; // Checking if any product appears in results
    
    @FindBy(xpath = "//label[@for='product_attribute_75_5_31_96']")
    WebElement slowOption; // "Slow" radio button label

    // Constructor
    public ProductSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to search a product
    public void searchProduct(String productName) throws InterruptedException {
        searchBox.sendKeys(productName);
        Thread.sleep(1000);
        searchButton.click();
    }

    // Method to verify products are displayed
    public boolean isProductListDisplayed() {
        return productList.isDisplayed();
    }

    // Method to select Simple Computer
    public void selectSimpleComputer() {
        simpleComputer.click();
    }

    // Method to add the product to the cart
    public void addToCart() throws InterruptedException {
        // Wait for 2 seconds before selecting the radio button
        Thread.sleep(2000);

        // Select the "Slow" radio button
        slowOption.click();

        // Scroll down 300px
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");

        // Wait for 1 second before clicking "Add to Cart"
        Thread.sleep(1000);

        // Click "Add to Cart" button
        addToCartButton.click();

        // Wait for 2 seconds before navigating to cart
        Thread.sleep(2000);
    }
}
