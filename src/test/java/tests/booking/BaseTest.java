package tests.booking;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
        Configuration.baseUrl = "https://www.booking.com";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";

        // Selenoid
        Configuration.remote = "http://localhost:4444/wd/hub";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("enableVNC", true);
        chromeOptions.addArguments("--lang=ru_RU");
        Configuration.browserCapabilities = chromeOptions;
    }

    @AfterEach
    void teardown() {
        Selenide.closeWebDriver();
    }
}
