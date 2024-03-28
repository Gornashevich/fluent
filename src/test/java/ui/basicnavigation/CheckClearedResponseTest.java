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
@Feature("Data page - Saving the cleared survey data")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckClearedResponseTest extends BaseUITest {
    @Description("Ensure that cleared checkbox answers can be saved")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyCheckboxColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSaveClearedCheckBoxAnswers(Column column) {
        DataTable table = getCorrectionTable("09473f398a");
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);
        comboBox.getSetToOriginalIcon().first().hoverOver();
        setToOriginalMenu.clickOnSetToOriginalButton();
        String initialResponseOption = getComboBoxValue(comboBox);

        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        clickOnAnyPlace();
        correctionsPage.clickOnSaveButton();
        String clearedResponseOption = getComboBoxValue(comboBox);
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);

        assertThat(clearedResponseOption).isNotEqualTo(initialResponseOption);

    }

    @Description("Ensure that cleared radio answers can be saved")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyRadioButtonColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSaveClearedRadioButtonsAnswers(Column column) {
        DataTable table = getCorrectionTable("400230ff6f");
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);

        String initialResponseOption = getComboBoxValue(comboBox);
        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        correctionsPage.clickOnSaveButton();
        table.waitUntilRowsLoaded();
        String clearedResponseOption = getComboBoxValue(comboBox);
        comboBox.open();
        open.await().atMost(1000).until().displayed();
        open.getUnselectedRadioButtonOptions().first().waitAndClick();
        correctionsPage.clickOnSaveButton();
        table.waitUntilRowsLoaded();

        assertThat(clearedResponseOption).isNotEqualTo(initialResponseOption);

    }


    @Description("Ensure that cleared numeric answers can be saved")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getNumericComboBoxForInputColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSaveEmptyNumericInputAnswers(Column column) {
        DataTable table = getCorrectionTable("33142e6655");
        table.waitUntilRowsLoaded();
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        inputBox.getSetToOriginalIcon().first().hoverOver();
        setToOriginalMenu.clickOnSetToOriginalButton();
        clickOnAnyPlace();
        String initialResponseOption = InputBox.getNumericInputFieldValue(inputBox);
        FluentWebElement fluentWebElement = inputBox.clearInputBox();
        String clearedResponseOption = fluentWebElement.text();
        clickOnAnyPlace();
        correctionsPage.getSaveButton().doubleClick();
        reloadProjectPage();

        assertThat(clearedResponseOption).isNotEqualTo(initialResponseOption);

    }

    @Description("Ensure that cleared numeric answers can be saved")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getComboBoxForRankedQuestions",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSaveEmptyRankInputAnswers(Column column) {
        DataTable table = getCorrectionTable("43a1fbb056");
        table.waitUntilRowsLoaded();
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        inputBox.getSetToOriginalIcon().first().hoverOver();
        setToOriginalMenu.clickOnSetToOriginalButton();
        clickOnAnyPlace();
        String initialResponseOption = InputBox.getNumericInputFieldValue(inputBox);
        FluentWebElement fluentWebElement = inputBox.clearInputBox();
        String clearedResponseOption = fluentWebElement.text();
        clickOnAnyPlace();
        correctionsPage.getSaveButton().doubleClick();
        reloadProjectPage();

        assertThat(clearedResponseOption).isNotEqualTo(initialResponseOption);

    }

    private FluentWebElement restoreCleanedCheckboxAnswer(ComboBox comboBox) {
        return comboBox.open().getUnselectedCheckBoxButtonOptions().first().click();
    }

    private FluentWebElement restoreInputBoxAnswer(InputBox inputBox) {
        FluentWebElement element = inputBox.changeNumericInputFieldValue(RandomDataGenerator.getMinimumRandomNumberValue());
        await().until(element).present();
        return element;
    }

    private void restoreRadioButtonValue(DataTable table, ComboBox comboBox) {
        ListBox open = comboBox.open();
        open.await().atMost(1500).until().displayed();
        open.getUnselectedRadioButtonOptions().first().waitAndClick();
        correctionsPage.clickOnSaveButton();
        waitUntilSurveyFullyLoaded(table);
    }

    private void waitUntilSurveyFullyLoaded(DataTable table) {
        table.waitUntilRowsLoaded();
        table.waitUntilProjectLoaded();
        table.waitUntilProjectLoadedMessageDisappears();
    }

    private DataTable getCorrectionTable(String projectId) {
        return correctionsPage
                .go(56, projectId)
                .getDataTable();
    }

    private void reloadProjectPage() {
        correctionsPage.getDriver().navigate().refresh();
    }
}
