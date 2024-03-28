package ui.basicnavigation;

import commonWidgets.ComboBox;
import commonWidgets.InputBox;
import commonWidgets.ListBox;
import dataprovider.QuestionTypeColumns;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
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
@Feature("Data page - Saving the adjusted survey data")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckChangeSurveyAnswersTest extends BaseUITest {
    @Description("Ensure that adjusted radio column answers can be saved ")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getComboBoxForRadioButtonColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testChangeAllSurveyRadioButtonsAnswers(Column column) {
        DataTable table = getCorrectionTable("71b10c88ed");
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);
        String initialComboBoxText = getComboBoxValue(comboBox);
        comboBox.open().getUnselectedRadioButtonOptions().first().click();
        String textFromNewOption = getComboBoxValue(comboBox);
        correctionsPage.getSaveButton().click();
        reloadProjectPage();
        table.waitUntilProjectLoadedMessageDisappears();
        assertThat(textFromNewOption).isNotEqualTo(initialComboBoxText);
    }

    @Description("Ensure that adjusted checkbox column answers can be saved ")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyCheckboxColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testChangeAllSurveyCheckboxAnswers(Column column) {
        DataTable table = getCorrectionTable("36d46ec969");
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);

        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        correctionsPage.clickOnSaveButton();
        reloadPageAndWait(table);
        String initialComboBoxText = getComboBoxValue(comboBox);
        restoreCleanedCheckboxAnswer(comboBox);
        clickOnAnyPlace();
        correctionsPage.clickOnSaveButton();
        table.waitUntilRowsLoaded();
        reloadPageAndWait(table);
        String textFromNewOption = getComboBoxValue(comboBox);
        assertThat(textFromNewOption).isNotEqualTo(initialComboBoxText);
    }

    @Description("Ensure that adjusted essay column answers can be saved ")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyEssayColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testChangeAllSurveyEssayAnswers(Column column) {
        DataTable table = getCorrectionTable("f7ca44f99d");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        String initialEssayCellValue = InputBox.getEssayInputField(inputBox);
        FluentWebElement fluentWebElement = inputBox.clearAndChangeEssayField(RandomDataGenerator.getRandomNumberValue());
        clickOnAnyPlace();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        String changedEssayCellValue = fluentWebElement.text();
        assertThat(initialEssayCellValue).isNotEqualTo(changedEssayCellValue);

    }

    @Description("Ensure that adjusted numeric column answers can be saved ")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyNumericColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testChangeAllSurveyNumericAnswers(Column column) {
        DataTable table = getCorrectionTable("baf0dda9d2");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        String inputFieldValue = InputBox.getNumericInputFieldValue(inputBox);
        inputBox.changeNumericInputFieldValue(RandomDataGenerator.getRandomNumberValue());
        clickOnAnyPlace();
        String textFromNewOption = InputBox.getNumericInputFieldValue(inputBox);
        correctionsPage.getSaveButton().doubleClick();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        assertThat(inputFieldValue).isNotEqualTo(textFromNewOption);

    }

    @Description("Ensure that adjusted slider column answers can be saved ")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlySliderColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testChangeAllSurveySliderAnswers(Column column) {
        DataTable table = getCorrectionTable("3fdda9f1c2");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        String initialValue = InputBox.getNumericInputFieldValue(inputBox);
        inputBox.clearInputBox();
        inputBox.changeNumericInputFieldValue(RandomDataGenerator.getRandomNumberValue());
        clickOnAnyPlace();
        String changedValue = InputBox.getNumericInputFieldValue(inputBox);
        correctionsPage.getSaveButton().doubleClick();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        assertThat(initialValue).isNotEqualTo(changedValue);

    }

    @Description("Ensure that adjusted numeric grid answers can be saved ")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyNumericGridColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testChangeAllSurveyGridAnswers(Column column) {
        DataTable table = getCorrectionTable("c37876657c");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        String initialValue = InputBox.getNumericInputFieldValue(inputBox);
        inputBox.clearInputBox();
        inputBox.changeNumericInputFieldValue(RandomDataGenerator.getRandomNumberValue());
        clickOnAnyPlace();
        String changedValue = InputBox.getNumericInputFieldValue(inputBox);
        correctionsPage.getSaveButton().doubleClick();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        assertThat(initialValue).isNotEqualTo(changedValue);

    }


    private FluentWebElement restoreCleanedCheckboxAnswer(ComboBox comboBox) {
        return comboBox.open().getUnselectedCheckBoxButtonOptions().first().click();
    }

    private void waitUntilSurveyFullyLoaded(DataTable table) {
        table.waitUntilRowsLoaded();
        table.waitUntilProjectLoaded();
        table.waitUntilProjectLoadedMessageDisappears();
    }

    private void reloadProjectPage() {
        correctionsPage.getDriver().navigate().refresh();
    }

    private void reloadPageAndWait(DataTable table) {
        reloadProjectPage();
        table.waitUntilRowsLoaded();
        table.waitUntilProjectLoadedMessageDisappears();
    }

    private DataTable getCorrectionTable(String projectId) {
        return correctionsPage
                .go(56, projectId)
                .getDataTable();
    }
}
