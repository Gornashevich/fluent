package ui.basicnavigation;

import commonWidgets.ComboBox;
import commonWidgets.InputBox;
import dataprovider.QuestionTypeColumns;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.fluentlenium.configuration.ConfigurationProperties;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.corrections.widgets.data.DataTable;
import pages.project.Column;
import ui.BaseUITest;
import utils.RetryAnalyzer;

import java.util.List;

import static commonWidgets.ComboBox.getComboBoxValue;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@Feature("Data page - Applying user custom filter sidebar")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckFiltersSideBarTest extends BaseUITest {
    @Description("Check the capability to apply filter sidebar for checkbox type of questions")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testApplyFilterSideBarForCheckboxAnswers() {
        DataTable table = getDataTable("4ae038780b");
        waitUntilSurveyFullyLoaded(table);
        table.resetFiltersFromDataTab();
        var status = "Complete";
        String firstRespondentId = table.getFirstRespondentId();
        correctionsPage.getFiltersSidebar().click();
        filterSideBar.inputRespondentId(firstRespondentId);
        filterSideBar.getQuestionFilterBlockMenu().click();
        filterByQuestionBlockMenu.getSurveysQuestionOption().click();
        List<FluentWebElement> surveysQuestionList = filterByQuestionBlockMenu.chooseSurveyQuestionWithoutMosTabs();
        filterByQuestionBlockMenu.fullFillQuestionMenuWithAnswerOptions(surveysQuestionList);
        String chosenFilteredAnswerOption = filterByQuestionBlockMenu.getAnswerFilterOption().text();
        String filteredAnswerOption = getStrings(chosenFilteredAnswerOption);
        filterByQuestionBlockMenu.clickOnApplyButton();
        filterSideBar.getRespondentStatusMenu().click();
        respondentStatus.clickOnAppropriateStatus(status);
        filterSideBar.getRespondentStatusMenu().click();
        filterSideBar.getApplyButton().waitAndClick();

        List<FluentWebElement> questionNameList = getRespondentAnswersWebElementList();
        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(Column.MULTI_SELECT_CHECKBOX_ROW);
        String answerOption = getComboBoxValue(comboBox);
        boolean isNecessaryRespondentIdDisplays = correctionsPage.getDataTable().isProperRespondentInfoDisplays(firstRespondentId, questionNameList);
        boolean isNecessaryStatusDisplays = correctionsPage.getDataTable().isProperRespondentInfoDisplays(status, questionNameList);

        assertThat(isNecessaryStatusDisplays).isTrue();
        assertThat(isNecessaryRespondentIdDisplays).isTrue();
        assertThat(answerOption.contains(filteredAnswerOption)).isTrue();
    }

    @Description("Check the capability to apply filter sidebar for radio button type of questions")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyNPSRadioButton",
            dataProviderClass = QuestionTypeColumns.class)
    public void testApplyFilterSideBarForRadioButtonAnswers(Column column) {
        DataTable table = getDataTable("2d1539b9e6");
        waitUntilSurveyFullyLoaded(table);
        table.resetFiltersFromDataTab();
        var status = "Complete";
        String firstRespondentId = table.getFirstRespondentId();
        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);

        String initialAnswerOption = getComboBoxValue(comboBox);
        correctionsPage.getFiltersSidebar().click();
        filterSideBar.clickOnRespondentStatusMenu();
        respondentStatus.clickOnAppropriateStatus(status);
        filterSideBar.getRespondentStatusMenu().click();
        filterSideBar.getQuestionFilterBlockMenu().click();
        filterByQuestionBlockMenu.getSurveysQuestionOption().click();
        List<FluentWebElement> surveysQuestionList = filterByQuestionBlockMenu.chooseSurveyQuestionWithoutMosTabs();
        filterByQuestionBlockMenu.fullFillQuestionMenuWithValueInput(surveysQuestionList, initialAnswerOption);
        filterByQuestionBlockMenu.clickOnApplyButton();
        filterSideBar.inputRespondentId(firstRespondentId);
        filterSideBar.getApplyButton().click();
        List<FluentWebElement> questionNameList = getRespondentAnswersWebElementList();
        String filteredAnswerOption = getComboBoxValue(comboBox);
        boolean isNecessaryRespondentIdDisplays = correctionsPage.getDataTable().isProperRespondentInfoDisplays(firstRespondentId, questionNameList);
        boolean isNecessaryStatusDisplays = correctionsPage.getDataTable().isProperRespondentInfoDisplays(status, questionNameList);

        assertThat(isNecessaryStatusDisplays).isTrue();
        assertThat(isNecessaryRespondentIdDisplays).isTrue();
        assertThat(filteredAnswerOption).isEqualTo(initialAnswerOption);
    }

    @Description("Check the capability to apply filter sidebar for numeric  type of questions")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyNumericNumberTextBoxSingle",
            dataProviderClass = QuestionTypeColumns.class)
    public void testApplyFilterSideBarForNumericAnswers(Column column) {
        DataTable table = getDataTable("1a8c8919e1");
        waitUntilSurveyFullyLoaded(table);
        table.resetFiltersFromDataTab();
        var status = "Complete";

        String firstRespondentId = table.getFirstRespondentId();
        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);
        String initialFieldValue = InputBox.getNumericInputFieldValue(inputBox);
        correctionsPage.getFiltersSidebar().click();
        filterSideBar.clickOnRespondentStatusMenu();
        respondentStatus.clickOnAppropriateStatus(status);
        filterSideBar.getRespondentStatusMenu().click();
        filterSideBar.getQuestionFilterBlockMenu().click();
        filterByQuestionBlockMenu.getSurveysQuestionOption().click();
        List<FluentWebElement> surveysQuestionList = filterByQuestionBlockMenu.chooseSurveyQuestionWithoutMosTabs();
        filterByQuestionBlockMenu.fullFillQuestionMenuWithValueInput(surveysQuestionList, initialFieldValue);
        filterByQuestionBlockMenu.clickOnApplyButton();
        filterSideBar.inputRespondentId(firstRespondentId);
        filterSideBar.getApplyButton().click();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> questionNameList = getRespondentAnswersWebElementList();
        String filteredFieldValue = InputBox.getNumericInputFieldValue(inputBox);
        boolean isNecessaryRespondentIdDisplays = correctionsPage.getDataTable().isProperRespondentInfoDisplays(firstRespondentId, questionNameList);
        boolean isNecessaryStatusDisplays = correctionsPage.getDataTable().isProperRespondentInfoDisplays(status, questionNameList);

        assertThat(isNecessaryStatusDisplays).isTrue();
        assertThat(isNecessaryRespondentIdDisplays).isTrue();
        assertThat(filteredFieldValue).isEqualTo(initialFieldValue);
    }

    @Description("Check the capability to apply filter sidebar for essay  type of questions")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyEssayTextAreaSingle",
            dataProviderClass = QuestionTypeColumns.class)
    public void testApplyFilterSideBarForEssayAnswers(Column column) {
        DataTable table = getDataTable("62f038f983");
        waitUntilSurveyFullyLoaded(table);
        var status = "Complete";
        String firstRespondentId = table.getFirstRespondentId();

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        String initialFieldValue = InputBox.getEssayInputField(inputBox);
        correctionsPage.getFiltersSidebar().click();
        filterSideBar.clickOnRespondentStatusMenu();
        respondentStatus.clickOnAppropriateStatus(status);
        filterSideBar.getRespondentStatusMenu().click();
        filterSideBar.getQuestionFilterBlockMenu().click();
        filterByQuestionBlockMenu.getSurveysQuestionOption().click();
        List<FluentWebElement> surveysQuestionList = filterByQuestionBlockMenu.chooseSurveyQuestionWithoutMosTabs();
        filterByQuestionBlockMenu.fullFillQuestionMenuWithValueInput(surveysQuestionList, initialFieldValue);
        filterByQuestionBlockMenu.clickOnApplyButton();
        filterSideBar.inputRespondentId(firstRespondentId);
        filterSideBar.getApplyButton().click();
        table.waitUntilRowsLoaded();
        List<FluentWebElement> questionNameList = getRespondentAnswersWebElementList();
        String filteredFieldValue = getEssayInputField(inputBox);
        boolean isNecessaryRespondentIdDisplays = correctionsPage.getDataTable().isProperRespondentInfoDisplays(firstRespondentId, questionNameList);
        boolean isNecessaryStatusDisplays = correctionsPage.getDataTable().isProperRespondentInfoDisplays(status, questionNameList);

        assertThat(isNecessaryStatusDisplays).isTrue();
        assertThat(isNecessaryRespondentIdDisplays).isTrue();
        assertThat(filteredFieldValue).isEqualTo(initialFieldValue);

    }

    @Description("Check the capability to reset filter settings via Filter 'Reset all button'")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testResetAllAppliedFiltersViaFilterSideBar() {
        DataTable table = getDataTable("8e778a4fd4");
        waitUntilSurveyFullyLoaded(table);
        table.resetFiltersFromDataTab();
        var status = "Terminated";
        correctionsPage.getFiltersSidebar().click();
        filterSideBar.clickOnRespondentStatusMenu();
        respondentStatus.clickOnAppropriateStatus(status);
        filterSideBar.getApplyButton().click();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> appliedFilterRespondentList = getRespondentAnswersWebElementList();
        correctionsPage.getFiltersSidebar().click();
        filterSideBar.clickOnResetAllButton();
        waitUntilSurveyFullyLoaded(table);
        filterSideBar.getCloseFilterSideBarIcon().click();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> resetFilterRespondentList = getRespondentAnswersWebElementList();

        assertThat(appliedFilterRespondentList).isNotEqualTo(resetFilterRespondentList);
    }

    @Description("Check the capability to reset filter settings via reset on Data tab")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testResetAllAppliedFiltersDataTab() {
        DataTable table = getDataTable("1f33c229c2");
        waitUntilSurveyFullyLoaded(table);
        var status = "Terminated";
        correctionsPage.getFiltersSidebar().click();
        filterSideBar.clickOnRespondentStatusMenu();
        respondentStatus.clickOnAppropriateStatus(status);
        filterSideBar.getApplyButton().click();
        List<FluentWebElement> appliedFilterRespondentList = getRespondentAnswersWebElementList();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        table.resetFiltersFromDataTab();
        List<FluentWebElement> resetFilterRespondentList = getRespondentAnswersWebElementList();

        assertThat(appliedFilterRespondentList).isNotEqualTo(resetFilterRespondentList);
    }

    private String getStrings(String chosenFilteredAnswerOption) {
        String answerOption = chosenFilteredAnswerOption.replaceAll("arrow_drop_down", "");
        String[] s = answerOption.split("\\s+");
        return s[2];
    }

    private FluentList<FluentWebElement> getRespondentAnswersWebElementList() {
        return find(By.xpath("//tbody[@role='rowgroup']"));
    }

    private DataTable getDataTable(String projectId) {
        return correctionsPage
                .go(56, projectId)
                .getDataTable();
    }

    private void waitUntilSurveyFullyLoaded(DataTable table) {
        table.waitUntilRowsLoaded();
        table.waitUntilProjectLoaded();
        table.waitUntilProjectLoadedMessageDisappears();
    }

    public String getEssayInputField(InputBox inputBox) {
        inputBox.await().until().displayed();
        return inputBox.find(By.xpath(".//div/app-table-text/div")).first().text();
    }

    private void reloadProjectPage() {
        correctionsPage.getDriver().navigate().refresh();
    }
}
