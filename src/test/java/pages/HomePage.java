package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.BaseTest.cfg;

public class HomePage {

    private final SelenideElement BUTTON_CATALOG = $("[data-elem-id='1628140756306'] [href='/wear']");
    private final SelenideElement CATALOG_WEAR = $("[data-elem-id='1627449302046'] [href='/wear']");

    public HomePage openPage() {
        open(cfg.baseUrl());
        return this;
    }

    public void openCatalogWear() {
        CATALOG_WEAR.shouldBe(appear, Duration.ofSeconds(10)).click();
    }
}
