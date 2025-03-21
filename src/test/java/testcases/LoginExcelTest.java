package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;

public class LoginExcelTest extends BaseClass {
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() throws IOException {
        readData(); // Load config.properties
        invokeBrowser(); // Launch the browser
        driver.get(prop.getProperty("url")); // Navigate to the website

        // Initialize page objects
        homePage = new HomePage();
        loginPage = new LoginPage();
    }

    // DataProvider method to read login credentials from Excel
    @DataProvider(name = "loginData")
    public Object[][] readExcel() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\DELL\\OneDrive\\Attachments\\Desktop\\Wipro\\Testing\\DemoWebShop_excel.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("login");

        int rowCount = sheet.getLastRowNum(); // Get number of rows
        Object[][] data = new Object[rowCount][2]; // 2D array for username & password

        for (int i = 1; i <= rowCount; i++) { // Start from row 1 (skip header)
            data[i - 1][0] = sheet.getRow(i).getCell(0).getStringCellValue(); // Username
            data[i - 1][1] = sheet.getRow(i).getCell(1).getStringCellValue(); // Password
        }

        workbook.close();
        fis.close();
        return data; // Return credentials for test execution
    }

    // Test method to validate login using data from Excel
    @Test(dataProvider = "loginData")
    public void validateLogin(String username, String password) throws InterruptedException {
        homePage.navigateToLogin(); //  Navigate to Login page
        
        //  Enter login credentials using locators from LoginPage
        driver.findElement(By.id("Email")).sendKeys(username);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Log in']")).click();

        // Check if login failed using LoginPage method
        if (loginPage.isLoginFailed()) {
            System.out.println("Login failed for: " + username);
        } else {
            System.out.println("Login successful for: " + username);
        }
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        closeBrowser(); // Close the browser after test execution
    }
}
