package ui.basicnavigation;

import commonWidgets.ComboBox;
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
import utils.RetryAnalyzer;

import static commonWidgets.ComboBox.getComboBoxValue;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@Feature("Data page - Numeric switcher data option")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckNumericSwitcherMenuTest extends BaseUITest {
    @Description("Ensure that numeric value switcher changes displayed survey data")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyCheckboxColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetNumericValueViaNumericSwitchButton(Column column) {
        DataTable table = getCorrectionTable("e3d382fcef");
        waitUntilSurveyFullyLoaded(table);
        var firstNumericAnswerOption = "1";

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);
        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        correctionsPage.clickOnSaveButton();
        waitUntilSurveyFullyLoaded(table);
        restoreCleanedCheckboxAnswer(comboBox);
        clickOnAnyPlace();
        correctionsPage.clickOnSaveButton();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.getSettingsSideBar().click();
        settingsSideBar.switchToNumericValueButton();
        settingsSideBar.clickOnApplyButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        String switchedNumericValueOption = getComboBoxValue(comboBox);
        correctionsPage.getSettingsSideBar().click();
        settingsSideBar.getTextValueButton().click();
        settingsSideBar.clickOnApplyButton();
        waitUntilSurveyFullyLoaded(table);

        assertThat(switchedNumericValueOption.contains(firstNumericAnswerOption));
    }


    private FluentWebElement restoreCleanedCheckboxAnswer(ComboBox comboBox) {
        comboBox.await().until().displayed();
        comboBox.await().until().enabled();
        return comboBox.open().getUnselectedCheckBoxButtonOptions().first().click();
    }

    private DataTable getCorrectionTable(String projectId) {
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
