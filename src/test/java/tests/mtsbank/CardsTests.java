package tests.mtsbank;


import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.mtsbank.MainPage;
import pages.mtsbank.cards.CardPage;
import pages.mtsbank.cards.CreditCardPage;
import pages.mtsbank.cards.DebetCardPage;
import pages.mtsbank.cards.VirtualCardPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CardsTests extends BaseTest {
    private static final MainPage MAIN_PAGE = new MainPage();

    @BeforeEach
    void preview() {
        MAIN_PAGE.openMainPage().
                openHoverMenu("Карты");
    }

    private static Stream<Arguments> cardPages() {
        return Stream.of(
                arguments(named("Кредитные карты", new CreditCardPage())),
                arguments(named("Дебетовые карты", new DebetCardPage())),
                arguments(named("Виртуальные карты", new VirtualCardPage())));
    }

    @Epic("Desktop Tests")
    @Story("Тестирование верстки")
    @DisplayName("Проверка отображения в разделе только одного вида карт.")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("cardPages")
    void checkCardNamesIsCorrect(CardPage cardPage) {
        MAIN_PAGE.openMenuPage(cardPage.getLinkText());
        cardPage.checkCardName();
    }

    @Epic("Desktop Tests")
    @Story("Тестирование верстки")
    @DisplayName("Проверка отображения кнопок для каждой карты.")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("cardPages")
    void checkCardButtonIsExist(CardPage cardPage) {
        MAIN_PAGE.openMenuPage(cardPage.getLinkText());
        cardPage.checkCardButton();
    }
}