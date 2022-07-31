package tests.booking;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.booking.HomePage;
import pages.booking.MapFullOverlayPage;
import pages.booking.SearchResultsPage;

import java.time.LocalDate;
import java.util.List;

@Epic("Проверки поиска отелей на главной странице сервиса.")
public class BookingTest extends BaseTest {
    private static HomePage homePage = new HomePage();
    private static SearchResultsPage searchResultsPage = new SearchResultsPage();
    private static MapFullOverlayPage mapFullOverlayPage = new MapFullOverlayPage();

    private final String DESTINATION = "Барселона";
    private final Integer ADULT_COUNT = 1;
    private final Integer CHILDREN_COUNT = 0;
    private final Integer ROOM_COUNT = 1;
    private final Integer RATING = 5;
    private final Integer DATE_RANGE = 7;
    private final LocalDate CHECK_IN = LocalDate.now().plusDays(1);
    private final LocalDate CHECK_OUT = CHECK_IN.plusDays(DATE_RANGE);

    @Before
    public void preview() {
        homePage.openPage()
                .enterDestination(DESTINATION)
                .enterDateRange(CHECK_IN, CHECK_OUT)
                .enterGuestsCounts(List.of(ADULT_COUNT, CHILDREN_COUNT, ROOM_COUNT))
                .checkPrices();
    }

    @Test
    @DisplayName("Проверка параметров поиска.")
    @Description("Проверка соответствия результатов поиска введенным данным.")
    public void checkSearchDetails() {
        searchResultsPage.checkDestinationValue(DESTINATION)
                .checkDatesRange(CHECK_IN, CHECK_OUT, homePage.getDatesRange())
                .checkOccupancy(List.of(ADULT_COUNT, CHILDREN_COUNT, ROOM_COUNT))
                .checkResultsCount();
    }

    @Test
    @DisplayName("Проверка отображения результатов поиска с заданным рейтингом.")
    public void checkFilterByRating() {
        searchResultsPage.checkCardsCountFilteredByRating(RATING);
    }

    @Test
    @DisplayName("Проверка отображения результатов поиска с заданным рейтингом на оверлее карты.")
    public void checkFilterByRatingOnMapOverlay() {
        searchResultsPage.openMapFullOverlay();
        mapFullOverlayPage.checkCardsCountFilteredByRating(RATING);
    }
}
