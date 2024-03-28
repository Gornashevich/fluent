package ui.basicnavigation;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.fluentlenium.configuration.ConfigurationProperties;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.corrections.widgets.Row;
import pages.corrections.widgets.data.DataTable;
import ui.BaseUITest;

import java.util.List;
import java.util.stream.Collectors;
@Epic("Data page")
@Feature("Import survey data")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckImportSurveyAnswersTest extends BaseUITest {
    @Test(description = "[SS-468 Automate Data Correction Import]")
    public void importEssayTypeOfQuestionsTest() {
        DataTable table = getCorrectionTable("eb7559e4ec");
        waitUntilSurveyFullyLoaded(table);
        String pathToNewDataSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/importEssayType.xlsx";
        String pathToDefaultDataSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/importDefaultEssayType.xlsx";

        FluentList<Row> respondentDataBeforeImporting = table.getRows();
        List<String> originalDataAnswers = respondentDataBeforeImporting.stream().map(FluentWebElement::text).collect(Collectors.toList());
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToNewDataSurvey);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        waitUntilSurveyFullyLoaded(table);
        FluentList<Row> respondentDataAfterImporting = table.getRows();
        List<String> changedDataAnswers = respondentDataAfterImporting.stream().map(FluentWebElement::text).collect(Collectors.toList());

        Assert.assertNotEquals(changedDataAnswers, originalDataAnswers);
        reloadProjectPage();
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToDefaultDataSurvey);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        waitUntilSurveyFullyLoaded(table);
    }

    @Test(description = "[SS-468 Automate Data Correction Import]")
    public void importMultiSelectTypeOfQuestionsTest() {
        DataTable table = getCorrectionTable("e634732f0c");
        waitUntilSurveyFullyLoaded(table);
        String pathToNewDataSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/importNewDataMultiSelectType.xlsx";
        String pathToDefaultDataSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/importInitialDataMultiSelectType.xlsx";
        FluentList<Row> respondentDataBeforeImporting = table.getRows();
        List<String> originalDataAnswers = respondentDataBeforeImporting.stream().map(FluentWebElement::text).collect(Collectors.toList());
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToNewDataSurvey);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        waitUntilSurveyFullyLoaded(table);
        FluentList<Row> respondentDataAfterImporting = table.getRows();
        List<String> changedDataAnswers = respondentDataAfterImporting.stream().map(FluentWebElement::text).collect(Collectors.toList());

        Assert.assertNotEquals(changedDataAnswers, originalDataAnswers);
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToDefaultDataSurvey);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        waitUntilSurveyFullyLoaded(table);
    }

    @Test(description = "[SS-468 Automate Data Correction Import]")
    public void importSingleSelectTypeOfQuestionsTest() {
        DataTable table = getCorrectionTable("400230ff6f");
        waitUntilSurveyFullyLoaded(table);
        String pathToNewDataSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/importRadioSingleSelectType.xlsx";
        String pathToDefaultDataSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/importClearedRadioSingleSelectType.xlsx";
        FluentList<Row> respondentDataBeforeImporting = table.getRows();
        List<String> originalDataAnswers = respondentDataBeforeImporting.stream().map(FluentWebElement::text).collect(Collectors.toList());
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToNewDataSurvey);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        waitUntilSurveyFullyLoaded(table);
        FluentList<Row> respondentDataAfterImporting = table.getRows();
        List<String> changedDataAnswers = respondentDataAfterImporting.stream().map(FluentWebElement::text).collect(Collectors.toList());

        Assert.assertNotEquals(changedDataAnswers, originalDataAnswers);
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToDefaultDataSurvey);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        waitUntilSurveyFullyLoaded(table);
    }

    @Test(description = "[SS-468 Automate Data Correction Import]")
    public void importOtherSpecifyTypeOfQuestionsTest() {
        DataTable table = getCorrectionTable("efba966841");
        waitUntilSurveyFullyLoaded(table);
        String pathToSpecifiedOtherOptionSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/specifiedOtherValueOption.xlsx";
        String pathToNonSpecifiedOtherOptionSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/nonSpecifiedOtherValueOption.xlsx";
        FluentList<Row> respondentDataBeforeImporting = table.getRows();
        List<String> originalDataAnswers = respondentDataBeforeImporting.stream().map(FluentWebElement::text).collect(Collectors.toList());
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToSpecifiedOtherOptionSurvey);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        waitUntilSurveyFullyLoaded(table);
        FluentList<Row> respondentDataAfterImporting = table.getRows();
        List<String> changedDataAnswers = respondentDataAfterImporting.stream().map(FluentWebElement::text).collect(Collectors.toList());

        Assert.assertNotEquals(changedDataAnswers, originalDataAnswers);
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToNonSpecifiedOtherOptionSurvey);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        waitUntilSurveyFullyLoaded(table);
    }

    @Test(description = "[SS-468 Automate Data Correction Import]")
    public void importClearedEssayTypeOfQuestionsTest() {
        DataTable table = getCorrectionTable("ac64f72708");
        waitUntilSurveyFullyLoaded(table);
        String pathToEmptyDataSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/clearedEssaySurvey.xlsx";
        String pathToDefaultDataSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/fullEssaySurvey.xlsx";

        FluentList<Row> respondentDataBeforeImporting = table.getRows();
        List<String> originalDataAnswers = respondentDataBeforeImporting.stream().map(FluentWebElement::text).collect(Collectors.toList());
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToEmptyDataSurvey);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        waitUntilSurveyFullyLoaded(table);
        FluentList<Row> respondentDataAfterImporting = table.getRows();
        List<String> changedDataAnswers = respondentDataAfterImporting.stream().map(FluentWebElement::text).collect(Collectors.toList());

        Assert.assertNotEquals(changedDataAnswers, originalDataAnswers);
        reloadProjectPage();
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToDefaultDataSurvey);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        waitUntilSurveyFullyLoaded(table);
    }

    @Test(description = "[SS-468 Automate Data Correction Import]")
    public void importClearedMultiSelectTypeOfQuestionsTest() {
        DataTable table = getCorrectionTable("8fe9da7c89");
        waitUntilSurveyFullyLoaded(table);
        String pathToNewDataSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/clearedMultiSelectSurvey.xlsx";
        String pathToDefaultDataSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/fullMultiSelectSurvey.xlsx";
        FluentList<Row> respondentDataBeforeImporting = table.getRows();
        List<String> originalDataAnswers = respondentDataBeforeImporting.stream().map(FluentWebElement::text).collect(Collectors.toList());
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToNewDataSurvey);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        waitUntilSurveyFullyLoaded(table);
        FluentList<Row> respondentDataAfterImporting = table.getRows();
        List<String> changedDataAnswers = respondentDataAfterImporting.stream().map(FluentWebElement::text).collect(Collectors.toList());

        Assert.assertNotEquals(changedDataAnswers, originalDataAnswers);
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToDefaultDataSurvey);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        waitUntilSurveyFullyLoaded(table);
    }

    @Test(description = "[SS-468 Automate Data Correction Import]")
    public void importClearedSingleSelectTypeOfQuestionsTest() {
        DataTable table = getCorrectionTable("31e62c2c5c");
        waitUntilSurveyFullyLoaded(table);
        String pathToNewDataSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/importRadioSingleSelectType.xlsx";
        String pathToDefaultDataSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/importClearedRadioSingleSelectType.xlsx";
        FluentList<Row> respondentDataBeforeImporting = table.getRows();
        List<String> originalDataAnswers = respondentDataBeforeImporting.stream().map(FluentWebElement::text).collect(Collectors.toList());
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToNewDataSurvey);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        waitUntilSurveyFullyLoaded(table);
        FluentList<Row> respondentDataAfterImporting = table.getRows();
        List<String> changedDataAnswers = respondentDataAfterImporting.stream().map(FluentWebElement::text).collect(Collectors.toList());

        Assert.assertNotEquals(changedDataAnswers, originalDataAnswers);
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToDefaultDataSurvey);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        waitUntilSurveyFullyLoaded(table);
    }

    @Test(description = "[SS-468 Automate Data Correction Import]")
    public void importSurveyWithDataIssuesTest() {
        DataTable table = getCorrectionTable("82d1c4881e");
        waitUntilSurveyFullyLoaded(table);
        String pathToInvalidDataSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/surveyWithDataIssues.xlsx";
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToInvalidDataSurvey);
        importMenu.clickImportButton();
        Assert.assertTrue(importMenu.isDataIssueNotificationMessageDisplay());
        Assert.assertTrue(importMenu.isDownloadDataIssueButtonDisplay());


    }

    @Test(description = "[SS-468 Automate Data Correction Import]")
    public void importSurveyWithMetaDataIssuesTest() {
        DataTable table = getCorrectionTable("82d1c4881e");
        waitUntilSurveyFullyLoaded(table);
        String pathToInvalidDataSurvey = "D:/Epam/src/Fluentlenium/src/main/resources/surveyWithMetaDataIssues.xlsx";
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToInvalidDataSurvey);
        importMenu.clickImportButton();
        Assert.assertTrue(importMenu.isMetadataIssueNotificationMessageDisplay());
        Assert.assertTrue(importMenu.isDownloadMetadataIssueButtonDisplay());

    }

    @Test(description = "[SS-468 Automate Data Correction Import]")
    public void createNewRespondentViaImportTest() {
        DataTable table = getCorrectionTable("63f3bab346");
        waitUntilSurveyFullyLoaded(table);
        String pathToSurveyWithoutRespondentId = "D:/Epam/src/Fluentlenium/src/main/resources/surveyWithoutRespondentId.xlsx";
        List<FluentWebElement> respondentIdList1 = table.getRespondentIdList();
        int initialRespondentsAmount = respondentIdList1.size();
        System.out.println(initialRespondentsAmount);
        table.getExpandImportButton().click();
        table.getImportButton().click();
        importMenu.importSurveyFile(pathToSurveyWithoutRespondentId);
        importMenu.clickImportButton();
        importMenu.clickOnDoneButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> respondentIdList2 = find(By.xpath("//td[contains(@class,'respondentId')]"));
        int currentRespondentsAmount = respondentIdList2.size();
        System.out.println(currentRespondentsAmount);
        Assert.assertNotEquals(currentRespondentsAmount, initialRespondentsAmount);

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
