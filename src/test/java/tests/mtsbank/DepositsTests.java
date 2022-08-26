package tests.mtsbank;


import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.mtsbank.MainPage;
import pages.mtsbank.deposit.DepositPage;
import pages.mtsbank.deposit.DohodniyPage;
import pages.mtsbank.deposit.SchetPage;
import pages.mtsbank.deposit.VkladPage;

import java.util.stream.Stream;

import static elements.PageContent.waitHeaderElementIsExist;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DepositsTests extends BaseTest {

    private static final MainPage MAIN_PAGE = new MainPage();

    @BeforeEach
    void preview() {
        MAIN_PAGE.openMainPage().
                openHoverMenu("Вклады и счета");
    }

    private static Stream<Arguments> depositPages() {
        return Stream.of(
                arguments(named("МТС Вклад", new VkladPage())),
                arguments(named("МТС Доходный", new DohodniyPage())),
                arguments(named("Накопительный МТС Счет", new SchetPage())));
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада с корректным заполнением всех полей.")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("depositPages")
    void trySendCorrectData(DepositPage depositPage) {
        MAIN_PAGE.openMenuPage(depositPage.getLinkText());
        Client client = new Client();

        depositPage.
                enterClientData(client).
                clickNext().
                checkConfirmationFieldIsDisplayed();
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада без указания ФИО.")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("depositPages")
    void trySendDataWithoutFIO(DepositPage depositPage) {
        MAIN_PAGE.openMenuPage(depositPage.getLinkText());
        Client client = new Client().toBuilder().
                withFIO("").build();

        depositPage.
                enterClientData(client).
                clickNext().
                helperTextIsDisplayed("FIO");
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада с указанием некорректного ФИО.")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("depositPages")
    void trySendDataWithWrongFIO(DepositPage depositPage) {
        MAIN_PAGE.openMenuPage(depositPage.getLinkText());
        Client client = new Client().toBuilder().
                withFIO("a!,").build();

        depositPage.
                enterClientData(client).
                clickNext().
                helperTextIsDisplayed("CYRILLIC");
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада без указания номера телефона.")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("depositPages")
    void trySendDataWithoutPhone(DepositPage depositPage) {
        MAIN_PAGE.openMenuPage(depositPage.getLinkText());
        Client client = new Client().toBuilder().
                withPhone("").build();

        depositPage.
                enterClientData(client).
                clickNext().
                helperTextIsDisplayed("PHONE");
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада с указанием некорректного номера телефона.")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("depositPages")
    void trySendDataWithWrongPhone(DepositPage depositPage) {
        MAIN_PAGE.openMenuPage(depositPage.getLinkText());
        Client client = new Client().toBuilder().
                withPhone("800").build();

        depositPage.
                enterClientData(client).
                clickNext().
                helperTextIsDisplayed("PHONE");
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада без указания email.")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("depositPages")
    void trySendDataWithoutEmail(DepositPage depositPage) {
        MAIN_PAGE.openMenuPage(depositPage.getLinkText());
        Client client = new Client().toBuilder().
                withEmail("").build();

        depositPage.
                enterClientData(client).
                clickNext().
                checkConfirmationFieldIsDisplayed();
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада с указанием некорректного email.")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("depositPages")
    void trySendDataWithWrongEmail(DepositPage depositPage) {
        MAIN_PAGE.openMenuPage(depositPage.getLinkText());
        Client client = new Client().toBuilder().
                withEmail("ivanov@mail,ru").build();

        depositPage.
                enterClientData(client).
                clickNext().
                helperTextIsDisplayed("EMAIL");
    }


    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада без согласия на обработку персональных данных.")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("depositPages")
    void trySendDataWithoutConditionAgree(DepositPage depositPage) {
        MAIN_PAGE.openMenuPage(depositPage.getLinkText());
        Client client = new Client();

        depositPage.
                enterClientData(client).
                uncheckedConditionAgree().
                clickNext().
                helperTextIsDisplayed("FLAG");
    }

    @Epic("Desktop Tests")
    @Story("Проверка заполнения полей форм.")
    @DisplayName("Заказать карту для вклада без заполнения полей.")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("depositPages")
    void trySendWithoutData(DepositPage depositPage) {
        MAIN_PAGE.openMenuPage(depositPage.getLinkText());
        waitHeaderElementIsExist();
        depositPage.
                clickNext().
                helperTextIsDisplayed("FIO").
                helperTextIsDisplayed("EMPTY");
    }
}