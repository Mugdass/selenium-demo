package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // Use system-installed Chrome directly (installed in workflow)
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/google-chrome"); // GitHub Actions Chrome path
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void searchSeleniumDemo() {
        driver.get("https://www.google.com");

        // Wait for search box to be present
        WebElement searchBox = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("q"))
        );

        searchBox.sendKeys("selenium demo");
        searchBox.submit();

        // Wait for search results container
        WebElement results = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("search"))
        );

        // Assert that results contain the word "selenium"
        assertTrue(results.getText().toLowerCase().contains("selenium"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
