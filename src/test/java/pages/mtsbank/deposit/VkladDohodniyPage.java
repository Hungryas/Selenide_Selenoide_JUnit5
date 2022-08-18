package pages.mtsbank.deposit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class VkladDohodniyPage extends DepositPage {
    String relativeUrl = "chastnim-licam/vkladi/mts-vklad-dohodniy";
    String subsectionText = "МТС Доходный до 7,85%";
}
