import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.io.File;
import java.time.Duration;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.FormatKeys.MimeType;
import org.monte.media.VideoFormatKeys;
import org.monte.media.VideoFormatKeys.EncodingKey;
import org.monte.media.VideoFormatKeys.CompressorNameKey;
import org.monte.media.VideoFormatKeys.DepthKey;
import org.monte.media.VideoFormatKeys.FrameRateKey;
import org.monte.media.VideoFormatKeys.QualityKey;
import org.monte.media.VideoFormatKeys.KeyFrameIntervalKey;
import org.monte.media.ScreenRecorder;

public class BaseTest {

    protected WebDriver driver;
    private ScreenRecorder screenRecorder;

    @BeforeEach
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        File movieFolder = new File("target/videos");
        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        }

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        Rectangle captureArea = gc.getBounds();

        Format fileFormat = new Format(MediaTypeKey, MediaType.FILE,
                                       MimeTypeKey, MIME_QUICKTIME);
        Format screenFormat = new Format(MediaTypeKey, MediaType.VIDEO,
                EncodingKey, ENCODING_QUICKTIME_ANIMATION,
                CompressorNameKey, ENCODING_QUICKTIME_ANIMATION,
                DepthKey, 24,
                FrameRateKey, Rational.valueOf(15),
                QualityKey, 1.0f,
                KeyFrameIntervalKey, 15 * 60);

        screenRecorder = new ScreenRecorder(gc, captureArea,
                                           movieFolder, fileFormat,
                                           screenFormat, null, null);
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
}
