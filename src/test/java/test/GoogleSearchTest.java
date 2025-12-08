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
        // Use system-installed Chrome directly
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/google-chrome"); // path to Chrome installed by workflow
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

        WebElement searchBox = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("q"))
        );

        searchBox.sendKeys("selenium demo");
        searchBox.submit();

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
