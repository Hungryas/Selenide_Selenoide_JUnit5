package pages.mtsbank.deposit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class VkladPage extends DepositPage {
    String relativeUrl = "chastnim-licam/vkladi/mts-vklad";
    String subsectionText = "МТС Вклад до 8,30%";
}
