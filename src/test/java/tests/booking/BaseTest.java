package tests.booking;

import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Configuration.*;

public abstract class BaseTest {

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
        baseUrl = "https://www.booking.com";
        browser = "chrome";
        browserSize = "1920x1080";

        // Selenoid
        remote = "http://localhost:4444/wd/hub";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("enableVNC", true);
        chromeOptions.addArguments("--lang=ru_RU");
        browserCapabilities = chromeOptions;
    }

    @AfterEach
    void teardown() {
        Selenide.closeWebDriver();
    }
}
