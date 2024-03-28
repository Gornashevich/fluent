package ui.basicnavigation;

import commonWidgets.ComboBox;
import commonWidgets.InputBox;
import commonWidgets.ListBox;
import dataprovider.QuestionTypeColumns;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.fluentlenium.configuration.ConfigurationProperties;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.corrections.widgets.data.DataTable;
import pages.project.Column;
import ui.BaseUITest;
import utils.RandomDataGenerator;
import utils.RetryAnalyzer;

import static commonWidgets.ComboBox.getComboBoxValue;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@Feature("Data page - 'Set to original' option usage")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckSetToOriginalFunctionTest extends BaseUITest {
    @Story("[SS-399] Automate 'Set to original' function checks")
    @Description("Ensure that 'Set to original option' can be applied to checkboxes answers")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyCheckboxColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetToOriginalDataCheckboxes(Column column) {
        DataTable table = getDataTable("ad5c9c39aa");
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);

        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        correctionsPage.getSaveButton().waitAndClick();
        waitUntilSurveyFullyLoaded(table);
        comboBox.getSetToOriginalIcon().first().hoverOver();
        String originalResponse = setToOriginalMenu.getOriginalRespondentResponse();
        String replacedOriginalResponse = originalResponse.replaceAll("Original: ", "").replaceAll("\\[\\d+\\]", "");
        setToOriginalMenu.clickOnSetToOriginalButton();
        correctionsPage.getSaveButton().waitAndClick();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        String setToOriginalComboBoxText = getComboBoxValue(comboBox);
        String cleanedString = setToOriginalComboBoxText.replaceAll("arrow_drop_down", "");
        assertThat(replacedOriginalResponse.trim()).startsWith(cleanedString.trim());

    }

    @Story("[SS-399] Automate 'Set to original' function checks")
    @Description("Ensure that 'Set to original option' can be applied to radio answers")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyRadioButtonColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetToOriginalDataRadioButton(Column column) {
        DataTable table = getDataTable("ecf8cafd5f");
        waitUntilSurveyFullyLoaded(table);
        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);

        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        comboBox.getSetToOriginalIcon().first().hoverOver();
        String originalResponse = setToOriginalMenu.getOriginalRespondentResponse();
        String replacedOriginalResponse = originalResponse.replaceAll("Original: ", "").replaceAll("\\[\\d+\\]", "");
        setToOriginalMenu.clickOnSetToOriginalButton();
        correctionsPage.getSaveButton().waitAndClick();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        String setToOriginalComboBoxText = getComboBoxValue(comboBox);
        String cleanedString = setToOriginalComboBoxText.replaceAll("arrow_drop_down", "");
        assertThat(replacedOriginalResponse.trim()).startsWith(cleanedString.trim());

    }

    @Story("[SS-399] Automate 'Set to original' function checks")
    @Description("Ensure that 'Set to original option' can be applied to essay answers")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyEssayColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetToOriginalDataEssay(Column column) {
        DataTable table = getDataTable("e4eb05f976");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        inputBox.clearEssayField();
        clickOnAnyPlace();
        correctionsPage.getSaveButton().doubleClick();
        waitUntilSurveyFullyLoaded(table);
        inputBox.getSetToOriginalIcon().first().hoverOver();
        String originalResponse = setToOriginalMenu.getOriginalRespondentResponse();
        setToOriginalMenu.clickOnSetToOriginalButton();
        correctionsPage.getSaveButton().doubleClick();
        waitUntilSurveyFullyLoaded(table);
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        String setToOriginalComboBoxText = InputBox.getEssayInputField(inputBox);

        assertThat(originalResponse).contains(setToOriginalComboBoxText);
    }

    @Story("[SS-399] Automate 'Set to original' function checks")
    @Description("Ensure that 'Set to original option' can be applied to numeric answers")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyNumericColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetToOriginalDataNumericAnswers(Column column) {
        DataTable table = getDataTable("33fdf60a5a");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        inputBox.clearInputBox();
        clickOnAnyPlace();
        correctionsPage.getSaveButton().doubleClick();
        waitUntilSurveyFullyLoaded(table);
        inputBox.getSetToOriginalIcon().first().hoverOver();
        String originalResponse = setToOriginalMenu.getOriginalRespondentResponse();
        setToOriginalMenu.clickOnSetToOriginalButton();
        correctionsPage.getSaveButton().doubleClick();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        String setToOriginalComboBoxText = InputBox.getNumericInputFieldValue(inputBox);
        assertThat(originalResponse).contains(setToOriginalComboBoxText);
    }

    @Story("[SS-399] Automate 'Set to original' function checks")
    @Description("Ensure that the radio answer cell color return to colourless after 'Set to original' option applying")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyRadioButtonColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetToOriginalDataRadioCellColor(Column column) {
        DataTable table = getDataTable("1d55f6e65a");
        waitUntilSurveyFullyLoaded(table);
        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);

        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        String changedCellColorValue = getCellColorValue(column.getValue());
        comboBox.getSetToOriginalIcon().first().hoverOver();
        setToOriginalMenu.clickOnSetToOriginalButton();
        String nonChangedCellColorValue = getCellColorValue(column.getValue());

        assertThat(changedCellColorValue).isNotEqualTo(nonChangedCellColorValue);

    }

    @Story("[SS-399] Automate 'Set to original' function checks")
    @Description("Ensure that the checkbox answers cell color return to colourless after 'Set to original' option applying")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyCheckboxColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetToOriginalDataCheckboxCellColor(Column column) {
        DataTable table = getDataTable("02fb74223c");
        waitUntilSurveyFullyLoaded(table);
        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);

        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        String changedCellColorValue = getCellColorValue(column.getValue());
        comboBox.getSetToOriginalIcon().first().hoverOver();
        setToOriginalMenu.clickOnSetToOriginalButton();
        String nonChangedCellColorValue = getCellColorValue(column.getValue());

        assertThat(changedCellColorValue).isNotEqualTo(nonChangedCellColorValue);

    }

    @Story("[SS-399] Automate 'Set to original' function checks")
    @Description("Ensure that the essay answers cell color return to colourless after 'Set to original' option applying")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyEssayColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetToOriginalDataEssayColor(Column column) {
        DataTable table = getDataTable("ef5246dce4");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        inputBox.clearAndChangeEssayField(RandomDataGenerator.getRandomNumberValue());
        clickOnAnyPlace();
        String changedCellColorValue = getCellColorValue(column.getValue());
        inputBox.getSetToOriginalIcon().first().hoverOver();
        setToOriginalMenu.clickOnSetToOriginalButton();
        String nonChangedCellColorValue = getCellColorValue(column.getValue());

        assertThat(changedCellColorValue).isNotEqualTo(nonChangedCellColorValue);
    }

    @Story("[SS-399] Automate 'Set to original' function checks")
    @Description("Ensure that the numeric answers cell color return to colourless after 'Set to original' option applying")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyNumericColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetToOriginalDataNumericColor(Column column) {
        DataTable table = getDataTable("8617c01c39");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        inputBox.changeNumericInputFieldValue(RandomDataGenerator.getRandomNumberValue());
        clickOnAnyPlace();
        String changedCellColorValue = getCellColorValue(column.getValue());
        inputBox.getSetToOriginalIcon().first().hoverOver();
        setToOriginalMenu.clickOnSetToOriginalButton();
        String nonChangedCellColorValue = getCellColorValue(column.getValue());

        assertThat(changedCellColorValue).isNotEqualTo(nonChangedCellColorValue);
    }

    public String getCellColorValue(String columName) {
        String formatValue = String.format("//td[contains(@class,'%s')]/app-table-cell-wrapper/div", columName);
        FluentWebElement el = el(By.xpath(formatValue));
        await().until(el).displayed();
        String cssValue = el.cssValue("background-color");
        return cssValue;
    }

    private void reloadProjectPage() {
        correctionsPage.getDriver().navigate().refresh();
    }

    private void waitUntilSurveyFullyLoaded(DataTable table) {
        table.waitUntilRowsLoaded();
        table.waitUntilProjectLoaded();
        table.waitUntilProjectLoadedMessageDisappears();
    }

    private DataTable getDataTable(String projectId) {
        return correctionsPage
                .go(56, projectId)
                .getDataTable();
    }
}
