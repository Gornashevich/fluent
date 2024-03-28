package pages.corrections;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.corrections.widgets.data.*;
import pages.corrections.widgets.data.sidebar.FilterByQuestionBlockMenu;
import pages.corrections.widgets.data.sidebar.FilterDate;
import pages.corrections.widgets.data.sidebar.FilterSideBar;
import pages.corrections.widgets.metadata.MetadataTable;
import pages.corrections.widgets.metadata.QuestionTextMenu;

@Getter
@PageUrl("/{projectId}/{kekId}/corrections/data")
public class CorrectionsPage extends BasePage {
    @FindBy(xpath = "//table")
    private DataTable dataTable;

    @FindBy(xpath = "//mat-table")
    private MetadataTable metadataTable;

    @FindBy(xpath = "//p[contains(text(),'Questions')]")
    private FluentWebElement questionSideBar;

    @FindBy(xpath = "//p[contains(text(),'Layouts')]/..")
    private FluentWebElement layoutsSideBar;

    @FindBy(xpath = "//div/mat-icon[contains(text(),'filter_list')]")
    private FluentWebElement filtersSidebar;

    @FindBy(xpath = "//p[contains(text(),'Settings')]")
    private FluentWebElement settingsSideBar;

    @FindBy(xpath = "//app-corrections-data-filter/app-corrections-filter-container")
    private FilterSideBar filterSideBarMenu;

    @FindBy(xpath = "//mat-panel-title/div/div[contains(text(),'Date')]")
    private FilterDate filterDate;

    @FindBy(xpath = "//app-corrections-filter-by-question")
    private FilterByQuestionBlockMenu filterByQuestionBlockMenu;

    @FindBy(xpath = "//app-corrections-cleaned-info[contains(@class,'cleaned-indicator-tooltip')]")
    private FluentList<FluentWebElement> setToOriginalIcon;

    @FindBy(xpath = "//span[contains(text(),'Save')]")
    private FluentWebElement saveButton;

    @FindBy(xpath = "//button[contains(@class,'cancel-button')]")
    private FluentWebElement cancelButton;

    @FindBy(xpath = "//app-corrections-cancel-changes")
    public CancelChanges cancelChanges;

    @FindBy(xpath = "//div[@class='cdk-overlay-pane corrections-import-dialog']")
    private ImportMenu importMenu;

    @FindBy(xpath = "//div[@class='cdk-overlay-pane']/div[@role='menu']")
    public QuestionTextMenu questionTextMenu;

    @FindBy(xpath = "//span[text()=' Data ']")
    public FluentWebElement dataTab;

    @FindBy(xpath = "//span[text()=' Metadata ']")
    public FluentWebElement metadataTab;

    @FindBy(xpath = "//div[contains(text(),'Corrections')]")
    public FluentWebElement correctionsMenu;

    @Override
    public CorrectionsPage go() {
        return super.go();
    }

    @Override
    public CorrectionsPage go(Object... params) {
        return super.go(params);
    }
    @Step("Click on Save button")
    public void clickOnSaveButton() {
        saveButton.await().until().clickable();
        saveButton.doubleClick();
    }
    @Step("Click on Cancel button")
    public void clickOnCancelButton() {
        cancelButton.await().until().clickable();
        cancelButton.waitAndClick();
    }
    @Step("Click on Settings button")
    public void clickOnSettingsButton() {
        settingsSideBar.await().until().clickable();
        settingsSideBar.waitAndClick();
    }
    @Step("Switch to MetaData tab")
    public void switchToMetadataPage(){
        correctionsMenu.click();
        metadataTab.waitAndClick();
    }
    @Step("Switch to Data tab")
    public void switchToDataPage(){
        await().until(correctionsMenu).clickable();
        await().until(correctionsMenu).enabled();
        correctionsMenu.click();
        dataTab.waitAndClick();
    }

}
