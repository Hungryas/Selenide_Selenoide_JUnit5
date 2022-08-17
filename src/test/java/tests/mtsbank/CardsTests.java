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
                openHoverMenu("karti", "Карты");
    }

    @AllArgsConstructor
    @Getter
    private enum Cards {
        CREDIT(new CreditCardPage(), "karti/all/credit", "Кредитные карты"),
        DEBET(new DebetCardPage(), "karti/all/debet", "Дебетовые карты"),
        VIRTUAL(new VirtualCardPage(), "karti/all/virtual", "Виртуальные карты");

        private final CardPage cardPage;
        private final String subSection;
        private final String subSectionText;
    }

    @Epic("Desktop Tests")
    @Story("Тестирование верстки")
    @DisplayName("Проверка отображения в разделе только одного вида карт.")
    @ParameterizedTest
    @EnumSource(value = Cards.class)
    void checkCardNamesIsCorrect(Cards card) {
        MAIN_PAGE.openMenuPage(card.getSubSection(), card.getSubSectionText());
        CardPage cardPage = card.getCardPage();
        cardPage.checkCardName();
    }

    @Epic("Desktop Tests")
    @Story("Тестирование верстки")
    @DisplayName("Проверка отображения кнопок для каждой карты.")
    @ParameterizedTest
    @EnumSource(value = Cards.class)
    void checkCardButtonIsExist(Cards card) {
        MAIN_PAGE.openMenuPage(card.getSubSection(), card.getSubSectionText());
        CardPage cardPage = card.getCardPage();
        cardPage.checkCardButton();
    }
}