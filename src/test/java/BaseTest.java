import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.io.File;
import java.time.Duration;

import org.monte.screenrecorder.ScreenRecorder;
import org.monte.media.Format;
import org.monte.media.math.Rational;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class BaseTest {

    protected WebDriver driver;
    private ScreenRecorder screenRecorder;

    @BeforeEach
    public void setUp() throws Exception {
        // Set up ChromeDriver automatically
        WebDriverManager.chromedriver().setup();

        // Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        // Do NOT use headless — recording needs a visible framebuffer

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Start screen recording
        File file = new File("target/videos");
        if (!file.exists()) file.mkdirs();

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        screenRecorder = new SpecializedScreenRecorder(gc, file, "TestVideo");
        screenRecorder.start();
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        if (screenRecorder != null) {
            screenRecorder.stop();
        }
    }

    // Custom ScreenRecorder to name files with timestamp
    private static class SpecializedScreenRecorder extends ScreenRecorder {
        private final String fileName;

        public SpecializedScreenRecorder(GraphicsConfiguration cfg, File movieFolder, String name) throws Exception {
            super(cfg, movieFolder,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_QUICKTIME),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_QUICKTIME_ANIMATION,
                            CompressorNameKey, ENCODING_QUICKTIME_ANIMATION,
                            DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                            QualityKey, 1.0f,
                            KeyFrameIntervalKey, 15 * 60),
                    null, null);
            this.fileName = name;
        }

        @Override
        protected File createMovieFile(Format format) {
            if (!movieFolder.exists()) movieFolder.mkdirs();
            return new File(movieFolder, fileName + "_" + System.currentTimeMillis() + ".mov");
        }
    }
}
