import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Automatically download and setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Start Chrome
        driver = new ChromeDriver();

        // Maximize window (may fail in some CI, see note below)
        driver.manage().window().maximize();

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Optional: start video recording here
        // VideoRecorder.startRecording("TestVideoName");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        // Optional: stop video recording here
        // VideoRecorder.stopRecording();
    }
}
