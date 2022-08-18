package pages.mtsbank.cards;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class VirtualCardPage extends CardPage {
    String relativeUrl = "chastnim-licam/karti/all/virtual";
    String subsectionText = "Виртуальные карты";
    String cardName = "ВИРТУАЛЬНАЯ КАРТА";
}
