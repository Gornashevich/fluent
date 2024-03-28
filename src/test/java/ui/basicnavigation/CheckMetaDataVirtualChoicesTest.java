package ui.basicnavigation;

import commonWidgets.ComboBox;
import commonWidgets.InputBox;
import commonWidgets.ListBox;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.assertj.core.api.Assertions;
import org.fluentlenium.configuration.ConfigurationProperties;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.domain.FluentWebElement;
import org.testng.annotations.Test;
import pages.corrections.widgets.data.DataTable;
import pages.project.Column;
import ui.BaseUITest;
import utils.RandomDataGenerator;
import utils.RetryAnalyzer;

import static commonWidgets.ComboBox.getComboBoxValue;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
@Feature("MetaData page - Adjusting the questions and respondents responses")
public class CheckMetaDataVirtualChoicesTest extends BaseUITest {
    @Description("Ensure that it's possible to set response for Checkbox virtual question on Data tab")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testSetResponseForVirtualCheckboxQuestions() {
        DataTable table = getDataTable("0775d40c6b");
        waitUntilSurveyFullyLoaded(table);
        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(Column.MULTI_SELECT_CHECKBOX_ROW);

        String initialComboBoxText = getComboBoxValue(comboBox);
        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        waitAfterDataAdjusting(table);
        String textFromNewOption = getComboBoxValue(comboBox);
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        assertThat(textFromNewOption).isNotEqualTo(initialComboBoxText);
        restoreCleanedCheckboxAnswer(comboBox);
        waitAfterDataAdjusting(table);
    }

    @Description("Ensure that it's possible to set response for Essay virtual question on Data tab ")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testSetResponseForVirtualEssayQuestions() {
        DataTable table = getDataTable("9ce71872eb");
        waitUntilSurveyFullyLoaded(table);
        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(Column.ESSAY_TEXTAREA_ROW);

        String inputFieldValue = InputBox.getEssayInputField(inputBox);
        FluentWebElement fluentWebElement = inputBox.clearAndChangeEssayField(RandomDataGenerator.getRandomNumberValue());
        waitAfterDataAdjusting(table);
        String textFromNewOption = fluentWebElement.text();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);

        assertThat(inputFieldValue).isNotEqualTo(textFromNewOption);
    }

    @Description("Ensure that it's possible to set response for RadioButton virtual question on Data tab")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testSetResponseForVirtualRadioQuestions() {
        DataTable table = getDataTable("3de75f7143");
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(Column.SINGLE_SELECT_DROPDOWN_GRID);

        String initialComboBoxText = getComboBoxValue(comboBox);
        comboBox.open().getUnselectedRadioButtonOptions().first().click();
        String textFromNewOption = getComboBoxValue(comboBox);
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);

        Assertions.assertThat(textFromNewOption).isNotEqualTo(initialComboBoxText);
    }

    @Description("Ensure that it's possible to set response for Numeric virtual question on Data tab")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testSetResponseForVirtualNumericQuestions() {
        DataTable table = getDataTable("df614f3388");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(Column.NUMERIC_NUMBER_TEXTBOX_ROW);

        String inputFieldValue = InputBox.getNumericInputFieldValue(inputBox);
        FluentWebElement fluentWebElement = inputBox.changeNumericInputFieldValue(RandomDataGenerator.getRandomNumberValue());
        String textFromNewOption = fluentWebElement.text();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);

        assertThat(inputFieldValue).isNotEqualTo(textFromNewOption);
    }

    private FluentWebElement restoreCleanedCheckboxAnswer(ComboBox comboBox) {
        return comboBox.open().getUnselectedCheckBoxButtonOptions().first().click();

    }

    private DataTable getDataTable(String projectId) {
        return correctionsPage
                .go(56, projectId)
                .getDataTable();
    }

    private void reloadProjectPage() {
        correctionsPage.getDriver().navigate().refresh();
    }

    private void waitUntilSurveyFullyLoaded(DataTable table) {
        table.waitUntilRowsLoaded();
        table.waitUntilProjectLoaded();
        table.waitUntilProjectLoadedMessageDisappears();
    }

    private void waitAfterDataAdjusting(DataTable dataTable) {
        clickOnAnyPlace();
        correctionsPage.getSaveButton().click();
        dataTable.waitUntilProjectLoaded();
        dataTable.waitUntilRowsLoaded();
    }
}
