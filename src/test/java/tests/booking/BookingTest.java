package tests.booking;

import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;
import pages.booking.HomePage;
import pages.booking.MapFullOverlay;
import pages.booking.SearchResultsPage;
import tests.BaseTest;

import java.time.LocalDate;
import java.util.List;

public class BookingTest extends BaseTest {
    private static HomePage homePage;
    private static SearchResultsPage searchResultsPage;
    private static MapFullOverlay mapFullOverlay;

    private final LocalDate CHECK_IN = LocalDate.now().plusDays(1);
    private final LocalDate CHECK_OUT = CHECK_IN.plusDays(Math.round(1 + 29 * Math.random()));
    private final String DESTINATION = "Барселона";
    private final Integer ADULT_COUNT = 1;
    private final Integer CHILDREN_COUNT = 0;
    private final Integer ROOM_COUNT = 1;
    private final Integer RATING = 5;


    @Before
    public void setup() {
        Configuration.baseUrl = "https://www.booking.com";
        Configuration.browserSize = "1920x1080";

        homePage = new HomePage();
        homePage.openPage()
                .enterDestination(DESTINATION)
                .enterDateRange(CHECK_IN, CHECK_OUT)
                .enterGuestsCounts(List.of(ADULT_COUNT, CHILDREN_COUNT, ROOM_COUNT))
                .checkPrices();
    }

    @Test
    public void checkSearchDetails() {
        searchResultsPage = new SearchResultsPage();
        searchResultsPage.checkDestinationValue(DESTINATION)
                .checkDatesRange(CHECK_IN, CHECK_OUT, homePage.getDatesRange())
                .checkOccupancy(List.of(ADULT_COUNT, CHILDREN_COUNT, ROOM_COUNT))
                .checkResultsCount();
    }

    @Test
    public void checkFilterByRating() {
        searchResultsPage = new SearchResultsPage();
        searchResultsPage.checkCardsCountFilteredByRating(RATING);
    }

    @Test
    public void checkFilterByRatingOnMapOverlay() {
        searchResultsPage = new SearchResultsPage();
        searchResultsPage.openMapFullOverlay();

        mapFullOverlay = new MapFullOverlay();
        mapFullOverlay.checkCardsCountFilteredByRating(RATING);
    }
}
