package ui.basicnavigation;

import dataprovider.QuestionTypeColumns;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.fluentlenium.configuration.ConfigurationProperties;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.corrections.widgets.data.DataTable;
import pages.project.Column;
import ui.BaseUITest;
import utils.RetryAnalyzer;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@Feature("Data page - Highlight adjusted survey data")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckAllChangesViaHighlightButtonTest extends BaseUITest {
    @Description("Ensure that 'all changes' answers can be highlighted ")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyRadioButtonColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testAllChangesViaHighlightButton(Column column) {
        DataTable table = getDataTable("350c5cedad");
        table.waitUntilRowsLoaded();
        table.waitUntilProjectLoadedMessageDisappears();
        correctionsPage.getSettingsSideBar().click();
        settingsSideBar.clickOnHighlightUnsavedChangesButton();
        settingsSideBar.clickOnApplyButton();

        table.getRows()
                .first()
                .getComboBoxForColumn(column);


        String cellColorValueBeforeAdjusting = getCellColorValue(column.getValue());
        correctionsPage.clickOnSettingsButton();
        settingsSideBar.clickOnHighlightAllChangesButton();
        settingsSideBar.clickOnApplyButton();
        String nonChangedCellColorValue = getCellColorValue(column.getValue());
        assertThat(nonChangedCellColorValue).isNotEqualTo(cellColorValueBeforeAdjusting);

    }

    public String getCellColorValue(String columName) {
        String formatValue = String.format("//td[contains(@class,'%s')]/app-table-cell-wrapper/div", columName);
        FluentWebElement el = el(By.xpath(formatValue));
        String cssValue = el.cssValue("background-color");
        return cssValue;
    }

    private DataTable getDataTable(String projectId) {
        return correctionsPage
                .go(56, projectId)
                .getDataTable();
    }
}
