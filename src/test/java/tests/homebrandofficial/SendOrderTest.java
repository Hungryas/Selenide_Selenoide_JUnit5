package tests.homebrandofficial;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.homebrandofficial.HomePage;
import pages.homebrandofficial.OrderPage;
import pages.homebrandofficial.ProductPage;
import pages.homebrandofficial.WearPage;
import tests.BaseTest;

public class SendOrderTest extends BaseTest {
    private final String PRODUCT_NAME = "Футболка поло черная (м)";
    private final String TEST_NAME = "Тестов Тест Тестович";
    private final String TEST_PHONE = "0000000000";
    private final String TEST_TEXT = "Тест";
    private final String ERROR_PHONE_TEXT = "Укажите, пожалуйста, корректный номер телефона";

    private static HomePage homePage;
    private static WearPage wearPage;
    private static ProductPage productPage;
    private static OrderPage orderPage;

    @Before
    public void preview() {
        homePage = new HomePage();
        homePage.openPage()
                .openCatalogWear();

        wearPage = new WearPage();
        wearPage.openPage().openProductCard(PRODUCT_NAME);

        productPage = new ProductPage();
        productPage.addToCart();
    }

    @Test
    public void orderWithPhoneError() {
        orderPage = new OrderPage();
        orderPage.enterData(orderPage.FIELD_NAME, TEST_NAME)
                .enterData(orderPage.FIELD_PHONE, TEST_PHONE)
                .enterData(orderPage.FIELD_REGION, TEST_TEXT)
                .enterData(orderPage.FIELD_ADDRESS, TEST_TEXT)
                .enterData(orderPage.FIELD_DELIVERY_USER, TEST_NAME)
                .enterData(orderPage.FIELD_DELIVERY_STREET)
                .enterData(orderPage.FIELD_DELIVERY_HOUSE)
                .enterData(orderPage.FIELD_DELIVERY_APTOFFICE, String.valueOf(Math.round(10 * Math.random() + 1)))
                .submitOrder();

        Assert.assertTrue(orderPage.checkErrorIsDisplayed(ERROR_PHONE_TEXT));
    }
}
