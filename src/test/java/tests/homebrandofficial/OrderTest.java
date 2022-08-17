package tests.homebrandofficial;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.homebrandofficial.HomePage;
import pages.homebrandofficial.OrderPage;
import pages.homebrandofficial.ProductPage;
import pages.homebrandofficial.WearPage;

@Epic("Проверки отправки и отмены заказа.")
class OrderTest extends BaseTest {
    private static HomePage homePage = new HomePage();
    private static WearPage wearPage = new WearPage();
    private static ProductPage productPage = new ProductPage();
    private static OrderPage orderPage = new OrderPage();

    private final static String PRODUCT_NAME = "Футболка оверсайз белая";
    private final static String TEST_NAME = "Тестов Тест Тестович";
    private final static String TEST_PHONE = "0000000000";
    private final static String TEST_TEXT = "Тест";
    private final static Integer TEST_NUMBER = 1;
    private final static String ERROR_PHONE_TEXT = "Укажите, пожалуйста, корректный номер телефона";

    @BeforeEach
    void preview() {
        homePage.openPage()
                .openCatalogWear();
        wearPage.openProductCard(PRODUCT_NAME);
        productPage.addToCart();
    }

    @Test
    @DisplayName("Проверка неуспешной отправки заказа при указании некорректного номера телефона.")
    @Description("Невозможно отправить заказ при указании некорректного номера телефона. Ошибка отображается.")
    void orderWithPhoneError() {
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
    void cancelOrder() {
        orderPage.cancelOrder()
                .checkTimerIsExist()
                .checkTimerTitle(String.format("Вы удалили \"%s\"", PRODUCT_NAME))
                .checkTimerIsNotExist();

        productPage.checkTitle(PRODUCT_NAME)
                .checkCartIconIsNotVisible();
    }
}
