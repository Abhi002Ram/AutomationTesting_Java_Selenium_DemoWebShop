package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Properties;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

public class BaseClass {
    public static WebDriver driver;
    public static Properties prop;
    protected static WebDriverWait wait;
   
    // Read config.properties
    public static void readData() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/data/data.properties"); // Relative path
        prop.load(fis);
    }

    // Launch Browser using config file
    public static void invokeBrowser() throws IOException {
        readData(); // Load data.properties
        String browser = prop.getProperty("browser"); // Read browser from properties
        initializeBrowser(browser);
    }
    
    // Launch Browser with TestNG Parameter
    @Parameters("browser")
    public static void initializeBrowser(String browser) {
        
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            System.out.println("Invalid browser in config.properties");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Take screenshot
    public static void screenshot(String filename) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path destination = Paths.get("./screenshots/" + filename + "-" + System.currentTimeMillis() + ".png");
        Files.copy(src.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
    }


    // Close browser
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
        System.out.println("Browser closed successfully.");
    }
}
