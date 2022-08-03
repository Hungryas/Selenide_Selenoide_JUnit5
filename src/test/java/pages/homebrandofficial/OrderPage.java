package pages.homebrandofficial;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.Assert.assertTrue;

public class OrderPage {
    private final ElementsCollection FORM_CART = $$("[data-formcart] [name]");

    private final SelenideElement FIELD_NAME = FORM_CART.findBy(name("Name"));
    private final SelenideElement FIELD_PHONE = FORM_CART.findBy(name("tildaspec-phone-part[]"));
    private final SelenideElement FIELD_REGION = FORM_CART.findBy(name("Карта региона или страны. Мы добавим её в логотип"));
    private final SelenideElement FIELD_ADDRESS = FORM_CART.findBy(name("Адрес для доставки"));

    private final SelenideElement FIELD_DELIVERY_USER = FORM_CART.findBy(name("tildadelivery-userinitials"));
    private final SelenideElement FIELD_DELIVERY_STREET = FORM_CART.findBy(name("tildadelivery-street"));
    private final SelenideElement FIELD_DELIVERY_HOUSE = FORM_CART.findBy(name("tildadelivery-house"));
    private final SelenideElement FIELD_DELIVERY_APTOFFICE = FORM_CART.findBy(name("tildadelivery-aptoffice"));

    private final SelenideElement BUTTON_SUBMIT = $("[data-formcart] button[type='submit']");
    private final SelenideElement ERROR_BOX_ITEM = $(".t-form__errorbox-middle p[style='display: block;']");
    private final SelenideElement INPUT_ERROR = $(".js-error-control-box div.t-input-error");

    private final SelenideElement SEARCHBOX_LIST_ITEM = $(".searchbox-list-item.t-text");
    private final SelenideElement DELETE_IMG = $(".t706__product-del img");
    private final SelenideElement TIMER_RETURN = $(".t706__product-deleted__timer__return");
    private final SelenideElement TIMER_TITLE = $(".t706__product-deleted__timer__title");

    @Step("Ввести ФИО полностью*: {data}")
    public OrderPage enterName(String data) {
        FIELD_NAME.clear();
        FIELD_NAME.sendKeys(data);
        return this;
    }

    @Step("Ввести Телефон*: {data}")
    public OrderPage enterPhone(String data) {
        FIELD_PHONE.clear();
        FIELD_PHONE.sendKeys(data);
        return this;
    }

    @Step("Ввести карту региона или страны. Мы добавим её в логотип: {data}")
    public OrderPage enterRegion(String data) {
        FIELD_REGION.clear();
        FIELD_REGION.sendKeys(data);
        return this;
    }

    @Step("Ввести Адрес для доставки*: {data}")
    public OrderPage enterAddress(String data) {
        FIELD_ADDRESS.clear();
        FIELD_ADDRESS.sendKeys(data);
        return this;
    }

    @Step("Ввести имя получателя: {data}")
    public OrderPage enterDeliveryUser(String data) {
        FIELD_DELIVERY_USER.clear();
        FIELD_DELIVERY_USER.sendKeys(data);
        return this;
    }

    @Step("Выбрать улицу из предложенных.")
    public OrderPage chooseSuggestedStreet() {
        FIELD_DELIVERY_STREET.click();
        SEARCHBOX_LIST_ITEM.click();
        return this;
    }

    @Step("Выбрать номер дома из предложенных.")
    public OrderPage chooseSuggestedHouse() {
        FIELD_DELIVERY_HOUSE.click();
        SEARCHBOX_LIST_ITEM.click();
        return this;
    }

    @Step("Ввести квартиру/офис: {data}")
    public OrderPage enterAptOffice(Integer data) {
        FIELD_DELIVERY_APTOFFICE.clear();
        FIELD_DELIVERY_APTOFFICE.sendKeys(data.toString());
        return this;
    }

    @Step("Нажать кнопку \"Оформить заказ\".")
    public OrderPage submitOrder() {
        BUTTON_SUBMIT.click();
        return this;
    }

    @Step("Проверить, что отображается текст ошибки: {errorText}")
    public OrderPage checkErrorIsDisplayed(String errorText) {
        assertTrue(ERROR_BOX_ITEM.shouldHave(text(errorText)).isDisplayed() &&
                INPUT_ERROR.shouldHave(text(errorText)).isDisplayed());
        return this;
    }

    @Step("Нажать на иконку удаления товара.")
    public OrderPage cancelOrder() {
        DELETE_IMG.click();
        return this;
    }

    @Step("Проверить, что отображается таймер отмены.")
    public OrderPage checkTimerIsExist() {
        TIMER_RETURN.shouldBe(appear);
        return this;
    }

    @Step("Проверить, что таймер истек.")
    public OrderPage checkTimerIsNotExist() {
        TIMER_RETURN.shouldNotBe(appear, Duration.ofSeconds(5));
        return this;
    }

    @Step("Проверить, что отображается подтверждение: {title}")
    public OrderPage checkTimerTitle(String title) {
        TIMER_TITLE.shouldHave(text(title));
        return this;
    }
}
