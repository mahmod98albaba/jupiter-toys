package cart1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FindMostExpensiveItem {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setup() {
        // Set up WebDriver and WebDriverWait
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void findAndAddMostExpensiveItemToCart() {
        // Navigate to the Jupiter Shop website
        driver.get("https://jupiter.cloud.planittesting.com/#/shop");

        // Find all product elements
        List<WebElement> productElements = driver.findElements(By.cssSelector("li[ng-repeat='item in catalog']"));

        // Initialize variables to store information about the most expensive item
        double maxPrice = 0.0;

        // Loop through each product to find the most expensive price
        for (WebElement product : productElements) {
            // Get product price and convert it to double
            String priceText = product.findElement(By.cssSelector("span.product-price")).getText().replaceAll("[^\\d.]", "");
            double productPrice = Double.parseDouble(priceText);

            // Compare prices and update the most expensive item if needed
            if (productPrice >= maxPrice) {
                maxPrice = productPrice;
            }
        }

        // Loop through each product to click the "Buy" button for the most expensive items
        for (WebElement product : productElements) {
            // Get product name
            String productName = product.findElement(By.cssSelector("h4.product-title")).getText();

            // Get product price and convert it to double
            String priceText = product.findElement(By.cssSelector("span.product-price")).getText().replaceAll("[^\\d.]", "");
            double productPrice = Double.parseDouble(priceText);

            // Click the "Buy" button for each occurrence of the most expensive item
            if (productPrice == maxPrice) {
                WebElement buyButton = product.findElement(By.xpath(".//a[@ng-click='add(item)']"));
                buyButton.click();
            }
        }

        // Navigate to the cart (optional)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='#/cart']")));
        driver.findElement(By.cssSelector("a[href='#/cart']")).click();
    }

    @AfterTest
    public void teardown() {
        // Quit the WebDriver
        if (driver != null) {
            driver.quit();
        }
    }
}
