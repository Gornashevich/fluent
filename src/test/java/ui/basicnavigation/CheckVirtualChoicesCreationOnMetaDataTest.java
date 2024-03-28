package ui.basicnavigation;

import commonWidgets.ComboBox;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
@Feature("MetaData page - Creation of virtual choices")
public class CheckVirtualChoicesCreationOnMetaDataTest extends BaseUITest {
    @Description("Ensure that virtual row choice can be added in Metadata page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"groupNeedingCleanup"})
    public void testAddVirtualRowChoiceForMetadata() {
        DataTable table = getDataTable("fd33e7febc");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        var virtualRow = RandomDataGenerator.getRandomText();
        rowsMenu.getRowsAnswerOptions().first().click();
        rowsMenu.clickOnAddRowButton();
        rowsMenu.fullFillVirtualAnswerOption(virtualRow);
        rowsMenu.clickOnUpdateButton();
        correctionsPage.getSaveButton().click();
        reloadProjectPage();
        table.waitUntilProjectLoadedMessageDisappears();
        metadataTable.waitUntilMetaDataRowsLoaded();
        rowsMenu.getRowsAnswerOptions().first().click();
        rowsMenu.chooseLastAnswerOption();
        String addedVirtualRow = rowsMenu.getTextOptionInputList().last().attribute("value");
        assertThat(addedVirtualRow).isEqualTo(virtualRow);

    }

    @Description("Ensure that created virtual answer is displayed on Data page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"groupNeedingCleanup"})
    public void testAddedVirtualChoiceOnDataTab() {
        DataTable table = getDataTable("2d7130e299");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        var virtualRowOption = RandomDataGenerator.getRandomText();
        rowsMenu.getRowsAnswerOptions().first().click();
        rowsMenu.clickOnAddRowButton();
        rowsMenu.fullFillVirtualAnswerOption(virtualRowOption);
        rowsMenu.clickOnUpdateButton();
        correctionsPage.getSaveButton().click();
        reloadProjectPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        correctionsPage.switchToDataPage();
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(Column.MULTI_SELECT_CHECKBOX_ROW);

        comboBox.open();
        List<FluentWebElement> checkboxComboOptionsList = getCheckboxComboOptions();
        boolean isVirtualChoiceDisplayedInDataTab = comboBox.isConfiguredVirtualDataDisplays(virtualRowOption, checkboxComboOptionsList);
        clickOnAnyPlace();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        rowsMenu.getRowsAnswerOptions().first().waitAndClick();
        assertThat(isVirtualChoiceDisplayedInDataTab).isTrue();
    }
    @AfterMethod(groups = {"groupNeedingCleanup"})
    public void cleanup() {
        clearResponses();
        rowsMenu.clickOnUpdateButton();
        correctionsPage.getSaveButton().waitAndClick();
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

    private List<FluentWebElement> getCheckboxComboOptions() {
        return find(By.xpath(".//mat-option[.//mat-pseudo-checkbox and not(contains(@class,'reset'))]"));
    }
    private void clearResponses() {
        FluentList<FluentWebElement> respondentChoices = find(By.xpath("//button[@class='delete button-icon']"));
        if (respondentChoices.size() != 0) {
            for (FluentWebElement deleteIcon : respondentChoices) {
                if (deleteIcon.displayed()) {
                    deleteIcon.waitAndClick();
                }
            }
        }
    }
}
