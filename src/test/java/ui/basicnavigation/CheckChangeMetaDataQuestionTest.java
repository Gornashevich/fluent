package ui.basicnavigation;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.fluentlenium.configuration.ConfigurationProperties;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.corrections.widgets.data.DataTable;
import ui.BaseUITest;
import utils.RandomDataGenerator;
import utils.RetryAnalyzer;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
@Feature("MetaData page - Adjusting the questions and respondents responses")
public class CheckChangeMetaDataQuestionTest extends BaseUITest {
    @Description("Ensure that question text can be modified in Metadata tab")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testChangeMetaDataQuestionText() {
        DataTable table = getDataTable("adcfd4a2b9");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        int index = RandomDataGenerator.getRandomNumberValue(getRows().size());
        var newQuestionText = RandomDataGenerator.getRandomText();
        metadataTable.clickOnQuestionTextMenu(index);
        questionTextMenu.changeQuestionText(newQuestionText);
        questionTextMenu.clickOnUpdateButton();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        String questionTextCells = metadataTable.getQuestionTextCells()
                .stream()
                .filter(s -> s.contains(newQuestionText))
                .findFirst()
                .orElseThrow();
        // String actualText = metadataTable.getCell("Question Text", index, true).text();
        assertThat(newQuestionText).isEqualTo(questionTextCells);
    }

    @Description("Ensure that adjusted question text presence on Data tab")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testChangedQuestionTextInDataTab() {
        DataTable table = getDataTable("2c044e2e6e");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.switchToMetadataPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        int index = RandomDataGenerator.getRandomNumberValue(getRows().size());
        var newQuestionText = RandomDataGenerator.getRandomText();
        metadataTable.clickOnQuestionTextMenu(index);
        questionTextMenu.changeQuestionText(newQuestionText);
        questionTextMenu.clickOnUpdateButton();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        metadataTable.waitUntilMetaDataRowsLoaded();
        correctionsPage.switchToDataPage();
        waitUntilSurveyFullyLoaded(table);
        String changedDataTabQuestion = correctionsPage.getDataTable()
                .getHeadersText()
                .stream().filter(s -> s.contains(newQuestionText)).findFirst().orElseThrow();
        assertThat(changedDataTabQuestion).contains(newQuestionText);
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

    private List<FluentWebElement> getRows() {
        FluentList<FluentWebElement> elements = find(By.xpath("//mat-row[@role='row']"));
        return elements.stream().filter(i ->
                !i.text().trim().contains("mos_Id")
                        && !i.text().trim().contains("mos_panelid")).collect(Collectors.toList());
    }


}