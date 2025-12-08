package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");      // run in headless mode
        options.addArguments("--disable-gpu");   // recommended for headless
        options.addArguments("--no-sandbox");    // required on Linux CI
        options.addArguments("--disable-dev-shm-usage"); // prevent memory issues
        driver = new ChromeDriver(options);
    }

    @Test
    public void searchSeleniumDemo() throws InterruptedException {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("selenium demo");
        searchBox.submit();

        Thread.sleep(2000); // wait for results to load

        assertTrue(driver.getTitle().toLowerCase().contains("selenium demo"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
