package ui.basicnavigation;

import commonWidgets.ComboBox;
import commonWidgets.ListBox;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.fluentlenium.configuration.ConfigurationProperties;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
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
@Feature("MetaData page - Interaction with created virtual questions")
public class CheckVirtualChoicesOnDataPageTest extends BaseUITest {
    @Description("Ensure that it's possible to choose virtual answer for Checkbox Row question on Data page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"groupNeedingCleanup"})
    public void testChooseVirtualChoiceForCheckboxRowQuestion() {
        DataTable table = getDataTable("1bf68b8e82");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        var expectedVirtualChoice = RandomDataGenerator.getRandomText();
        var questionName = "MULTI_SELECT_CHECKBOX_ROW";
        metadataTable.clickOnRowMenu(questionName);
        rowsMenu.clickOnAddRowButton();
        rowsMenu.fullFillTextValueResponses(expectedVirtualChoice);
        rowsMenu.clickOnUpdateButton();
        correctionsPage.getSaveButton().waitAndClick();
        reloadProjectPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        correctionsPage.switchToDataPage();
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(Column.MULTI_SELECT_CHECKBOX_ROW);
        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        comboBox.open().getUnselectedCheckBoxButtonOptions().last().click();
        clickOnAnyPlace();
        correctionsPage.getSaveButton().click();
        waitUntilSurveyFullyLoaded(table);
        String actualVirtualChoice = getComboBoxValue(comboBox).replaceAll("arrow_drop_down", "").trim();
        reloadProjectPage();
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        rowsMenu.getRowsAnswerOptions().first().waitAndClick();
        assertThat(actualVirtualChoice).isEqualTo(expectedVirtualChoice);

    }

    @Description("Ensure that it's possible to choose virtual answer for Checkbox Column question on Data page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"groupNeedingCleanup"})
    public void testChooseVirtualChoiceForCheckboxColumnQuestion() {
        DataTable table = getDataTable("41b699cc67");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        var expectedVirtualChoice = RandomDataGenerator.getRandomText();
        var questionName = "MULTI_SELECT_CHECKBOX_COLUMN";
        metadataTable.clickOnColumnMenu(questionName);
        rowsMenu.clickOnAddRowButton();
        rowsMenu.fullFillTextValueResponses(expectedVirtualChoice);
        rowsMenu.clickOnUpdateButton();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        correctionsPage.switchToDataPage();
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(Column.MULTI_SELECT_CHECKBOX_COLUMN);
        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        comboBox.open().getUnselectedCheckBoxButtonOptions().last().click();
        clickOnAnyPlace();
        correctionsPage.getSaveButton().click();
        waitUntilSurveyFullyLoaded(table);
        String actualVirtualChoice = getComboBoxValue(comboBox).replaceAll("arrow_drop_down", "").trim();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        metadataTable.clickOnColumnMenu(questionName);
        assertThat(actualVirtualChoice).isEqualTo(expectedVirtualChoice);

    }

    @Description("Ensure that it's possible to choose virtual answer for Radio Column question on Data page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"groupNeedingCleanup"})
    public void testVirtualChoiceRadioColumnQuestion() {
        DataTable table = getDataTable("7d1d21b108");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        var newRandomValue = RandomDataGenerator.getRandomText();
        String questionName = "SINGLE_SELECT_RADIO_COLUMN";
        columnsMenu.clickOnColumnMenu(questionName);
        columnsMenu.clickOnAddRowButton();
        columnsMenu.fullFillTextValueResponses(newRandomValue);
        questionTextMenu.clickOnUpdateButton();
        correctionsPage.getSaveButton().doubleClick();
        reloadProjectPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        correctionsPage.switchToDataPage();
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(Column.SINGLE_SELECT_RADIO_COLUMN);

        comboBox.open().getUnselectedRadioButtonOptions().last().click();
        String textFromNewOption = getComboBoxValue(comboBox).replaceAll("arrow_drop_down", "").trim();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        metadataTable.clickOnColumnMenu(questionName);
        assertThat(textFromNewOption).isEqualTo(newRandomValue);

    }

    @Description("Ensure that it's possible to choose virtual answer for Radio Row question on Data page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"groupNeedingCleanup"})
    public void testVirtualChoiceRadioRowQuestion() {
        DataTable table = getDataTable("e0151865c2");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        var newRandomValue = RandomDataGenerator.getRandomText();
        String questionName = "SINGLE_SELECT_BUTTON_ROW";
        rowsMenu.clickOnRowMenu(questionName);
        rowsMenu.clickOnAddRowButton();
        rowsMenu.fullFillTextValueResponses(newRandomValue);
        questionTextMenu.clickOnUpdateButton();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        correctionsPage.switchToDataPage();
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(Column.SINGLE_SELECT_BUTTON_ROW);

        comboBox.open().getUnselectedRadioButtonOptions().last().click();
        String textFromNewOption = getComboBoxValue(comboBox).replaceAll("arrow_drop_down", "").trim();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        rowsMenu.getRowsAnswerOptions().first().waitAndClick();
        assertThat(textFromNewOption).isEqualTo(newRandomValue);
    }

    @Description("Ensure that it's possible to choose virtual answer for Multi Select Dropdown Row question on Data page")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testVirtualChoiceMultiSelectDropDownQuestion() {
        DataTable table = getDataTable("d5138be9a1");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        var expectedVirtualChoice = RandomDataGenerator.getRandomText();
        String questionName = "MULTI_SELECT_DROPDOWN_ROW";
        cellsMenu.clickOnCellMenu(questionName);
        cellsMenu.clickOnAddRowButton();
        cellsMenu.fullFillTextValueResponses(expectedVirtualChoice);
        questionTextMenu.clickOnUpdateButton();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        correctionsPage.switchToDataPage();
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(Column.MULTI_SELECT_DROPDOWN_ROW);

        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        comboBox.open().getUnselectedCheckBoxButtonOptions().last().click();
        clickOnAnyPlace();
        correctionsPage.getSaveButton().click();
        waitUntilSurveyFullyLoaded(table);
        String actualVirtualChoice = getComboBoxValue(comboBox).replaceAll("arrow_drop_down", "").trim();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        cellsMenu.clickOnCellMenu(questionName);
        assertThat(actualVirtualChoice).isEqualTo(expectedVirtualChoice);
    }

    @Description("Ensure that it's possible to choose virtual answer for Radio button grid question on Data page")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testChooseVirtualChoiceForRadioButtonGridQuestion() {
        DataTable table = getDataTable("d403fdc146");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        var newRandomValue = RandomDataGenerator.getRandomText();
        String questionName = "SINGLE_SELECT_DROPDOWN_COLUMN";
        cellsMenu.clickOnCellMenu(questionName);
        cellsMenu.clickOnAddRowButton();
        cellsMenu.fullFillTextValueResponses(newRandomValue);
        questionTextMenu.clickOnUpdateButton();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        metadataTable.waitUntilMetaDataRowsLoaded();

        correctionsPage.switchToDataPage();
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(Column.SINGLE_SELECT_DROPDOWN_COLUMN);

        comboBox.open().getUnselectedRadioButtonOptions().last().click();
        String textFromNewOption = getComboBoxValue(comboBox).replaceAll("arrow_drop_down", "").trim();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        cellsMenu.clickOnCellMenu(questionName);
        table.waitUntilProjectLoadedMessageDisappears();
        assertThat(textFromNewOption).isEqualTo(newRandomValue);
    }

    @AfterMethod(groups = {"groupNeedingCleanup"})
    public void cleanup() {
        FluentList<FluentWebElement> deleteIcons = find(By.xpath("//button[@class='delete button-icon']"));
        if (deleteIcons.size() != 0) {
            for (FluentWebElement deleteIcon : deleteIcons) {
                while (deleteIcon.displayed()) {
                    deleteIcon.waitAndClick();
                    FluentWebElement el = el(By.xpath("//span[contains(text(),'Update')]"));
                    await().until(el).present();
                    el.waitAndClick();
                    correctionsPage.clickOnSaveButton();
                }
            }
        }
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
}
