package pages.mtsbank;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    private final static ElementsCollection MENU_HOLDER = $$x("//*[contains(@class, 'MenuHolder')]/*[contains(@href, 'chastnim-licam')]")
            .as("MENU_HOLDER");
    private final static ElementsCollection DROP_DOWN_HOLDER = $$x("//*[contains(@class, 'DropDownHolder')]//*[contains(@href, 'chastnim-licam')]")
            .as("DROP_DOWN_HOLDER");

    @Step("Открыть главную страницу.")
    public MainPage openMainPage() {
        open(baseUrl);
        return this;
    }

    @Step("Открыть меню \"{linkText}\".")
    public MainPage openHoverMenu(String linkText) {
        MENU_HOLDER.findBy(text(linkText)).shouldBe(visible).hover();
        return this;
    }

    @Step("Выбрать раздел \"{linkText}\".")
    public void openMenuPage(String linkText) {
        DROP_DOWN_HOLDER.findBy(text(linkText)).shouldBe(visible).click();
    }


}