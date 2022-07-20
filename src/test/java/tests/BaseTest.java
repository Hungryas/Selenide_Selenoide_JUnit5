package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.ProjectConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {

    public static ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);

    @Before
    public void setup() {
        WebDriverManager.edgedriver().setup();
        Configuration.browser = "edge";
        Configuration.driverManagerEnabled = false;
        Configuration.headless = false;
        Configuration.browserSize = "1920x1080";
    }

    @After
    public void teardown() {
        Selenide.closeWebDriver();
    }
}
