package tests.mtsbank;


import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pages.mtsbank.MainPage;
import pages.mtsbank.cards.CardPage;
import pages.mtsbank.cards.CreditCardPage;
import pages.mtsbank.cards.DebetCardPage;
import pages.mtsbank.cards.VirtualCardPage;

class CardsTests extends BaseTest {
    private static final MainPage MAIN_PAGE = new MainPage();

    @BeforeEach
    void preview() {
        MAIN_PAGE.openMainPage().
                openHoverMenu("chastnim-licam/karti", "Карты");
    }

    @AllArgsConstructor
    @Getter
    private enum Cards {
        CREDIT(new CreditCardPage()),
        DEBET(new DebetCardPage()),
        VIRTUAL(new VirtualCardPage());

        private final CardPage cardPage;

    }

    @Epic("Desktop Tests")
    @Story("Тестирование верстки")
    @DisplayName("Проверка отображения в разделе только одного вида карт.")
    @ParameterizedTest
    @EnumSource(value = Cards.class)
    void checkCardNamesIsCorrect(Cards card) {
        CardPage cardPage = card.getCardPage();
        MAIN_PAGE.openMenuPage(cardPage.getRelativeUrl(), cardPage.getSubsectionText());
        cardPage.checkCardName();
    }

    @Epic("Desktop Tests")
    @Story("Тестирование верстки")
    @DisplayName("Проверка отображения кнопок для каждой карты.")
    @ParameterizedTest
    @EnumSource(value = Cards.class)
    void checkCardButtonIsExist(Cards card) {
        CardPage cardPage = card.getCardPage();
        MAIN_PAGE.openMenuPage(cardPage.getRelativeUrl(), cardPage.getSubsectionText());
        cardPage.checkCardButton();
    }
}