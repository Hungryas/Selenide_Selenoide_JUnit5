package tests.homebrandofficial;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.homebrandofficial.HomePage;
import pages.homebrandofficial.OrderPage;
import pages.homebrandofficial.ProductPage;
import pages.homebrandofficial.WearPage;

@Epic("Проверки отправки и отмены заказа.")
public class OrderTest extends BaseTest {
    private static HomePage homePage = new HomePage();
    private static WearPage wearPage = new WearPage();
    private static ProductPage productPage = new ProductPage();
    private static OrderPage orderPage = new OrderPage();

    private final String PRODUCT_NAME = "Футболка оверсайз белая";
    private final String TEST_NAME = "Тестов Тест Тестович";
    private final String TEST_PHONE = "0000000000";
    private final String TEST_TEXT = "Тест";
    private final Integer TEST_NUMBER = 1;
    private final String ERROR_PHONE_TEXT = "Укажите, пожалуйста, корректный номер телефона";

    @Before
    public void preview() {
        homePage.openPage()
                .openCatalogWear();
        wearPage.openProductCard(PRODUCT_NAME);
        productPage.addToCart();
    }

    @Test
    @DisplayName("Проверка неуспешной отправки заказа при указании некорректного номера телефона.")
    @Description("Невозможно отправить заказ при указании некорректного номера телефона. Ошибка отображается.")
    public void orderWithPhoneError() {
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

    @Test
    @DisplayName("Проверка отмены заказа.")
    @Description("Отмена заказа путем удаления всех товаров из корзины. Отображается таймер отмены и подтверждение.")
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
