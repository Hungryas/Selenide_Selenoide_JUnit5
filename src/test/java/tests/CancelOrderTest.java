package tests;

import org.junit.Before;
import org.junit.Test;
import pages.HomePage;
import pages.OrderPage;
import pages.ProductPage;
import pages.WearPage;

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
