package pages.mtsbank.cards;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DebetCardPage extends CardPage {
    String relativeUrl = "chastnim-licam/karti/all/debet";
    String subsectionText = "Дебетовые карты";
    String cardName = "ДЕБЕТОВАЯ КАРТА";
}
