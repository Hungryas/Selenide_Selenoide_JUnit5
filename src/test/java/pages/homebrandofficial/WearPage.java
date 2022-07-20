package pages.homebrandofficial;

import com.codeborne.selenide.ElementsCollection;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static tests.BaseTest.cfg;

public class WearPage {

    private final ElementsCollection PRODUCTS_LIST_BY_NAME = $$("a .js-product-name");
    private final ElementsCollection SORT_SELECT_OPTIONS = $$(".t-store__sort-select option");
    private final ElementsCollection PRODUCTS_PRICE_TEXT = $$("div[data-product-price-def]");

    public WearPage openPage() {
        open(cfg.baseUrl() + "/wear");
        return this;
    }

    public void openProductCard(String productName) {
        PRODUCTS_LIST_BY_NAME.shouldHave(sizeGreaterThan(0)).findBy(text(productName)).click();
    }

    public WearPage selectSortingOrder(String value) {
        SORT_SELECT_OPTIONS.shouldHave(sizeGreaterThan(0)).findBy(attribute("value", value)).click();
        return this;
    }

    public boolean checkPricesOrder() {
        // TODO issue Element is not attached to the page document
        sleep(1000);
        PRODUCTS_PRICE_TEXT.shouldHave(size(24));
        List<Integer> actualPricesOrder = PRODUCTS_PRICE_TEXT.stream()
                .map(e -> e.getAttribute("data-product-price-def")).filter(Objects::nonNull)
                .map(Integer::parseInt).toList();
        List<Integer> expectedPricesOrder = actualPricesOrder.stream().sorted().toList();
        return expectedPricesOrder.equals(actualPricesOrder);
    }
}