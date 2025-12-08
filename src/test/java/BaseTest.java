package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

// Optional: for video recording
import org.monte.media.Format;
import org.monte.screenrecorder.ScreenRecorder;
import java.awt.*;
import java.io.File;

public class BaseTest {

    protected WebDriver driver;
    private ScreenRecorder screenRecorder;

    @BeforeEach
    public void setUp() throws Exception {
        // Setup ChromeDriver automatically
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Optional: Start video recording
        startRecording("TestVideo");
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }

        // Stop video recording
        stopRecording();
    }

    private void startRecording(String fileName) throws Exception {
        // Define recording folder
        File folder = new File("target/videos");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Set up screen recording
        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        screenRecorder = new ScreenRecorder(gc, new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()),
                new Format(org.monte.media.VideoFormatKeys.MediaTypeKey, org.monte.media.VideoFormatKeys.MediaType.FILE),
                new Format(org.monte.media.VideoFormatKeys.MediaTypeKey, org.monte.media.VideoFormatKeys.MediaType.VIDEO, 
                           org.monte.media.VideoFormatKeys.MimeTypeKey, org.monte.media.VideoFormatKeys.MIME_AVI),
                null, folder, fileName);

        screenRecorder.start();
    }

    private void stopRecording() throws Exception {
        if (screenRecorder != null) {
            screenRecorder.stop();
        }
    }
}
