package pages.mtsbank;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    private final static String MENU_LOCATOR = "//a[@href='/chastnim-licam/%s/']/div";
    private final static String SUBMENU_LOCATOR = "//div/a[@href='/chastnim-licam/%s/']";

    @Step("Открыть главную страницу.")
    public MainPage openMainPage() {
        open(baseUrl);
        return this;
    }

    @Step("Открыть меню \"{sectionText}\".")
    public MainPage openHoverMenu(String section, String sectionText) {
        final SelenideElement sectionLink = $x(String.format(MENU_LOCATOR, section));
        sectionLink.shouldHave(text(sectionText)).shouldBe(visible).hover();
        return this;
    }

    @Step("Выбрать раздел \"{subSectionText}\".")
    public void openMenuPage(String subSection, String subSectionText) {
        final SelenideElement cardPageLink = $x(String.format(SUBMENU_LOCATOR, subSection));
        cardPageLink.shouldHave(text(subSectionText)).shouldBe(visible).click();
    }


}