package ui.basicnavigation;

import commonWidgets.ComboBox;
import dataprovider.QuestionTypeColumns;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.fluentlenium.configuration.ConfigurationProperties;
import org.fluentlenium.configuration.FluentConfiguration;
import org.testng.annotations.Test;
import pages.corrections.widgets.data.DataTable;
import pages.project.Column;
import ui.BaseUITest;
import utils.RetryAnalyzer;

import static commonWidgets.ComboBox.getComboBoxValue;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@Feature("Data page - Automate 'Cancel' option checks")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckCancelChangesButtonTest extends BaseUITest {
    @Description("Check that  'Cancel' button  reverts changes to the previous state")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyRadioButtonColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testCancelChangesViaButtonTest(Column column) {
        DataTable table = getDataTable("6f7db0b604");
        table.waitUntilRowsLoaded();
        table.waitUntilProjectLoadedMessageDisappears();

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);

        String cellBeforeChanging = getComboBoxValue(comboBox);
        comboBox.open().getUnselectedRadioButtonOptions().first().click();
        correctionsPage.getCancelButton().click();
        cancelChanges.clickOnYesProceedButton();
        boolean allChangesSavedNotificationMessageDisplays = table.isAllChangesSavedNotificationMessageDisplays();
        assertThat(allChangesSavedNotificationMessageDisplays).isTrue();
        String cellAfterChanging = getComboBoxValue(comboBox);
        assertThat(cellAfterChanging).isEqualTo(cellBeforeChanging);
    }

    @Description("Check that 'back' is in the confirmation dialog  kept  all changes in the Data tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyRadioButtonColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testBackChangesViaButton(Column column) {
        DataTable table = getDataTable("49f070ce56");
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);

        String cellBeforeChanging = getComboBoxValue(comboBox);
        comboBox.open().getUnselectedRadioButtonOptions().first().click();
        correctionsPage.clickOnCancelButton();
        cancelChanges.clickOnBackButton();
        boolean changesAreNotSavedNotificationMessageDisplays = table.isChangesAreNotSavedNotificationMessageDisplays();
        assertThat(changesAreNotSavedNotificationMessageDisplays).isTrue();
        String cellAfterChanging = getComboBoxValue(comboBox);
        assertThat(cellAfterChanging).isNotEqualTo(cellBeforeChanging);
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