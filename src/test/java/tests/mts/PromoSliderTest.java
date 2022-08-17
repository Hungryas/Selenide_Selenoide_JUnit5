package tests.mts;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.mts.HomePage;

@Epic("Проверки верстки главной страницы.")
class PromoSliderTest extends BaseTest {
    private static HomePage homePage = new HomePage();

    @Test
    @DisplayName("Проверка слайдера промо-обложек.")
    @Description("Проверка смены промо-обложки при истечении таймера перехода.")
    public void checkPromoSlider() {
        homePage.openPage()
                .checkPromoSliderChanging();
    }
}
