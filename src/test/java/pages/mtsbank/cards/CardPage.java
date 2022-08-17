package pages.mtsbank.cards;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$x;
import static elements.PageContent.waitHeaderElementIsExist;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class CardPage {
    String cardName = null;
    static String BUTTON_ORDER_TEXT = "Оформить карту";
    static String BUTTON_INFO_TEXT = "О карте";
    static ElementsCollection CARD_NAME_LIST = $$x("//h2");

    @Step("Проверить, что названия всех карт на странице содержат название раздела.")
    public void checkCardName() {
        waitHeaderElementIsExist();

        Integer expectedCount = CARD_NAME_LIST.shouldHave(sizeGreaterThan(0)).size();
        Integer actualCount = CARD_NAME_LIST.filterBy(text(getCardName())).size();
        assertEquals(expectedCount, actualCount);
    }

    @Step("Проверить, что у каждой карты отображаются кнопки \"Оформить карту\" и \"О карте\".")
    public void checkCardButton() {
        waitHeaderElementIsExist();

        final ElementsCollection CARD_CONTAINERS = $$x("//h2/ancestor::div[contains(@class,'styled__WrapperDescription')]").
                shouldHave(sizeGreaterThan(0));

        for (SelenideElement element : CARD_CONTAINERS) {
            element.$$x("./div[starts-with(@class,'Desktop')]//div[contains(@class,'ButtonText')]").
                    shouldHave(size(2)).
                    shouldHave(itemWithText(BUTTON_ORDER_TEXT)).
                    shouldHave(itemWithText(BUTTON_INFO_TEXT));
        }
    }
}
