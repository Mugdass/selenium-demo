import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DemoSearchTest extends BaseTest {

    @Test
    public void searchTest() {
        driver.get("https://www.google.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));

        searchBox.sendKeys("Selenium");
        searchBox.submit();

        wait.until(ExpectedConditions.titleContains("Selenium"));
    }
}
