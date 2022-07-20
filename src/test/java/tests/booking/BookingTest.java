package tests.booking;

import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;
import pages.booking.HomePage;
import pages.booking.SearchResultsPage;
import tests.BaseTest;

import java.util.List;

public class BookingTest extends BaseTest {
    private static HomePage homePage;
    private static SearchResultsPage searchResultsPage;
    private final String DESTINATION = "Барселона";
    private final Integer ADULT_COUNT = 1;
    private final Integer CHILDREN_COUNT = 0;
    private final Integer ROOM_COUNT = 1;

    @Before
    public void setup() {
        Configuration.baseUrl = "https://www.booking.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void checkBookingDetails() {
        homePage = new HomePage();
        homePage.openPage()
                .enterDestination(DESTINATION)
                .enterDateRange()
                .enterGuestsCounts(List.of(ADULT_COUNT, CHILDREN_COUNT, ROOM_COUNT))
                .checkPrices();

        searchResultsPage = new SearchResultsPage();
        searchResultsPage.checkDestinationValue(DESTINATION)
                .checkDatesRange(homePage.getDatesRange())
                .checkOccupancy(List.of(ADULT_COUNT, CHILDREN_COUNT, ROOM_COUNT))
                .checkResultsCount();
    }
}
