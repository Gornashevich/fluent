package pages.corrections.widgets.data.sidebar;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

@Getter
public class FilterDate extends BasePage {
    @FindBy(xpath = "//mat-panel-title/div/div[contains(text(),'Date')]")
    private FilterDate filterDate;
    @FindBy(id = "//*[@id=dateFrom]")
    private FluentWebElement dateFromField;
    @FindBy(xpath = "//input[@formcontrolname='end']")
    private FluentWebElement dateToField;
    @FindBy(xpath = "//button[contains(text(),'Today')]")
    private FluentWebElement todayButton;
    @FindBy(xpath = "//button[contains(text(),'Yesterday')]")
    private FluentWebElement yesterdayButton;
    @FindBy(xpath = "//button[contains(text(),'Last 7 days')]")
    private FluentWebElement lastSevenDaysButton;
    @FindBy(xpath = "//button[contains(text(),'Last month')]")
    private FluentWebElement lastMonthButton;
    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    private FluentWebElement chooseYearButton;
    @FindBy(xpath = "//div[contains(@class,'calendar-reset-button')]/button")
    private FluentWebElement resetButton;
    @FindBy(xpath = "//mat-panel-title/div/div[contains(text(),'Date')]/following-sibling::div")
    private FluentWebElement greenIndicator;
    @FindBy(xpath = "//span[contains(@class,'mat-calendar') and contains(text(),'1')]")
    private FluentWebElement firstDayOfTheMonth;
    @FindBy(xpath = "//span[contains(text(),'30')]")
    private FluentWebElement lastDayOfTheMonth;

    @Step("Choose calendar date")
    public void chooseAppropriateCalendarDate() {
        await().until(firstDayOfTheMonth).clickable();
        firstDayOfTheMonth.click();
        await().until(lastDayOfTheMonth).clickable();
        lastDayOfTheMonth.click();
    }
}
