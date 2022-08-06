package pages.booking;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertEquals;

public class MapFullOverlayPage {
    private final SelenideElement CARDS_CONTAINER = $(".map_left_cards__container");
    private final SelenideElement FILTERS_CONTAINER = $(".map_left_filters__container");
    private final SelenideElement CARDS_LOADER= $("div.map_left_cards_loader_conatiner");

    @Step("Проверить, что все предложения имеют рейтинг {rating} звезд.")
    public MapFullOverlayPage checkCardsCountFilteredByRating(Integer rating) {
        final String RATING_LOCATOR = String.format("[data-filters-item='class:class=%s']", rating);
        FILTERS_CONTAINER.find(RATING_LOCATOR).scrollTo().click();

        if (CARDS_LOADER.isDisplayed()) {CARDS_LOADER.shouldBe(disappear, ofSeconds(6));}

        final String CARD_RATING_LOCATOR = String.format(".//*[contains(@class, 'bui-rating')][count(span)=%s]", rating);
        int expectedCardsCount = CARDS_CONTAINER.findAll("div.map-card__content-container").size();
        int actualCardsCount = CARDS_CONTAINER.findAll(By.xpath(CARD_RATING_LOCATOR))
                .shouldHave(sizeGreaterThan(0)).size();
        assertEquals(expectedCardsCount, actualCardsCount);
        return this;
    }
}
