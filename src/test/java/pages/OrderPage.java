package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OrderPage {
    private final ElementsCollection FORM_CART = $$("*[data-formcart] [name]");

    public final SelenideElement FIELD_NAME = FORM_CART.find(name("Name"));
    public final SelenideElement FIELD_PHONE = FORM_CART.find(name("tildaspec-phone-part[]"));
    public final SelenideElement FIELD_REGION = FORM_CART.find(name("Карта региона или страны. Мы добавим её в логотип"));
    public final SelenideElement FIELD_ADDRESS = FORM_CART.find(name("Адрес для доставки"));

    public final SelenideElement FIELD_DELIVERY_USER = FORM_CART.find(name("tildadelivery-userinitials"));
    public final SelenideElement FIELD_DELIVERY_STREET = FORM_CART.find(name("tildadelivery-street"));
    public final SelenideElement FIELD_DELIVERY_HOUSE = FORM_CART.find(name("tildadelivery-house"));
    public final SelenideElement FIELD_DELIVERY_APTOFFICE = FORM_CART.find(name("tildadelivery-aptoffice"));

    public final SelenideElement BUTTON_SUBMIT = $("*[data-formcart] button[type='submit']");
    public final SelenideElement ERROR_BOX_ITEM = $(".t-form__errorbox-middle p[style='display: block;']");
    public final SelenideElement INPUT_ERROR = $(".js-error-control-box div.t-input-error");

    private final SelenideElement SEARCHBOX_LIST_ITEM = $(".searchbox-list-item.t-text");
    private final SelenideElement DELETE_IMG = $(".t706__product-del img");
    private final SelenideElement TIMER_RETURN = $(".t706__product-deleted__timer__return");
    private final SelenideElement TIMER_TITLE = $(".t706__product-deleted__timer__title");

    public OrderPage enterData(SelenideElement element, String data) {
        element.clear();
        element.sendKeys(data);
        return this;
    }

    public OrderPage enterData(SelenideElement element) {
        element.click();
        SEARCHBOX_LIST_ITEM.click();
        return this;
    }

    public void submitOrder() {
        BUTTON_SUBMIT.click();
    }

    public boolean checkErrorIsDisplayed(String errorText) {
        return ERROR_BOX_ITEM.shouldHave(text(errorText)).isDisplayed() &&
                INPUT_ERROR.shouldHave(text(errorText)).isDisplayed();
    }

    public OrderPage cancelOrder() {
        DELETE_IMG.click();
        return this;
    }

    public OrderPage checkTimerIsExist() {
        TIMER_RETURN.shouldBe(appear);
        return this;
    }

    public OrderPage checkTimerIsNotExist() {
        TIMER_RETURN.shouldNotBe(appear, Duration.ofSeconds(5));
        return this;
    }

    public OrderPage checkTimerTitle(String title) {
        TIMER_TITLE.shouldHave(text(title));
        return this;
    }
}
