package tests.homebrandofficial;

import org.junit.Before;
import org.junit.Test;
import pages.homebrandofficial.HomePage;
import pages.homebrandofficial.OrderPage;
import pages.homebrandofficial.ProductPage;
import pages.homebrandofficial.WearPage;
import tests.BaseTest;

public class SendOrderTest extends BaseTest {
    private static HomePage homePage;
    private static WearPage wearPage;
    private static ProductPage productPage;
    private static OrderPage orderPage;

    private final String PRODUCT_NAME = "Футболка поло черная (м)";
    private final String TEST_NAME = "Тестов Тест Тестович";
    private final String TEST_PHONE = "0000000000";
    private final String TEST_TEXT = "Тест";
    private final String TEST_NUMBER = String.valueOf(Math.round(10 * Math.random() + 1));
    private final String ERROR_PHONE_TEXT = "Укажите, пожалуйста, корректный номер телефона";

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
        orderPage.enterName(TEST_NAME)
                .enterPhone(TEST_PHONE)
                .enterRegion(TEST_TEXT)
                .enterAddress(TEST_TEXT)
                .enterDeliveryUser(TEST_NAME)
                .chooseSuggestedStreet()
                .chooseSuggestedHouse()
                .enterAptOffice(TEST_NUMBER)
                .submitOrder()
                .checkErrorIsDisplayed(ERROR_PHONE_TEXT);
    }
}
