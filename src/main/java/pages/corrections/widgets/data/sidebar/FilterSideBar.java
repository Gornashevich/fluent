package pages.corrections.widgets.data.sidebar;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import static org.fluentlenium.core.filter.FilterConstructor.withClass;

@Getter
public class FilterSideBar extends BasePage {
    @FindBy(xpath = "//app-corrections-data-filter/app-corrections-filter-container")
    FilterSideBar filterSideBarMenu;

    @FindBy(xpath = "//mat-panel-title/div/div[contains(text(),'Date')]")
    private FluentWebElement filterDate;

    @FindBy(xpath = "//input[@formcontrolname='respondentIds']")
    private FluentWebElement respondentIdInputField;

    @FindBy(xpath = "//div[@class='question-block']")
    private FluentWebElement questionFilterBlockMenu;

    @FindBy(xpath = "//mat-panel-title/div/div[contains(text(),'Respondent status')]")
    private FluentWebElement respondentStatusMenu;

    @FindBy(id = "//*[@id=mat-expansion-panel-header-1]")
    private FluentWebElement dateMenu;

    @FindBy(xpath = "//mat-panel-title/div/div[contains(text(),'Panel')]")
    private FluentWebElement panelMenu;

    @FindBy(xpath = "//mat-panel-title/div/div[contains(text(),'Audit fields')]")
    private FluentWebElement auditFieldsMenu;

    @FindBy(xpath = "//span[contains(text(),'Apply')]")
    private FluentWebElement applyButton;

    @FindBy(xpath = "//span[contains(text(),'Reset all')]")
    private FluentWebElement resetAllButton;

    @FindBy(xpath = "//span[contains(text(),'Question')]/following-sibling::span")
    private FluentWebElement questionGreenIndicator;

    @FindBy(xpath = "//mat-panel-title/div/div[contains(text(),'Respondent status')]/following-sibling::div")
    private FluentWebElement respondentStatusGreenIndicator;

    @FindBy(xpath = "//mat-panel-title/div/div[contains(text(),'Date')]/following-sibling::div")
    private FluentWebElement dateStatusGreenIndicator;

    @FindBy(xpath = "//mat-icon[contains(text(),'close')]")
    private FluentWebElement closeFilterSideBarIcon;

    @FindBy(xpath = "//section[@formgroupname='status']//mat-checkbox")
    private FluentList<FluentWebElement> statusesList;

    @Step("Input respondent Id")
    public void inputRespondentId(String respondentId) {
        await().until(respondentIdInputField).clickable();
        respondentIdInputField.write(respondentId);
    }

    public boolean isQuestionIndicatorDisplayed() {
        return questionGreenIndicator.displayed();
    }

    @Step("Click on Date menu")
    public void clickOnDateMenu() {
        await().until(filterDate).clickable();
        filterDate.waitAndClick();
    }

    @Step("Click on Respondent status menu")
    public void clickOnRespondentStatusMenu() {
        await().until(respondentStatusMenu).clickable();
        respondentStatusMenu.waitAndClick();
    }

    public boolean isRespondentStatusIndicatorDisplayed() {
        return respondentStatusGreenIndicator.displayed();
    }

    @Step("Click on Reset button")
    public void clickOnResetAllButton() {
        await().until(resetAllButton).clickable();
        resetAllButton.mouse().click();
    }

    @Step("Click on Apply button")
    public void clickOnApplyButton() {
        applyButton.enabled();
        applyButton.waitAndClick();
    }

    @Step("Apply all respondent statuses")
    public void getCheckedRespondentStatuses() {
        FluentList<FluentWebElement> elements = statusesList.find(By.xpath("."), withClass().notContains("mat-mdc-checkbox-checked"));
        if (elements.size() != 0) {
            for (int i = 0; i < elements.size() - 1; i++) {
                elements.get(i).click();
            }
        }
    }

    public boolean isDateStatusIndicatorDisplayed() {
        return dateStatusGreenIndicator.displayed();
    }
}
