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
import org.fluentlenium.core.domain.FluentList;
import org.testng.annotations.Test;
import pages.corrections.widgets.Row;
import pages.corrections.widgets.data.DataTable;
import pages.project.Column;
import ui.BaseUITest;
import utils.RandomDataGenerator;
import utils.RetryAnalyzer;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@Feature("Data page - 'Show to original' option usage")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckShowOriginalDataTest extends BaseUITest {
    @Description("Ensure that 'Show to original' can be applied to radio answers")
    @Test(retryAnalyzer = RetryAnalyzer.class,dataProvider = "getOnlyRadioButtonColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSwitchShowOriginalDataForRadioAnswers(Column column) {
        DataTable table = getDataTable("d7ac20a908");
        waitUntilSurveyFullyLoaded(table);
        FluentList<Row> currentDataAnswers = table.getRows();
        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);
        comboBox.open().getUnselectedRadioButtonOptions().first().click();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.getSettingsSideBar().click();
        settingsSideBar.turnOnToOriginalData();
        settingsSideBar.clickOnApplyButton();
        FluentList<Row> originalDataAnswers = table.getOriginalDataRows();
        assertThat(originalDataAnswers).isNotEqualTo(currentDataAnswers);
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.getSettingsSideBar().click();
        settingsSideBar.turnOffOriginalData();
        settingsSideBar.clickOnApplyButton();
        table.waitUntilProjectLoadedMessageDisappears();
    }

    @Description("Ensure that 'Show to original' can be applied to checkbox answers")
    @Test(retryAnalyzer = RetryAnalyzer.class,dataProvider = "getOnlyCheckboxColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSwitchShowOriginalDataForCheckboxAnswers(Column column) {
        DataTable table = getDataTable("d2922fc5ad");
        waitUntilSurveyFullyLoaded(table);
        FluentList<Row> currentDataAnswers = table.getRows();
        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);

        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.getSettingsSideBar().click();
        settingsSideBar.turnOnToOriginalData();
        settingsSideBar.clickOnApplyButton();
        FluentList<Row> originalDataAnswers = table.getOriginalDataRows();
        assertThat(originalDataAnswers).isNotEqualTo(currentDataAnswers);
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.getSettingsSideBar().click();
        settingsSideBar.turnOffOriginalData();
        settingsSideBar.clickOnApplyButton();
        table.waitUntilProjectLoadedMessageDisappears();

    }

    @Description("Ensure that 'Show to original' can be applied to essay answers")
    @Test(retryAnalyzer = RetryAnalyzer.class,dataProvider = "getEssayComboBoxForInputColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSwitchShowOriginalDataForEssayAnswers(Column column) {
        DataTable table = getDataTable("cbf2d4192a");
        waitUntilSurveyFullyLoaded(table);
        FluentList<Row> currentDataAnswers = table.getRows();
        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        inputBox.clearAndChangeEssayField(RandomDataGenerator.getRandomNumberValue());
        clickOnAnyPlace();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.getSettingsSideBar().click();
        settingsSideBar.turnOnToOriginalData();
        settingsSideBar.clickOnApplyButton();
        FluentList<Row> originalDataAnswers = table.getOriginalDataRows();
        assertThat(originalDataAnswers).isNotEqualTo(currentDataAnswers);
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.getSettingsSideBar().click();
        settingsSideBar.turnOffOriginalData();
        settingsSideBar.clickOnApplyButton();
        table.waitUntilProjectLoadedMessageDisappears();

    }

    @Description("Ensure that 'Show to original' can be applied to numeric answers")
    @Test(retryAnalyzer = RetryAnalyzer.class,dataProvider = "getOnlyNumericColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSwitchShowOriginalDataForNumericAnswers(Column column) {
        DataTable table = getDataTable("baf0dda9d2");
        waitUntilSurveyFullyLoaded(table);
        FluentList<Row> currentDataAnswers = table.getRows();
        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        inputBox.clearInputBox();
        correctionsPage.getSaveButton().doubleClick();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.getSettingsSideBar().click();
        settingsSideBar.turnOnToOriginalData();
        settingsSideBar.clickOnApplyButton();
        FluentList<Row> originalDataAnswers = table.getOriginalDataRows();
        assertThat(originalDataAnswers).isNotEqualTo(currentDataAnswers);
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.getSettingsSideBar().click();
        settingsSideBar.turnOffOriginalData();
        settingsSideBar.clickOnApplyButton();
        table.waitUntilProjectLoadedMessageDisappears();

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

    private void reloadProjectPage() {
        correctionsPage.getDriver().navigate().refresh();
    }

}