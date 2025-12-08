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
        // Automatically download and setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Configure Chrome options for headless CI environment
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");        // modern headless mode
        options.addArguments("--disable-gpu");         // recommended for Linux CI
        options.addArguments("--no-sandbox");          // necessary in some CI containers
        options.addArguments("--disable-dev-shm-usage"); // prevent limited /dev/shm issues
        options.addArguments("--window-size=1920,1080"); // replaces maximize()

        // Start Chrome with options
        driver = new ChromeDriver(options);

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Optional: start video recording
        // VideoRecorder.startRecording("TestVideoName");
    }

    @AfterEach
    public void tearDown() {
        // Stop Chrome
        if (driver != null) {
            driver.quit();
        }

        // Optional: stop video recording
        // VideoRecorder.stopRecording();
    }
}
