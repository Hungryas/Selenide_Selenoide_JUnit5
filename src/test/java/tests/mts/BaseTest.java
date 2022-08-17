package tests.mts;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class BaseTest {

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        Configuration.baseUrl = "https://www.mts.ru";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";

        // Selenoid
        Configuration.remote = "http://localhost:4444/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    public void teardown() {
        Selenide.closeWebDriver();
    }
}
