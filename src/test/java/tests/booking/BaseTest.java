package tests.booking;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {

    @Before
    public void setup() {
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

    @After
    public void teardown() {
        Selenide.closeWebDriver();
    }
}
