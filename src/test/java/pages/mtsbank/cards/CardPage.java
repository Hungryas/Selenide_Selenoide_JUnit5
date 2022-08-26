package pages.mtsbank.cards;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$x;
import static elements.PageContent.waitHeaderElementIsExist;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class CardPage {
    static String BUTTON_ORDER_TEXT = "Оформить карту";
    static String BUTTON_INFO_TEXT = "О карте";
    static By BUTTON_TEXTS = By.xpath("./div[starts-with(@class,'Desktop')]//div[contains(@class,'ButtonText')]");
    static ElementsCollection CARD_NAMES = $$x("//h2");
    static ElementsCollection CARD_CONTAINERS = $$x("//h2/ancestor::div[contains(@class,'styled__WrapperDescription')]");
    @NonFinal
    String linkText;
    @NonFinal
    String cardName;

    @Step("Проверить, что названия всех карт на странице содержат название раздела.")
    public void checkCardName() {
        waitHeaderElementIsExist();
        CARD_NAMES.asFixedIterable().forEach(e -> e.scrollTo()
                .shouldHave(text(getCardName())));
    }

    @Step("Проверить, что у каждой карты отображаются кнопки \"Оформить карту\" и \"О карте\".")
    public void checkCardButton() {
        waitHeaderElementIsExist();
        CARD_CONTAINERS.asDynamicIterable().forEach(e -> e.scrollTo().
                findAll(BUTTON_TEXTS).
                shouldHave(size(2)).
                shouldHave(itemWithText(BUTTON_ORDER_TEXT)).
                shouldHave(itemWithText(BUTTON_INFO_TEXT)));
    }
}