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

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@Feature("Data page - Survey statuses adjusting")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckSavingStatusesTest extends BaseUITest {
    @Description("Ensure that survey can be changed")
    @Test(retryAnalyzer = RetryAnalyzer.class,dataProvider = "getStatusColumn", dataProviderClass = QuestionTypeColumns.class)
    public void testChangeSurveyStatus(Column column) {
        DataTable table = getDataTable("e9027bbd33");
        waitUntilSurveyFullyLoaded(table);
        table.resetFiltersFromDataTab();

        ComboBox comboBox = table.getRows()
                .last()
                .getComboBoxForColumn(column);
        correctionsPage.getFiltersSidebar().click();
        filterSideBar.clickOnRespondentStatusMenu();
        filterSideBar.getCheckedRespondentStatuses();
        filterSideBar.clickOnApplyButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        String initialRespondentStatus = table.getLastRespondentStatus();
        comboBox.open().getUnselectedRadioButtonOptions().first().click();
        correctionsPage.getSaveButton().click();
        String adjustedRespondentStatus = table.getLastRespondentStatus();

        assertThat(adjustedRespondentStatus).isNotEqualTo(initialRespondentStatus);

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
