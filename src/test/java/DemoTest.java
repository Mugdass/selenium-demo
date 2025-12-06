import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest {
@Test
public void testGoogleTitle() {

    ChromeOptions options = new ChromeOptions();
    // comment out headless so you can see the browser
    // options.addArguments("--headless=new");

    WebDriver driver = new ChromeDriver(options);
    driver.get("https://www.google.com");

    // wait 5 seconds so you can see the page
    try { Thread.sleep(5000); } catch (Exception e) {}

    Assert.assertTrue(driver.getTitle().contains("Google"));

    driver.quit();
}

}
