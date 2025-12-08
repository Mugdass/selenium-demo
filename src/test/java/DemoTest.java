import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest {

    @Test
    public void testGoogleTitle() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        // CI-safe flags
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1280,720"); // match Xvfb

        // Important: remove headless so Chrome renders in Xvfb
        // options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com");

        // Optional wait to allow rendering
        Thread.sleep(5000);

        Assert.assertTrue(driver.getTitle().contains("Google"));
        driver.quit();
    }
}
