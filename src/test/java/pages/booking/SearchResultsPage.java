package pages.booking;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage {
    private final SelenideElement DESTINATION = $("input.ce45093752");
    private final SelenideElement FIELD_START_INNER = $("[data-testid='date-display-field-start']");
    private final SelenideElement FIELD_END_INNER = $("[data-testid='date-display-field-end']");
    private final ElementsCollection CHECKED_DATES_LIST = $$("div.fa3f76ae6b [aria-checked='true']");
    private final SelenideElement CURRENT_DATES_RANGE = $("div.caab4cf6d1");
    private final SelenideElement OCCUPANCY = $("[data-testid='occupancy-config']");
    private final SelenideElement SEARCH_RESULT_TITLE = $("h1.d3a14d00da");
    private final SelenideElement FILTERS_SIDEBAR = $("div[data-testid='filters-sidebar']");
    private final SelenideElement SEARCH_RESULTS_TABLE = $("div.d4924c9e74");
    private final SelenideElement OVERLAY_CARD = $("[data-testid='overlay-card']");
    private final SelenideElement MAIN_MAP_TRIGGER = $(".efdb2b543b [data-testid='map-trigger']");

    public SearchResultsPage checkDestinationValue(String desiredDestination) {
        String actualDestination = DESTINATION.getAttribute("value");
        Assert.assertEquals(desiredDestination, actualDestination);
        return this;
    }

    public SearchResultsPage checkDatesRange(LocalDate expectedCheckIn, LocalDate expectedCheckOut, String expectedDatesRange) {
        FIELD_START_INNER.click();
        LocalDate actualCheckIn = LocalDate.parse(CHECKED_DATES_LIST.get(0).shouldNotBe(empty)
                .getAttribute("data-date"));
        LocalDate actualCheckOut = LocalDate.parse(CHECKED_DATES_LIST.get(CHECKED_DATES_LIST.size() - 1).shouldNotBe(empty)
                .getAttribute("data-date"));

        String regex = "(.+\\()|(\\s\\D+)";
        Integer expectedNightNumber = Integer.valueOf(CURRENT_DATES_RANGE.getText().split(regex)[1]);
        Integer actualNightNumber = Integer.valueOf(expectedDatesRange.split(regex)[1]);

        Assert.assertEquals(expectedCheckIn, actualCheckIn);
        Assert.assertEquals(expectedCheckOut, actualCheckOut);
        Assert.assertEquals(expectedNightNumber, actualNightNumber);
        return this;
    }

    public SearchResultsPage checkOccupancy(List<Integer> expectedOccupancy) {
        List<Integer> actualOccupancy = Arrays.stream(OCCUPANCY.getText().split("(\\D+)"))
                .map(Integer::parseInt).toList();
        Assert.assertEquals(expectedOccupancy, actualOccupancy);
        return this;
    }

    public SearchResultsPage checkResultsCount() {
        int resultCount = Integer.parseInt(SEARCH_RESULT_TITLE.getText().split("(\\D+)")[1]);
        Assert.assertTrue(resultCount > 0);
        return this;
    }

    public SearchResultsPage checkCardsCountFilteredByRating(Integer rating) {
        final String RATING_LOCATOR = String.format("[data-filters-item='class:class=%s']", rating);
        FILTERS_SIDEBAR.find(RATING_LOCATOR).scrollTo().click();

        OVERLAY_CARD.shouldBe(disappear);

        final String CARD_RATING_LOCATOR = String.format(".//*[@data-testid='rating-stars'][count(span)=%s]", rating);
        int actualCardsCount = SEARCH_RESULTS_TABLE.findAll(By.xpath(CARD_RATING_LOCATOR)).size();
        int expectedCardsCount = SEARCH_RESULTS_TABLE.findAll("[data-testid='property-card']").size();
        Assert.assertEquals(expectedCardsCount, actualCardsCount);
        return this;
    }

    public SearchResultsPage openMapFullOverlay() {
        MAIN_MAP_TRIGGER.shouldBe(visible).click();
        return this;
    }
}
