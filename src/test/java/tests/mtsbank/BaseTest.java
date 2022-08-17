package tests.mtsbank;

import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public abstract class BaseTest {

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        baseUrl = "https://www.mtsbank.ru";
        browser = "chrome";
        browserSize = "1920x1080";
        remote = "http://localhost:4444/wd/hub";
        addListener("AllureSelenide", new AllureSelenide().savePageSource(false));
    }

    @AfterAll
    static void teardown() {
        Selenide.closeWebDriver();
    }
}
