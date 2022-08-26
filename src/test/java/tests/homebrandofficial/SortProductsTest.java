package tests.homebrandofficial;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.homebrandofficial.HomePage;
import pages.homebrandofficial.WearPage;

@Epic("Проверки сортировки товаров на странице каталога \"Одежда\".")
class SortProductsTest extends BaseTest {
    private static HomePage homePage = new HomePage();
    private static WearPage wearPage = new WearPage();

    @BeforeEach
    public void preview() {
        homePage.openPage()
                .openCatalogWear();
    }

    @Test
    @DisplayName("Проверка порядка сортировки \"Цена: по возрастанию\".")
    @Description("Проверка порядка сортировки \"Цена: по возрастанию\" всех цен на странице.")
    void checkSortAscByPrice() {
        wearPage.selectSortingOrder("price:asc")
                .checkPricesSort();
    }
}
