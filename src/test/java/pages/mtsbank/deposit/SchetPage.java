package pages.mtsbank.deposit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SchetPage extends DepositPage {
    String relativeUrl = "chastnim-licam/vkladi/mts-schet";
    String subsectionText = "Накопительный МТС Счет до 7%";
}
