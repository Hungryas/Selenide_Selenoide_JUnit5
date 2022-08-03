package pages.mts;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;

public class HomePage {
    private final ElementsCollection PROMO_COVER_LIST = $$(".promo-cover__list_initialize a");
    private final SelenideElement LOADER_CIRCLE = $("svg .loader-circle");

    @Step("Открыть домашнюю страницу сайта.")
    public HomePage openPage() {
        open(Configuration.baseUrl);
        return this;
    }

    @Step("Проверить, что демонстрируется весь список промо-обложек.")
    public HomePage checkPromoSliderChanging() {
        PROMO_COVER_LIST.shouldHave(sizeGreaterThan(0));
        for (SelenideElement element : PROMO_COVER_LIST) {
            element.shouldBe(visible);
            LOADER_CIRCLE.shouldHave(attribute("style", "stroke-dashoffset: 180;"),
                    ofSeconds(10));
            element.shouldNotBe(visible);
        }
        return this;
    }
}