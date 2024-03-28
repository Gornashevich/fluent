package ui.basicnavigation;

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
import ui.BaseUITest;
import utils.RandomDataGenerator;
import utils.RetryAnalyzer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
@Feature("MetaData page - Creation of virtual questions")
public class CheckVirtualQuestionCreationOnMetaDataTest extends BaseUITest {
    @Description("Ensure that Single virtual question can be created in MetaData tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"groupNeedingCleanup"})
    public void testAddSinglePunchVirtualQuestionForMetadataTab() {
        DataTable table = getDataTable("8bd0a7a7d7");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        var newQuestionText = RandomDataGenerator.getRandomText();
        metadataTable.getAddQuestionButton().click();
        newVirtualQuestion.clickAndAddSinglePunchVirtualQuestion(newQuestionText);
        newVirtualQuestion.clickOnAddButton();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        List<FluentWebElement> questionNameList = getQuestionNameList();
        List<FluentWebElement> questionTextList = getQuestionTextList();
        boolean isVirtualQuestionDisplayedInMetaDataTab = metadataTable.isConfiguredVirtualQuestionDataDisplays("Test" + newQuestionText, questionNameList);
        boolean isVirtualQuestionTextDisplayedInMetaDataTab = metadataTable.isConfiguredVirtualQuestionDataDisplays(newQuestionText, questionTextList);

        assertThat(isVirtualQuestionDisplayedInMetaDataTab).isTrue();
        assertThat(isVirtualQuestionTextDisplayedInMetaDataTab).isTrue();

    }

    @Description("Ensure that Multi Checkbox virtual question can be created in MetaData tab ")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"groupNeedingCleanup"})
    public void testAddMultiPunchVirtualQuestionForMetadataTab() {
        DataTable table = getDataTable("061157d581");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        var newQuestionText = "Test" + RandomDataGenerator.getRandomText();
        metadataTable.getAddQuestionButton().click();
        newVirtualQuestion.clickAndAddMultiPunchPunchVirtualQuestion(newQuestionText);
        newVirtualQuestion.fullFillQuestionResponseData(newQuestionText);
        newVirtualQuestion.clickOnAddButton();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        List<FluentWebElement> questionNameList = getQuestionNameList();
        List<FluentWebElement> questionTextList = getQuestionTextList();
        List<FluentWebElement> rowsChoiceAnswers = getRowsChoiceAnswers();
        boolean isVirtualQuestionDisplayedInMetaDataTab = metadataTable.isConfiguredVirtualQuestionDataDisplays(newQuestionText, questionNameList);
        boolean isVirtualQuestionTextDisplayedInMetaDataTab = metadataTable.isConfiguredVirtualQuestionDataDisplays(newQuestionText, questionTextList);
        boolean isVirtualChoicesTextDisplayedInMetaDataTab = metadataTable.isConfiguredVirtualQuestionDataDisplays(newQuestionText, rowsChoiceAnswers);

        assertThat(isVirtualQuestionDisplayedInMetaDataTab).isTrue();
        assertThat(isVirtualQuestionTextDisplayedInMetaDataTab).isTrue();
        assertThat(isVirtualChoicesTextDisplayedInMetaDataTab).isTrue();

    }

    @Description("Ensure that Multi Essay virtual question can be created in MetaData tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"groupNeedingCleanup"})
    public void testAddEssayVirtualQuestionForMetadataTab() {
        DataTable table = getDataTable("0fd241080b");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        var newQuestionText = RandomDataGenerator.getRandomText();
        metadataTable.getAddQuestionButton().click();
        newVirtualQuestion.clickAndAddEssaySingleVirtualQuestion(newQuestionText);
        newVirtualQuestion.clickOnAddButton();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        List<FluentWebElement> questionNameList = getQuestionNameList();
        List<FluentWebElement> questionTextList = getQuestionTextList();
        boolean isVirtualQuestionDisplayedInMetaDataTab = metadataTable.isConfiguredVirtualQuestionDataDisplays("Test" + newQuestionText, questionNameList);
        boolean isVirtualQuestionTextDisplayedInMetaDataTab = metadataTable.isConfiguredVirtualQuestionDataDisplays(newQuestionText, questionTextList);

        assertThat(isVirtualQuestionDisplayedInMetaDataTab).isTrue();
        assertThat(isVirtualQuestionTextDisplayedInMetaDataTab).isTrue();

    }

    @Description("Ensure that numeric single virtual question is displayed on Data tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"groupNeedingCleanup"})
    public void testCreatedSinglePunchVirtualQuestionOnDataTab() {
        DataTable table = getDataTable("1322ec3ad7");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        var newQuestionText = RandomDataGenerator.getRandomText();
        metadataTable.getAddQuestionButton().click();
        newVirtualQuestion.clickAndAddSinglePunchVirtualQuestion(newQuestionText);
        newVirtualQuestion.clickOnAddButton();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        correctionsPage.switchToDataPage();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> questionsRow = table.getRespondentRowList();
        boolean isVirtualQuestionDataDisplayed = table.isConfiguredVirtualDataDisplays(newQuestionText, questionsRow);
        assertThat(isVirtualQuestionDataDisplayed).isTrue();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
    }

    @Description("Ensure that Essay virtual question is displayed on Data tab")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreatedEssayVirtualQuestionForMetadataTab() {
        DataTable table = getDataTable("741ad84de3");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        var newQuestionText = RandomDataGenerator.getRandomText();
        metadataTable.getAddQuestionButton().click();
        newVirtualQuestion.clickAndAddEssaySingleVirtualQuestion(newQuestionText);
        newVirtualQuestion.clickOnAddButton();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        correctionsPage.switchToDataPage();
        waitUntilSurveyFullyLoaded(table);
        List<FluentWebElement> questionsRow = table.getRespondentRowList();
        boolean isVirtualDataDisplayedOnDataTab = table.isConfiguredVirtualDataDisplays(newQuestionText, questionsRow);
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        assertThat(isVirtualDataDisplayedOnDataTab).isTrue();
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

    private List<FluentWebElement> getQuestionNameList() {
        return find(By.xpath(".//mat-cell[contains(@class,'questionName')]"));
    }

    private List<FluentWebElement> getQuestionTextList() {
        return find(By.xpath("//mat-cell[contains(@class,'questionText')]"));
    }

    private List<FluentWebElement> getRowsChoiceAnswers() {
        return find(By.xpath("//mat-cell[contains(@class,'mat-column-row')]"));
    }

    @AfterMethod(groups = {"groupNeedingCleanup"})
    public void cleanup() {
        List<FluentWebElement> rows = metadataTable.getRows();
        while (rows.size() > 1) {
            find(By.xpath("//mat-cell[contains(@class,'mat-column-delete')]")).last().mouse().click();
            FluentWebElement deleteButton = el(By.xpath("//button[contains(@class,'dialog-save-button red')]"));
            if (deleteButton.present()) {
                deleteButton.waitAndClick();
            } else {
                break;
            }
        }

    }
}
