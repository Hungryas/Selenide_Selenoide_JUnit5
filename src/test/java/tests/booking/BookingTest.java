package tests.booking;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.booking.HomePage;
import pages.booking.MapFullOverlayPage;
import pages.booking.SearchResultsPage;

import java.time.LocalDate;
import java.util.List;

@Epic("Проверки поиска отелей на главной странице сервиса.")
class BookingTest extends BaseTest {
    private static HomePage homePage = new HomePage();
    private static SearchResultsPage searchResultsPage = new SearchResultsPage();
    private static MapFullOverlayPage mapFullOverlayPage = new MapFullOverlayPage();

    private final static String DESTINATION = "Barcelona";
    private final static Integer ADULT_COUNT = 1;
    private final static Integer CHILDREN_COUNT = 0;
    private final static Integer ROOM_COUNT = 1;
    private final static Integer RATING = 5;
    private final static Long DATE_RANGE = 7L;
    private final static LocalDate CHECK_IN = LocalDate.now().plusDays(1);
    private final static LocalDate CHECK_OUT = CHECK_IN.plusDays(DATE_RANGE);

    @BeforeEach
    void preview() {
        homePage.openPage()
                .enterDestination(DESTINATION)
                .enterDateRange(CHECK_IN, CHECK_OUT)
                .enterGuestsCounts(List.of(ADULT_COUNT, CHILDREN_COUNT, ROOM_COUNT))
                .checkPrices();
    }

    @Test
    @DisplayName("Проверка параметров поиска.")
    @Description("Проверка соответствия результатов поиска введенным данным.")
    void checkSearchDetails() {
        searchResultsPage.checkDestinationValue(DESTINATION)
                .checkDatesRange(CHECK_IN, CHECK_OUT, DATE_RANGE)
                .checkOccupancy(List.of(ADULT_COUNT, CHILDREN_COUNT, ROOM_COUNT))
                .checkResultsCount();
    }

    @Test
    @DisplayName("Проверка отображения результатов поиска с заданным рейтингом.")
    void checkFilterByRating() {
        searchResultsPage.checkCardsCountFilteredByRating(RATING);
    }

    @Test
    @DisplayName("Проверка отображения результатов поиска с заданным рейтингом на оверлее карты.")
    void checkFilterByRatingOnMapOverlay() {
        searchResultsPage.openMapFullOverlay();
        mapFullOverlayPage.checkCardsCountFilteredByRating(RATING);
    }
}
