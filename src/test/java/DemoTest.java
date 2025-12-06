import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest {

    @Test
    public void testGoogleTitle() {

        ChromeOptions options = new ChromeOptions();
        // Headless mode + CI-friendly flags
        options.addArguments("--headless=new");       // Run without GUI
        options.addArguments("--no-sandbox");         // Required in CI
        options.addArguments("--disable-dev-shm-usage"); // Prevent memory issues
        options.addArguments("--disable-gpu");        // Safe in CI

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com");

        // Optional: wait a bit (not needed in real tests)
        try { Thread.sleep(5000); } catch (Exception e) {}

        Assert.assertTrue(driver.getTitle().contains("Google"));

        driver.quit();
    }
}

