package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DemoSearchTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1280,720");
        driver = new ChromeDriver(options);
        driver.get("https://www.google.com");
    }

    @Test
    public void testGoogleSearch() throws InterruptedException {
        // Accept cookies if necessary
        try {
            WebElement acceptButton = driver.findElement(By.xpath("//button[contains(text(),'I agree')]"));
            acceptButton.click();
        } catch (Exception e) {}

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.sendKeys(Keys.RETURN);

        Thread.sleep(3000); // wait for results
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("selenium webdriver"));
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
