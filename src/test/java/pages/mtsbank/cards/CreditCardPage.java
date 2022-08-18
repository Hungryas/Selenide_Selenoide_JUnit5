package pages.mtsbank.cards;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CreditCardPage extends CardPage {
    String relativeUrl = "chastnim-licam/karti/all/credit";
    String subsectionText = "Кредитные карты";
    String cardName = "КРЕДИТНАЯ КАРТА";
}
