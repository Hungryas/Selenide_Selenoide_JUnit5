package elements;

import org.junit.jupiter.api.DisplayName;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class PageContent {

    @DisplayName("Ожидание появления заголовка раздела.")
    public static void waitHeaderElementIsExist() {
        $x("//h1[@data-testid='heading']").shouldBe(exist, ofSeconds(60));
    }
}
