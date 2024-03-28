package ui.basicnavigation;

import commonWidgets.InputBox;
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
import utils.RandomDataGenerator;
import utils.RetryAnalyzer;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@Feature("Data page - Other specify option")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckOtherSpecifyAnswerTest extends BaseUITest {
    @Description("Ensure that other specified answer is displayed")
    @Test(retryAnalyzer = RetryAnalyzer.class,dataProvider = "getOtherSpecifyAnswers",
            dataProviderClass = QuestionTypeColumns.class)
    public void testCheckOtherSpecifiedAnswerPresence(Column column) {
        DataTable table = getCorrectionTable("55029b33dd");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        waitUntilSurveyFullyLoaded(table);
        String inputFieldValue = InputBox.getOtherSpecifyInputField(inputBox);
        inputBox.clickAndChangeOtherSpecify((RandomDataGenerator.getRandomNumberValue()));
        clickOnAnyPlace();
        correctionsPage.clickOnSaveButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        String textFromNewOption = InputBox.getOtherSpecifyInputField(inputBox);

        assertThat(inputFieldValue).isNotEqualTo(textFromNewOption);


    }

    private void waitUntilSurveyFullyLoaded(DataTable table) {
        table.waitUntilRowsLoaded();
        table.waitUntilProjectLoaded();
        table.waitUntilProjectLoadedMessageDisappears();
    }

    private DataTable getCorrectionTable(String projectId) {
        return correctionsPage
                .go(56, projectId)
                .getDataTable();
    }

    private void reloadProjectPage() {
        correctionsPage.getDriver().navigate().refresh();
    }
}
