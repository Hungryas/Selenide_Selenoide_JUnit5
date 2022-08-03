package tests.homebrandofficial;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.homebrandofficial.HomePage;
import pages.homebrandofficial.WearPage;

@Epic("Проверки сортировки товаров на странице каталога \"Одежда\".")
public class SortProductsTest extends BaseTest {
    private static HomePage homePage = new HomePage();
    private static WearPage wearPage = new WearPage();

    @Before
    public void preview() {
        homePage.openPage()
                .openCatalogWear();
    }

    @Test
    @DisplayName("Проверка порядка сортировки \"Цена: по возрастанию\".")
    @Description("Проверка порядка сортировки \"Цена: по возрастанию\" всех цен на странице.")
    public void checkSortAscByPrice() {
        wearPage.selectSortingOrder("price:asc").checkPricesOrder();
    }
}
