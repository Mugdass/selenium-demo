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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // Explicitly set Chrome binary path for GitHub Actions
        options.setBinary("/usr/bin/google-chrome");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        // Dynamic wait for elements
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

        // Wait for the title to update
        wait.until(ExpectedConditions.titleContains("selenium demo"));

        assertTrue(driver.getTitle().toLowerCase().contains("selenium demo"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
