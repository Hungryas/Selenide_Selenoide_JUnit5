package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    private final SelenideElement BUTTON_ADD_TO_CART = $("a[href='#order']");
    private final SelenideElement CART_ICON = $(".t706__carticon-imgwrap");
    private final SelenideElement PRODUCT_TITLE = $("h1.js-store-prod-name");

    public void addToCart() {
        BUTTON_ADD_TO_CART.click();
    }

    public ProductPage checkTitle(String productName) {
        PRODUCT_TITLE.shouldHave(text(productName));
        return this;
    }

    public void checkCartIconIsNotVisible(){
        CART_ICON.shouldNotBe(visible);
    }
}
