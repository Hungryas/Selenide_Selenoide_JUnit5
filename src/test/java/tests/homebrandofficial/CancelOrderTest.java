package tests.homebrandofficial;

import org.junit.Before;
import org.junit.Test;
import pages.homebrandofficial.HomePage;
import pages.homebrandofficial.OrderPage;
import pages.homebrandofficial.ProductPage;
import pages.homebrandofficial.WearPage;
import tests.BaseTest;

public class CancelOrderTest extends BaseTest {
    private final String PRODUCT_NAME = "Футболка оверсайз белая";
    private static HomePage homePage ;
    private static WearPage wearPage;
    private static ProductPage productPage;
    private static OrderPage orderPage;

    @Before
    public void preview() {
        homePage = new HomePage();
        homePage.openPage()
                .openCatalogWear();

        wearPage = new WearPage();
        wearPage.openProductCard(PRODUCT_NAME);

        productPage = new ProductPage();
        productPage.addToCart();
    }

    @Test
    public void cancelOrder() {
        orderPage = new OrderPage();
        orderPage.cancelOrder()
                .checkTimerIsExist()
                .checkTimerTitle(String.format("Вы удалили \"%s\"", PRODUCT_NAME))
                .checkTimerIsNotExist();

        productPage.checkTitle(PRODUCT_NAME)
                .checkCartIconIsNotVisible();
    }
}
