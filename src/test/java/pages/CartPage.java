package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseClass;

public class CartPage {
    // Locators
    By cartLink = By.linkText("Shopping cart");
    By productNameInCart = By.xpath("//td[@class='product']//a");

    // Navigate to Cart Page
    public void navigateToCart() {
        BaseClass.driver.findElement(cartLink).click();
    }

    // Verify product is in cart
    public boolean isProductInCart(String expectedProductName) {
        // Get all product names in the cart
        List<WebElement> products = BaseClass.driver.findElements(productNameInCart);

        for (WebElement product : products) {
            if (product.getText().trim().equalsIgnoreCase(expectedProductName)) {
                return true; // Found the expected product
            }
        }
        return false; // Product not found in the cart
    }

}
