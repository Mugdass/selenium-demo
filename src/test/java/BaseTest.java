import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Automatically download and setup the correct ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Chrome options for CI/CD
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Headless mode
        options.addArguments("--no-sandbox"); // Required for Linux runners
        options.addArguments("--disable-dev-shm-usage"); // Avoid resource issues
        options.addArguments("--window-size=1920,1080"); // Optional, replaces maximize()

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Optional: Start video recording here
        // VideoRecorder.startRecording("TestVideoName");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        // Optional: Stop video recording here
        // VideoRecorder.stopRecording();
    }
}
