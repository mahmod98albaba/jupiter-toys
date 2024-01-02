package cart1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddAndRemoveItemsInTheSecondRow {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setup() {
        // Set up WebDriver and WebDriverWait
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void buySelectedItems() {
        // Navigate to the Jupiter Shop website
        driver.get("https://jupiter.cloud.planittesting.com/#/shop");

        // Provide the indices of the items to be bought in the second row
        int startIndex = 5;
        int endIndex = 8;

        // Loop through each item in the specified range
        for (int i = startIndex; i <= endIndex; i++) {
            // Find the item by index
            WebElement item = driver.findElement(By.xpath("(//h4[@class='product-title ng-binding'])[" + i + "]"));
            String itemName = item.getText();
            System.out.println(itemName); // Debugging purpose

            // Click the "Buy" button for the current item
            WebElement buyButton = item.findElement(By.xpath("ancestor::li//a[@class='btn btn-success']"));
            buyButton.click();
        }

        // Navigate to the cart
        driver.findElement(By.xpath("//a[@href='#/cart']")).click();
    }

    @Test(priority = 2)
    public void removeItemsFromCart() {
        // Remove the first item from the cart
        WebElement removeFirstItem = driver.findElement(By.xpath("(//i[@class='icon-remove icon-white'])[1]"));
        removeFirstItem.click();
driver.findElement(By.xpath("(//a[normalize-space()='Yes'])[1]")).click();
        // Remove the third item from the cart
        WebElement removeThirdItem = driver.findElement(By.xpath("(//a[@class='remove-item btn btn-mini btn-danger'])[2]"));
        removeThirdItem.click();
driver.findElement(By.cssSelector(".btn.btn-success[ng-click='removeItem(item)']")).click();
        // Optional: Wait for the cart page to load, if needed
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("your_cart_page_selector")));
    }

    @AfterTest
    public void teardown() {
        // Quit the WebDriver
        if (driver != null) {
//            driver.quit();
        }
    }
}
