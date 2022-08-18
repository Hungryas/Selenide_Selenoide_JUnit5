package pages.mtsbank.deposit;

import elements.DepositInputForm;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import model.Client;

import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static elements.DepositInputForm.HelperText.*;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class DepositPage extends DepositInputForm {
    String relativeUrl = null;
    String subsectionText = null;

    @Step("Ввести данные клиента: {client}")
    public DepositPage enterClientData(Client client) {
        FIO.shouldBe(exist, ofSeconds(60)).
                scrollTo().
                sendKeys(client.getFIO());
        PHONE.sendKeys(client.getPhone());
        EMAIL.sendKeys(client.getEmail());
        return this;
    }

    @Step("Снять флаг согласия на обработку данных.")
    public DepositPage uncheckedConditionAgree() {
        if (!CHECKBOX_CONDITIONS.isSelected()) CHECKBOX_CONDITIONS.click();
        return this;
    }

    @Step("Нажать кнопку \"Далее\".")
    public DepositPage clickNext() {
        BUTTON_NEXT.click();
        return this;
    }

    @Step("Проверка отображения поля ввода кода подтверждения из СМС.")
    public DepositPage confirmationFieldIsDisplayed() {
        CONFIRMATION_FIELD.shouldBe(visible);
        return this;
    }

    @Step("Проверка отображения текста для некорректно заполненного поля.")
    public DepositPage helperTextIsDisplayed(String helperText) {
        List<String> helperTexts = HELPER_TEXT.texts();
        switch (helperText) {
            case "FIO" -> assertTrue(helperTexts.contains(WRONG_FIO.getText()));
            case "CYRILLIC" -> assertTrue(helperTexts.contains(CYRILLIC.getText()));
            case "PHONE" -> assertTrue(helperTexts.contains(WRONG_PHONE.getText()));
            case "EMAIL" -> assertTrue(helperTexts.contains(WRONG_EMAIL.getText()));
            case "FLAG" -> assertTrue(helperTexts.contains(FLAG.getText()));
            case "EMPTY" -> assertTrue(helperTexts.contains(EMPTY.getText()));
            default -> throw new IllegalStateException("Unexpected value: " + helperText);
        }
        return this;
    }
}
