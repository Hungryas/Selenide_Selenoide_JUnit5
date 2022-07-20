package pages.booking;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class SearchResultsPage {
    private final SelenideElement DESTINATION = $("input.ce45093752");
    private final SelenideElement FIELD_START = $("[data-testid='date-display-field-start']");
    private final SelenideElement FIELD_END = $("[data-testid='date-display-field-end']");
    private final SelenideElement NIGHTS_NUMBER = $(".fca8fcd83b .d8eab2cf7f:last-child");
    private final SelenideElement OCCUPANCY = $("[data-testid='occupancy-config']");
    private final SelenideElement SEARCH_RESULT_TITLE = $("h1.d3a14d00da");

    public SearchResultsPage checkDestinationValue(String desiredDestination) {
        String actualDestination = DESTINATION.getAttribute("value");
        Assert.assertEquals(desiredDestination, actualDestination);
        return this;
    }

    public SearchResultsPage checkDatesRange(String datesRange) {
        String actualStartDate = FIELD_START.getText().split("(,\\s)|(\\s\\d{4})")[1];
        String actualEndDate = FIELD_END.getText().split("(,\\s)|(\\s\\d{4})")[1];
        Integer actualNightNumber = Integer.valueOf(NIGHTS_NUMBER.getText().split("\\s")[0]);

        String currentStartDate = datesRange.split("(,\\s)|(\\s-\\D+)|(\\s\\()")[1];
        String currentEndDate = datesRange.split("(,\\s)|(\\s-\\D+)|(\\s\\()")[2];
        Integer currentNightNumber = Integer.valueOf(datesRange.split("(\\D+)")[3]);

        Assert.assertEquals(currentStartDate, actualStartDate);
        Assert.assertEquals(currentEndDate, actualEndDate);
        Assert.assertEquals(currentNightNumber, actualNightNumber);
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
