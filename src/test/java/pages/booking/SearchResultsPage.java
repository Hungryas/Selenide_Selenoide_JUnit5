package pages.booking;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

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

    public SearchResultsPage checkDestinationValue(String desiredDestination) {
        String actualDestination = DESTINATION.getAttribute("value");
        Assert.assertEquals(desiredDestination, actualDestination);
        return this;
    }

    public SearchResultsPage checkDatesRange(String desiredCheckIn, String desiredCheckOut, String desiredDatesRange) {
        FIELD_START_INNER.click();
        String currentCheckIn = CHECKED_DATES_LIST.get(0).getAttribute("data-date");
        String currentCheckOut = CHECKED_DATES_LIST.get(CHECKED_DATES_LIST.size() - 1).getAttribute("data-date");

        String regex = "(.+\\()|(\\s\\D+)";
        Integer desiredNightNumber = Integer.valueOf(CURRENT_DATES_RANGE.getText().split(regex)[1]);
        Integer currentNightNumber = Integer.valueOf(desiredDatesRange.split(regex)[1]);

        Assert.assertEquals(desiredCheckIn, currentCheckIn);
        Assert.assertEquals(desiredCheckOut, currentCheckOut);
        Assert.assertEquals(desiredNightNumber, currentNightNumber);
        return this;
    }

    public SearchResultsPage checkOccupancy(List<Integer> currentOccupancy) {
        List<Integer> actualOccupancy = Arrays.stream(OCCUPANCY.getText().split("(\\D+)"))
                .map(Integer::parseInt).toList();
        Assert.assertEquals(currentOccupancy, actualOccupancy);
        return this;
    }

    public SearchResultsPage checkResultsCount() {
        int resultCount = Integer.parseInt(SEARCH_RESULT_TITLE.getText().split("(\\D+)")[1]);
        Assert.assertTrue(resultCount > 0);
        return this;
    }
}
