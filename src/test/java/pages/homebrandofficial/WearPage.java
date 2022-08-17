package pages.homebrandofficial;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.homebrandofficial.BaseTest.cfg;

public class WearPage {

    private final ElementsCollection PRODUCTS_LIST_BY_NAME = $$("a .js-product-name");
    private final ElementsCollection SORT_SELECT_OPTIONS = $$(".t-store__sort-select option");
    private final ElementsCollection PRODUCTS_PRICE_TEXT = $$("div[data-product-price-def]");

    @Step("Открыть страницу каталога \"Одежда\".")
    public WearPage openPage() {
        open(cfg.baseUrl() + "/wear");
        return this;
    }

    @Step("Открыть карточку товара: {productName}")
    public void openProductCard(String productName) {
        PRODUCTS_LIST_BY_NAME.shouldHave(sizeGreaterThan(0)).findBy(text(productName)).click();
    }

    @Step("Выбрать метод сортировки: {value}")
    public WearPage selectSortingOrder(String value) {
        SORT_SELECT_OPTIONS.shouldHave(sizeGreaterThan(0)).findBy(attribute("value", value)).click();
        return this;
    }

    @Step("Проверить порядок сортировки.")
    public WearPage checkPricesOrder() {
        // TODO ISSUE Element is not attached to the page document
        sleep(1000);
        PRODUCTS_PRICE_TEXT.shouldHave(size(24));
        List<Integer> actualPricesOrder = PRODUCTS_PRICE_TEXT.stream()
                .map(e -> e.getAttribute("data-product-price-def")).filter(Objects::nonNull)
                .map(Integer::parseInt).toList();
        List<Integer> expectedPricesOrder = actualPricesOrder.stream().sorted().toList();
        assertEquals(expectedPricesOrder, actualPricesOrder, "Sort error!");
        return this;
    }
}