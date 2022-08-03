package pages.homebrandofficial;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.homebrandofficial.BaseTest.cfg;

public class HomePage {

    private final SelenideElement BUTTON_CATALOG = $("[data-elem-id='1628140756306'] [href='/wear']");
    private final SelenideElement CATALOG_WEAR = $("[data-elem-id='1627449302046'] [href='/wear']");

    @Step("Открыть домашнюю страницу магазина.")
    public HomePage openPage() {
        open(cfg.baseUrl());
        return this;
    }

    @Step("Открыть каталог \"Одежда\".")
    public HomePage openCatalogWear() {
        CATALOG_WEAR.shouldBe(appear).click();
        return this;
    }
}
