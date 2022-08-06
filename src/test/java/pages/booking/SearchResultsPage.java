package pages.booking;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchResultsPage {
    private final ElementsCollection CHECKED_DATES_LIST = $$("div.fa3f76ae6b [aria-checked='true']");

    private final SelenideElement DESTINATION = $("input.ce45093752");
    private final SelenideElement FIELD_START_INNER = $("[data-testid='date-display-field-start']");
    private final SelenideElement FIELD_END_INNER = $("[data-testid='date-display-field-end']");
    private final SelenideElement CURRENT_DATES_RANGE = $("div.caab4cf6d1");
    private final SelenideElement OCCUPANCY = $("[data-testid='occupancy-config']");
    private final SelenideElement SEARCH_RESULT_TITLE = $("h1.d3a14d00da");
    private final SelenideElement FILTERS_SIDEBAR = $("div[data-testid='filters-sidebar']");
    private final SelenideElement SEARCH_RESULTS_TABLE = $("div.d4924c9e74");
    private final SelenideElement OVERLAY_CARD = $("[data-testid='overlay-card']");
    private final SelenideElement MAIN_MAP_TRIGGER = $(".efdb2b543b [data-testid='map-trigger']");

    @Step("Проверить, что место поездки соответствует введенному при поиске.")
    public SearchResultsPage checkDestinationValue(String desiredDestination) {
        String actualDestination = DESTINATION.getAttribute("value");
        assertEquals(desiredDestination, actualDestination);
        return this;
    }

    @Step("Проверить, что даты заезда и отъезда соответствуют введенным при поиске, а также количество ночей.")
    public SearchResultsPage checkDatesRange(LocalDate expectedCheckIn, LocalDate expectedCheckOut, Long expectedDatesRange) {
        FIELD_START_INNER.click();
        LocalDate actualCheckIn = LocalDate.parse(Objects.requireNonNull(
                CHECKED_DATES_LIST.shouldHave(sizeGreaterThan(0)).
                        get(0).shouldNotBe(empty).getAttribute("data-date")));
        LocalDate actualCheckOut = LocalDate.parse(Objects.requireNonNull(
                CHECKED_DATES_LIST.shouldHave(sizeGreaterThan(0)).
                        get(CHECKED_DATES_LIST.size() - 1).shouldNotBe(empty).getAttribute("data-date")));
        Long actualDataRange = ChronoUnit.DAYS.between(actualCheckIn, actualCheckOut);

        assertEquals(expectedCheckIn, actualCheckIn);
        assertEquals(expectedCheckOut, actualCheckOut);
        assertEquals(expectedDatesRange, actualDataRange);
        return this;
    }

    @Step("Проверить, что количество взрослых, детей и номеров равны указанным при поиске.")
    public SearchResultsPage checkOccupancy(List<Integer> expectedOccupancy) {
        List<Integer> actualOccupancy = Arrays.stream(OCCUPANCY.getText().split("(\\D+)")).map(Integer::parseInt).toList();
        assertEquals(expectedOccupancy, actualOccupancy);
        return this;
    }

    @Step("Проверить, что поиск дал результаты.")
    public SearchResultsPage checkResultsCount() {
        int resultCount = Integer.parseInt(SEARCH_RESULT_TITLE.getText().split("(\\D+)")[1]);
        assertTrue(resultCount > 0);
        return this;
    }

    @Step("Проверить, что все предложения имеют рейтинг {rating} звезд.")
    public SearchResultsPage checkCardsCountFilteredByRating(Integer rating) {
        final String RATING_LOCATOR = String.format("[data-filters-item='class:class=%s']", rating);
        FILTERS_SIDEBAR.find(RATING_LOCATOR).scrollTo().click();
        if (OVERLAY_CARD.isDisplayed()) {OVERLAY_CARD.shouldBe(disappear, ofSeconds(6));}

        final String CARD_RATING_LOCATOR = String.format(".//*[contains(@data-testid,'rating')][count(span)=%s]", rating);
        int expectedCardsCount = SEARCH_RESULTS_TABLE.findAll("[data-testid='property-card']").size();
        int actualCardsCount = SEARCH_RESULTS_TABLE.findAll(By.xpath(CARD_RATING_LOCATOR))
                .shouldHave(sizeGreaterThan(0)).size();
        assertEquals(expectedCardsCount, actualCardsCount);
        return this;
    }

    @Step("Открыть оверлей карты.")
    public SearchResultsPage openMapFullOverlay() {
        MAIN_MAP_TRIGGER.shouldBe(visible).click();
        return this;
    }
}
