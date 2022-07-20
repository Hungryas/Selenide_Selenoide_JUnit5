package tests.homebrandofficial;

import org.junit.Assert;
import org.junit.Test;
import pages.homebrandofficial.HomePage;
import pages.homebrandofficial.WearPage;
import tests.BaseTest;

public class SortProductsTest extends BaseTest {
    private static HomePage homePage ;
    private static WearPage wearPage;

    @Test()
    public void checkSortAscByPrice() {
        homePage = new HomePage();
        homePage.openPage()
                .openCatalogWear();

        wearPage = new WearPage();
        wearPage.selectSortingOrder("price:asc");

        Assert.assertTrue("Ошибка сортировки!", wearPage.checkPricesOrder());
    }
}
