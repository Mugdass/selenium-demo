import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest {

    @Test
    public void testGoogleTitle() {
        ChromeOptions options = new ChromeOptions();
        
        // CI-friendly flags
        options.addArguments("--no-sandbox");           // Required in CI
        options.addArguments("--disable-dev-shm-usage"); // Prevent memory issues
        options.addArguments("--disable-gpu");          // Safe in CI
        options.addArguments("--window-size=1280,720"); // Match ffmpeg recording

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.google.com");

            // Optional: small wait for page load
            Thread.sleep(3000);

            Assert.assertTrue(driver.getTitle().contains("Google"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
