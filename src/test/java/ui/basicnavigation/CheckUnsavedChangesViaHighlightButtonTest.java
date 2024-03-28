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
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.corrections.widgets.data.DataTable;
import pages.project.Column;
import ui.BaseUITest;
import utils.RetryAnalyzer;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@Feature("Data page - Highlight adjusted survey data")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckUnsavedChangesViaHighlightButtonTest extends BaseUITest {
    @Description("Ensure that 'unsaved changes' can be highlighted")
    @Test(groups = {"groupNeedingCleanup"}, retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyRadioButtonColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testUnsavedChangesViaHighlightButton(Column column) {
        DataTable table = getDataTable("5e62b00c0c");
        table.waitUntilRowsLoaded();
        table.waitUntilProjectLoadedMessageDisappears();

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);

        String cellColorValueBeforeAdjusting = getCellColorValue(column.getValue());
        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        correctionsPage.getSettingsSideBar().click();
        settingsSideBar.clickOnHighlightUnsavedChangesButton();
        settingsSideBar.clickOnApplyButton();
        String nonChangedCellColorValue = getCellColorValue(column.getValue());
        comboBox.getSetToOriginalIcon().first().hoverOver();
        boolean initialRespondentDataDisplays = setToOriginalMenu.isInitialRespondentDataDisplays();

        assertThat(nonChangedCellColorValue).isNotEqualTo(cellColorValueBeforeAdjusting);
        assertThat(initialRespondentDataDisplays).isTrue();
    }


    @AfterMethod(groups = {"groupNeedingCleanup"})
    public void cleanup() {
        correctionsPage.getSettingsSideBar().waitAndClick();
        settingsSideBar.clickOnHighlightAllChangesButton();
        settingsSideBar.clickOnApplyButton();
    }

    private DataTable getDataTable(String projectId) {
        return correctionsPage
                .go(56, projectId)
                .getDataTable();
    }

    public String getCellColorValue(String columName) {
        String formatValue = String.format("//td[contains(@class,'%s')]/app-table-cell-wrapper/div", columName);
        FluentWebElement el = el(By.xpath(formatValue));
        String cssValue = el.cssValue("background-color");
        return cssValue;
    }
}
