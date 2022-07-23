package pages.booking;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.LocalDate;
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
    private final SelenideElement ONETRUST_BANNER_ACCEPT = $("button#onetrust-accept-btn-handler");
    private static String datesRange;

    public HomePage openPage() {
        open(Configuration.baseUrl);
        return this;
    }

    public HomePage enterDestination(String destination) {
        DESTINATION_INPUT.sendKeys(destination);
        return this;
    }

    public HomePage enterDateRange(LocalDate checkIn, LocalDate checkOut) {
        if (ONETRUST_BANNER_ACCEPT.isDisplayed()) ONETRUST_BANNER_ACCEPT.click();
        DATES_INNER.click();
        CALENDAR_DATES_LIST.find(attribute("data-date", checkIn.toString())).click();
        CALENDAR_DATES_LIST.find(attribute("data-date", checkOut.toString())).click();
        // TODO Set displayed
        DATES_INNER.click();
        setDatesRange();
        return this;
    }

    public HomePage enterGuestsCounts(List<Integer> expectedCounts) {
        GUESTS_TOGGLE.click();
        List<Integer> actualCounts = GUESTS_COUNT_LIST.shouldHave(sizeGreaterThan(0)).texts()
                .stream().map(Integer::parseInt).toList();
        for (int i = 0; i < 3; i++) {
            int expected = expectedCounts.get(i);
            int actual = actualCounts.get(i);
            if (actual != expected) {
                if (expected > actual) clickAddButton(i, expected - actual);
                else clickSubtractButton(i, actual - expected);
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