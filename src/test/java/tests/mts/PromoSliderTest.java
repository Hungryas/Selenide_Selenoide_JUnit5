package tests.mts;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.mts.HomePage;

@Epic("Проверки верстки главной страницы.")
public class PromoSliderTest extends BaseTest {
    private static HomePage homePage = new HomePage();

    @Test
    @DisplayName("Проверка слайдера промо-обложек.")
    @Description("Проверка смены промо-обложки при истечении таймера перехода.")
    public void checkPromoSlider() {
        homePage.openPage()
                .checkPromoSliderChanging();
    }
}
