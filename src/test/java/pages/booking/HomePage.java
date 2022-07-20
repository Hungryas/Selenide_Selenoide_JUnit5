package pages.booking;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    private final SelenideElement DESTINATION_INPUT = $("#ss.sb-destination__input");
    private final SelenideElement DATES_INNER = $("div.xp__dates-inner");
    private final ElementsCollection CALENDAR_DATES_LIST = $$(".bui-calendar__content td");
    private final SelenideElement GUESTS_TOGGLE = $("label#xp__guests__toggle");
    private final ElementsCollection GUESTS_COUNT_LIST = $$("span.bui-stepper__display");
    private final ElementsCollection SUBTRACT_BUTTON_LIST = $$("button.bui-stepper__subtract-button");
    private final ElementsCollection ADD_BUTTON_LIST = $$("button.bui-stepper__add-button");
    private final SelenideElement SUBMIT_BUTTON = $("div.-submit-button");
    private final SelenideElement DATES_RANGE = $("div.bui-calendar__display");
    private static String datesRange;

    public HomePage openPage() {
        open(Configuration.baseUrl);
        return this;
    }

    public HomePage enterDestination(String destination) {
        DESTINATION_INPUT.sendKeys(destination);
        return this;
    }

    public HomePage enterDateRange(String checkIn, String checkOut) {
        DATES_INNER.click();
        CALENDAR_DATES_LIST.find(attribute("data-date", checkIn)).click();
        CALENDAR_DATES_LIST.find(attribute("data-date", checkOut)).click();
        // TODO Set displayed
        DATES_INNER.click();
        setDatesRange();
        return this;
    }

    public HomePage enterGuestsCounts(List<Integer> desiredCounts) {
        GUESTS_TOGGLE.click();
        List<Integer> currentCounts = GUESTS_COUNT_LIST.shouldHave(sizeGreaterThan(0)).texts()
                .stream().map(Integer::parseInt).toList();
        for (int i = 0; i < 3; i++) {
            int desired = desiredCounts.get(i);
            int current = currentCounts.get(i);
            if (current != desired) {
                if (desired > current) clickAddButton(i, desired - current);
                else clickSubtractButton(i, current - desired);
            }
        }
        return this;
    }

    public HomePage checkPrices() {
        SUBMIT_BUTTON.click();
        return this;
    }

    private void clickAddButton(Integer guest, Integer value) {
        for (int i = 0; i < value; i++) {
            ADD_BUTTON_LIST.get(guest).click();
        }
    }

    private void clickSubtractButton(Integer guest, Integer value) {
        for (int i = 0; i < value; i++) {
            SUBTRACT_BUTTON_LIST.get(guest).click();
        }
    }

    public String getDatesRange() {
        return datesRange;
    }

    private void setDatesRange() {
        HomePage.datesRange = DATES_RANGE.getText();
    }
}
