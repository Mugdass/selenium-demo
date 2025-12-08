import org.junit.jupiter.api.Test;

public class DemoTest extends BaseTest {

    @Test
    public void simpleTest() {
        driver.get("https://example.com");
        System.out.println("Title: " + driver.getTitle());
    }
}
