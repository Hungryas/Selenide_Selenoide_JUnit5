package tests.homebrandofficial;

import com.codeborne.selenide.Selenide;
import config.ProjectConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Configuration.*;

public abstract class BaseTest {

    public final static ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        browser = "chrome";
        browserSize = "1920x1080";

        // Selenoid
        remote = "http://localhost:4444/wd/hub";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("enableVNC", true);
        browserCapabilities = chromeOptions;
    }

    @AfterEach
    public void teardown() {
        Selenide.closeWebDriver();
    }
}
