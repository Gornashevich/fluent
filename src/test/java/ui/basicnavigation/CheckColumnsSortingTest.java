package ui.basicnavigation;


import dataprovider.QuestionTypeColumns;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.fluentlenium.configuration.ConfigurationProperties;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.corrections.widgets.data.DataTable;
import pages.project.Column;
import ui.BaseUITest;
import utils.RetryAnalyzer;

import java.util.List;
import java.util.stream.Collectors;

@Epic("Correction page")
@Feature("Data page - Sorting survey responses data")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckColumnsSortingTest extends BaseUITest {
    @Description("Ensure that numeric answers can be sorted")
    @Test(retryAnalyzer = RetryAnalyzer.class,dataProvider = "getOnlyNumericNumberTextBoxSingle",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSortNumericColumnValues(Column column) {
        DataTable table = getDataTable("1f87956851");
        waitUntilSurveyFullyLoaded(table);

        table.getRows()
                .first()
                .getInputBoxForColumn(column);

        List<FluentWebElement> defaultColumnAnswersOrderList = find(By.xpath(".//div[contains(@class,'mat-form-field')]/input[contains(@data-keyboard-trigger,'true')]"));
        List<String> originalNumericValuesByDataTab = defaultColumnAnswersOrderList.stream().map(x -> x.attribute("value")).collect(Collectors.toList());
        List<String> getSortedNumericValuesByStream = table.sortColumnAnswersByAscOrder(defaultColumnAnswersOrderList);
        table.hoverOverColumn(column.getValue());
        table.clickOnDefaultArrow(column.getValue());
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> sortedByAscOrderColumnAnswersList = find(By.xpath(".//div[contains(@class,'mat-form-field')]/input[contains(@data-keyboard-trigger,'true')]"));
        List<String> getSortedNumericByDataTabAsc = table.getColumnAnswers(sortedByAscOrderColumnAnswersList);
        Assert.assertEquals(getSortedNumericByDataTabAsc, getSortedNumericValuesByStream);


        table.getActiveSortArrow().waitAndClick();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> sortedByDataTabDescOrder = find(By.xpath(".//div[contains(@class,'mat-form-field')]/input[contains(@data-keyboard-trigger,'true')]"));
        List<String> getSortedNumericValuesByDataTab = table.getColumnAnswers(sortedByDataTabDescOrder);
        List<String> getSortedDescNumericValuesByStream = table.sortColumnAnswersByDescOrder(defaultColumnAnswersOrderList);

        Assert.assertEquals(getSortedDescNumericValuesByStream, getSortedNumericValuesByDataTab);

        table.getActiveSortArrow().click();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> sortedByDataTabDefaultOrder = find(By.xpath(".//div[contains(@class,'mat-form-field')]/input[contains(@data-keyboard-trigger,'true')]"));
        List<String> defaultNumericValuesOrderByDataTab = sortedByDataTabDefaultOrder.stream().map(x -> x.attribute("value")).collect(Collectors.toList());

        Assert.assertEquals(defaultNumericValuesOrderByDataTab, originalNumericValuesByDataTab);

    }
    @Description("Ensure that Essay answers can be sorted")
    @Test(retryAnalyzer = RetryAnalyzer.class,dataProvider = "getOnlyEssayTextAreaSingle",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSortEssayColumnValues(Column column) {
        DataTable table = getDataTable("dc0a399a59");
        table.waitUntilRowsLoaded();
        table.getRows()
                .first()
                .getInputBoxForColumn(column);

        List<FluentWebElement> defaultColumnAnswersOrderList = find(By.xpath(".//div[contains(@class,'mat-form-field')]/input[contains(@data-keyboard-trigger,'true')]"));
        List<String> originalNumericValuesByDataTab = defaultColumnAnswersOrderList.stream().map(x -> x.attribute("value")).collect(Collectors.toList());
        List<String> getSortedNumericValuesByStream = table.sortColumnAnswersByAscOrder(defaultColumnAnswersOrderList);
        table.hoverOverColumn(column.getValue());
        table.clickOnDefaultArrow(column.getValue());
        waitUntilSurveyFullyLoaded(table);
        table.waitUntilProjectLoadedMessageDisappears();
        List<FluentWebElement> sortedByAscOrderColumnAnswersList = find(By.xpath(".//div[contains(@class,'mat-form-field')]/input[contains(@data-keyboard-trigger,'true')]"));
        List<String> getSortedNumericByDataTabAsc = table.getColumnAnswers(sortedByAscOrderColumnAnswersList);
        Assert.assertEquals(getSortedNumericByDataTabAsc, getSortedNumericValuesByStream);

        table.getActiveSortArrow().click();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> sortedByDataTabDescOrder = find(By.xpath(".//div[contains(@class,'mat-form-field')]/input[contains(@data-keyboard-trigger,'true')]"));
        List<String> getSortedNumericValuesByDataTab = table.getColumnAnswers(sortedByDataTabDescOrder);
        List<String> getSortedDescNumericValuesByStream = table.sortColumnAnswersByDescOrder(defaultColumnAnswersOrderList);
        Assert.assertEquals(getSortedDescNumericValuesByStream, getSortedNumericValuesByDataTab);

        table.getActiveSortArrow().click();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> sortedByDataTabDefaultOrder = find(By.xpath(".//div[contains(@class,'mat-form-field')]/input[contains(@data-keyboard-trigger,'true')]"));
        List<String> defaultNumericValuesOrderByDataTab = sortedByDataTabDefaultOrder.stream().map(x -> x.attribute("value")).collect(Collectors.toList());

        Assert.assertEquals(defaultNumericValuesOrderByDataTab, originalNumericValuesByDataTab);
    }

    @Description("Ensure that radio answers can be sorted")
    @Test(retryAnalyzer = RetryAnalyzer.class,dataProvider = "getOnlyNPSSingle",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSortRadioColumnValues(Column column) {
        DataTable table = getDataTable("8e23b8c4e4");
        waitUntilSurveyFullyLoaded(table);

        table.getRows()
                .first()
                .getComboBoxForColumn(column);

        List<FluentWebElement> defaultColumnAnswersOrderList = find(By.xpath("//app-table-single-select-nps"));
        List<String> sortedRadioOrderColumnByAsc = table.getColumnAnswers(defaultColumnAnswersOrderList);
        table.hoverOverColumn(column.getValue());
        table.clickOnDefaultArrow(column.getValue());
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> sortedByAscOrderColumnAnswersList = find(By.xpath("//app-table-single-select-nps"));
        List<String> sortedColumnsByDataTabAsc = table.getColumnAnswers(sortedByAscOrderColumnAnswersList);
        Assert.assertEquals(sortedColumnsByDataTabAsc, sortedRadioOrderColumnByAsc);

        table.getActiveSortArrow().waitAndClick();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> sortedByDescOrderColumnAnswersList = find(By.xpath("//app-table-single-select-nps"));
        List<String> sortedColumnsByDataTabDesc = table.getColumnAnswers(sortedByDescOrderColumnAnswersList);
        List<String> sortedNumericOrderColumnByDesc = table.sortColumnAnswersByDescOrder(defaultColumnAnswersOrderList);
        Assert.assertEquals(sortedNumericOrderColumnByDesc, sortedColumnsByDataTabDesc);

        table.getActiveSortArrow().waitAndClick();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> restoredDefaultColumnAnswersOrderList = find(By.xpath("//app-table-single-select-nps"));
        List<String> defaultNumericValuesOrderByDataTab = table.getColumnAnswers(restoredDefaultColumnAnswersOrderList);
        Assert.assertEquals(defaultNumericValuesOrderByDataTab, sortedRadioOrderColumnByAsc);

    }

    @Description("Ensure that radio column answers can be sorted after numeric value switcher applying")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testSortRadioColumnValuesWithNumericSwitcher() {
        DataTable table = getDataTable("71b10c88ed");
        table.waitUntilRowsLoaded();

        table.getRows()
                .first()
                .getComboBoxForColumn(Column.SINGLE_SELECT_RADIO_COLUMN);

        correctionsPage.getSettingsSideBar().click();
        settingsSideBar.switchToNumericValueButton();
        settingsSideBar.clickOnApplyButton();
        List<FluentWebElement> defaultColumnAnswersOrderList = table.getColumnAnswersList(Column.SINGLE_SELECT_RADIO_COLUMN.getValue());
        List<String> sortedRadioOrderColumnByAsc = table.sortColumnAnswersByAscOrder(defaultColumnAnswersOrderList);
        table.hoverOverColumn(Column.SINGLE_SELECT_RADIO_COLUMN.getValue());
        table.clickOnDefaultArrow(Column.SINGLE_SELECT_RADIO_COLUMN.getValue());
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> sortedByAscOrderColumnAnswersList = table.getColumnAnswersList(Column.SINGLE_SELECT_RADIO_COLUMN.getValue());
        List<String> sortedColumnsByDataTabAsc = table.getColumnAnswers(sortedByAscOrderColumnAnswersList);
        Assert.assertEquals(sortedColumnsByDataTabAsc, sortedRadioOrderColumnByAsc);


        table.getActiveSortArrow().waitAndClick();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> sortedByDescOrderColumnAnswersList = table.getColumnAnswersList(Column.SINGLE_SELECT_RADIO_COLUMN.getValue());
        List<String> sortedColumnsByDataTabDesc = table.getColumnAnswers(sortedByDescOrderColumnAnswersList);
        List<String> sortedNumericOrderColumnByDesc = table.sortColumnAnswersByDescOrder(defaultColumnAnswersOrderList);
        Assert.assertEquals(sortedNumericOrderColumnByDesc, sortedColumnsByDataTabDesc);

        table.getActiveSortArrow().click();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> restoredDefaultColumnAnswersOrderList = table.getColumnAnswersList(Column.SINGLE_SELECT_RADIO_COLUMN.getValue());
        List<String> defaultSortColumns = table.sortColumnAnswersByAscOrder(restoredDefaultColumnAnswersOrderList);
        Assert.assertEquals(defaultSortColumns, sortedRadioOrderColumnByAsc);
    }

    @Description("Ensure that numeric answers can be sorted after numeric value switcher applying")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testSortRadioColumnWithIterationValues() {
        DataTable table = getDataTable("ecd3c634dd");
        table.waitUntilRowsLoaded();

        table.getRows()
                .first()
                .getComboBoxForColumn(Column.NPS_SINGLE);

        correctionsPage.getSettingsSideBar().click();
        settingsSideBar.switchToNumericValueButton();
        settingsSideBar.clickOnApplyButton();
        List<FluentWebElement> defaultColumnAnswersOrderList = table.getColumnAnswersList(Column.NPS_SINGLE.getValue());
        List<String> sortedRadioOrderColumnByAsc = table.sortColumnAnswersByAscOrder(defaultColumnAnswersOrderList);
        table.hoverOverColumn(Column.NPS_SINGLE.getValue());
        table.clickOnDefaultArrow(Column.NPS_SINGLE.getValue());
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> sortedByAscOrderColumnAnswersList = table.getColumnAnswersList(Column.NPS_SINGLE.getValue());
        List<String> sortedColumnsByDataTabAsc = table.getColumnAnswers(sortedByAscOrderColumnAnswersList);
        Assert.assertEquals(sortedColumnsByDataTabAsc, sortedRadioOrderColumnByAsc);

        table.getActiveSortArrow().waitAndClick();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> sortedByDescOrderColumnAnswersList = table.getColumnAnswersList(Column.NPS_SINGLE.getValue());
        List<String> sortedColumnsByDataTabDesc = table.getColumnAnswers(sortedByDescOrderColumnAnswersList);
        List<String> sortedNumericOrderColumnByDesc = table.sortColumnAnswersByDescOrder(defaultColumnAnswersOrderList);
        Assert.assertEquals(sortedNumericOrderColumnByDesc, sortedColumnsByDataTabDesc);

        table.getActiveSortArrow().waitAndClick();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> restoredDefaultColumnAnswersOrderList = table.getColumnAnswersList(Column.NPS_SINGLE.getValue());
        List<String> defaultSortColumns = table.sortColumnAnswersByAscOrder(restoredDefaultColumnAnswersOrderList);
        Assert.assertEquals(defaultSortColumns, sortedRadioOrderColumnByAsc);
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
}
