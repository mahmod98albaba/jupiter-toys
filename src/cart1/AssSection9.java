package cart1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AssSection9 {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        // Set up WebDriver with implicit wait
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void buyAllItemsAndCheckout() {
        // Navigate to the Jupiter Shop website
        driver.get("https://jupiter.cloud.planittesting.com/#/shop");

        // Click on the "Buy" button for each item
        List<WebElement> products = driver.findElements(By.linkText("Buy"));
        for (WebElement product : products) {
            product.click();
        }

        // Navigate to the cart
        driver.findElement(By.cssSelector("a[href='#/cart']")).click();

        // Click on the "Check Out" link
        driver.findElement(By.linkText("Check Out")).click();

        // Fill in the delivery details
        driver.findElement(By.id("forename")).sendKeys("Mahmod");
        driver.findElement(By.id("surname")).sendKeys("Albaba");
        driver.findElement(By.id("email")).sendKeys("mahmodalbaba987@gmail.com");
        driver.findElement(By.id("telephone")).sendKeys("0797922410");
        driver.findElement(By.id("address")).sendKeys("Amman/sahab");

        // Use implicit wait for the card type dropdown
        WebElement staticDropdown = driver.findElement(By.name("cardType"));
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByValue("Visa");

        // Use implicit wait for the card number field
        driver.findElement(By.xpath("//input[@placeholder='1234 9876 1234 9876']")).sendKeys("1243 4565 7780 1155");
        driver.findElement(By.id("checkout-submit-btn")).click();
    }

    @AfterTest
    public void teardown() {
        // Quit the WebDriver
        if (driver != null) {
            driver.quit();
        }
    }
}
