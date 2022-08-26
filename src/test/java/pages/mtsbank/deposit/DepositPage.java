package pages.mtsbank.deposit;

import elements.DepositInputForm;
import io.qameta.allure.Step;
import lombok.Getter;
import model.Client;

import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static elements.DepositInputForm.HelperText.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Getter
public abstract class DepositPage extends DepositInputForm {
    private String linkText;

    @Step("Ввести данные клиента: {client}")
    public DepositPage enterClientData(Client client) {
        FIO.shouldBe(exist).
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
    public void checkConfirmationFieldIsDisplayed() {
        CONFIRMATION_FIELD.shouldBe(visible);
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
