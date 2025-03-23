package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CategoryPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators using PageFactory
    @FindBy(linkText = "Computers")
    WebElement computersCategory;

    @FindBy(linkText = "Desktops")
    WebElement desktopsCategory;

    @FindBy(linkText = "Notebooks")
    WebElement notebooksCategory;

    @FindBy(linkText = "Accessories")
    WebElement accessoriesCategory;

    // Constructor
    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Navigate to Computers Category
    public void selectComputers() {
        wait.until(ExpectedConditions.elementToBeClickable(computersCategory)).click();
    }

    // Navigate to Desktops Category
    public void selectDesktops() {
        wait.until(ExpectedConditions.elementToBeClickable(desktopsCategory)).click();
    }

    // Navigate to Notebooks Category
    public void selectNotebooks() {
        wait.until(ExpectedConditions.elementToBeClickable(notebooksCategory)).click();
    }

    // Navigate to Accessories Category
    public void selectAccessories() {
        wait.until(ExpectedConditions.elementToBeClickable(accessoriesCategory)).click();
    }
}
