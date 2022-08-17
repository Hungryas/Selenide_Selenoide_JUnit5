package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class DepositInputForm {
    SelenideElement FIO = $x("//textarea[@name='clientFio']");
    SelenideElement PHONE = $x("//input[@name='phoneNumber']");
    SelenideElement EMAIL = $x("//input[@name='email']");
    SelenideElement CHECKBOX_CONDITIONS = $x("//*[@name='allowProcessingConditions']/..");
    SelenideElement BUTTON_NEXT = $x("//form//*[@data-testid='button']");
    SelenideElement CONFIRMATION_FIELD = $x("//form//*[@data-testid='confirmationField']");

    ElementsCollection HELPER_TEXT = $$x("//form//*[contains(@class,'HelperText')]");

    @AllArgsConstructor
    @Getter
    public enum HelperText {
        WRONG_FIO("Введите ФИО полностью"),
        CYRILLIC("Используйте только кириллицу"),
        WRONG_PHONE("Введите верный номер телефона"),
        WRONG_EMAIL("Введите верный электронный адрес"),
        EMPTY("Обязательное поле"),
        FLAG("Установите этот флажок");

        private final String text;
    }
}
