package elements;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;

public class PageContent {

    public static void waitHeaderElementIsExist() {
        // TODO: Get rid of method
        $x("//h1[@data-testid='heading']").shouldBe(exist);
    }
}
