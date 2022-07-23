package pages.booking;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class MapFullOverlay {
    private final SelenideElement CARDS_CONTAINER = $(".map_left_cards__container");
    private final SelenideElement FILTERS_CONTAINER = $(".map_left_filters__container");
    private final SelenideElement CARDS_LOADER= $("div.map_left_cards_loader_conatiner");

    public MapFullOverlay checkCardsCountFilteredByRating(Integer rating) {
        final String RATING_LOCATOR = String.format("[data-filters-item='class:class=%s']", rating);
        FILTERS_CONTAINER.find(RATING_LOCATOR).scrollTo().click();

        CARDS_LOADER.shouldBe(appear);
        CARDS_LOADER.shouldBe(disappear);

        final String CARD_RATING_LOCATOR = String.format(".//*[contains(@class, 'bui-rating')][count(span)=%s]", rating);
        int actualCardsCount = CARDS_CONTAINER.findAll(By.xpath(CARD_RATING_LOCATOR)).size();
        int expectedCardsCount = CARDS_CONTAINER.findAll("div.map-card__content-container").size();
        Assert.assertEquals(expectedCardsCount, actualCardsCount);
        return this;
    }
}
