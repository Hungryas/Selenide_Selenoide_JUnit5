package tests.mts;

import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Configuration.*;

public abstract class BaseTest {

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        baseUrl = "https://www.mts.ru";
        browser = "chrome";
        browserSize = "1920x1080";

        // Selenoid
        remote = "http://localhost:4444/wd/hub";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("enableVNC", true);
    }

    @AfterEach
    public void teardown() {
        Selenide.closeWebDriver();
    }
}
