package ui.basicnavigation;

import commonWidgets.ComboBox;
import commonWidgets.InputBox;
import commonWidgets.ListBox;
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
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
@Feature("Data page - Survey validation warning messages")
public class CheckDataTabUIValidationTest extends BaseUITest {
    @Description("Check warning notification 30006 for Ranked Questions")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getComboBoxForRankedQuestions",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetExceedingRankValue(Column column) {
        DataTable table = getCorrectionTable("ee7d30908");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        inputBox.clearInputBox();
        inputBox.changeNumericInputFieldValue(RandomDataGenerator.getRandomNumberValue());
        clickOnAnyPlace();
        correctionsPage.getSaveButton().waitAndClick();
        waitUntilSurveyFullyLoaded(table);
        pageIssue.clickOnFoundIssueNotification();
        String issueDescription = dataIssuesContainer.getDataIssueDescriptionText();
        String issueCode = dataIssuesContainer.getDataIssueCode().text();

        assertThat(issueDescription).isEqualTo("Rank value exceeds the number of options to rank.");
        assertThat(issueCode).isEqualTo("30006");

    }

    @Description("Check warning notification 30015 for Ranked Questions")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getComboBoxForRankedQuestions",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetNonUniqueRankValue(Column column) {
        final int nonUniqueValue = 3;
        DataTable table = getCorrectionTable("8df030d86c");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        inputBox.clearInputBox();
        clickOnAnyPlace();
        correctionsPage.getSaveButton().doubleClick();
        waitUntilSurveyFullyLoaded(table);
        inputBox.changeNumericInputFieldValue(nonUniqueValue);
        clickOnAnyPlace();
        correctionsPage.getSaveButton().waitAndClick();
        pageIssue.clickOnFoundIssueNotification();
        String issueDescription = dataIssuesContainer.getDataIssueDescriptionText();
        String issueCode = dataIssuesContainer.getDataIssueCode().text();

        assertThat(issueDescription).isEqualTo("Response for rank question should have unique values.");
        assertThat(issueCode).isEqualTo("30015");

    }

    @Description("Check warning notification 30003 for Essay Questions")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getEssayComboBoxForInputColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetMinimumEssayValue(Column column) {
        DataTable table = getCorrectionTable("b910052ec2");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);

        inputBox.clearEssayField();
        inputBox.setEssayFieldValue(RandomDataGenerator.getMinimumRandomNumberValue());
        clickOnAnyPlace();
        correctionsPage.getSaveButton().doubleClick();
        pageIssue.clickOnFoundIssueNotification();
        String issueDescription = dataIssuesContainer.getDataIssueDescriptionText();
        String issueCode = dataIssuesContainer.getDataIssueCode().text();

        assertThat(issueDescription).isEqualTo("Response is less than the minimum length defined.");
        assertThat(issueCode).isEqualTo("30003");

    }

    @Description("Check warning notification 30002 for Essay Questions")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getEssayComboBoxForInputColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetMaximumEssayValue(Column column) {
        DataTable table = getCorrectionTable("13547f1e66");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);
        table.waitUntilRowsLoaded();
        inputBox.clearAndChangeEssayField(RandomDataGenerator.getMaximumRandomNumberValue());
        clickOnAnyPlace();
        correctionsPage.getSaveButton().doubleClick();
        pageIssue.clickOnFoundIssueNotification();
        String issueDescription = dataIssuesContainer.getDataIssueDescriptionText();
        String issueCode = dataIssuesContainer.getDataIssueCode().text();

        assertThat(issueDescription).isEqualTo("Response exceeds the maximum length defined.");
        assertThat(issueCode).isEqualTo("30002");

    }

    @Description("Check warning notification 30014 for Numeric Questions")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getNumericComboBoxForUIValidation",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetOverMaximumValueRange(Column column) {
        DataTable table = getCorrectionTable("b7e384070f");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);
        inputBox.clearInputBox();
        inputBox.changeNumericInputFieldValue(RandomDataGenerator.getMaximumRandomNumberValue());
        clickOnAnyPlace();
        correctionsPage.getSaveButton().doubleClick();
        pageIssue.clickOnFoundIssueNotification();
        String issueDescription = dataIssuesContainer.getDataIssueDescriptionText();
        String issueCode = dataIssuesContainer.getDataIssueCode().text();

        assertThat(issueDescription).isEqualTo("Response is greater than the maximum value defined.");
        assertThat(issueCode).isEqualTo("30014");
    }

    @Description("Check warning notification 30014 for Slider Questions")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getSliderScaleQuestions",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetOverMaximumValueSliderRange(Column column) {
        DataTable table = getCorrectionTable("ecc739b795");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);
        inputBox.clearInputBox();
        inputBox.changeNumericInputFieldValue(RandomDataGenerator.getMaximumRandomNumberValue());
        clickOnAnyPlace();
        correctionsPage.getSaveButton().doubleClick();
        pageIssue.clickOnFoundIssueNotification();
        String issueDescription = dataIssuesContainer.getDataIssueDescriptionText();
        String issueCode = dataIssuesContainer.getDataIssueCode().text();

        assertThat(issueDescription).isEqualTo("Response is greater than the maximum value defined.");
        assertThat(issueCode).isEqualTo("30014");
    }

    @Description("Check warning notification 30013 for Numeric Questions")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getNumericComboBoxForUIValidation",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetUnderMinimumValueRange(Column column) {
        DataTable table = getCorrectionTable("b920e5d8d7");
        waitUntilSurveyFullyLoaded(table);

        InputBox inputBox = table.getRows()
                .first()
                .getInputBoxForColumn(column);
        inputBox.clearInputBox();
        inputBox.changeNumericInputFieldValue(1);
        clickOnAnyPlace();
        correctionsPage.getSaveButton().doubleClick();
        pageIssue.clickOnFoundIssueNotification();
        String issueDescription = dataIssuesContainer.getDataIssueDescriptionText();
        String issueCode = dataIssuesContainer.getDataIssueCode().text();

        assertThat(issueDescription).isEqualTo("Response is less than the minimum value defined.");
        assertThat(issueCode).isEqualTo("30013");
    }

    @Description("Check warning notification 30015 for Checkbox Questions")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyCheckboxColumnsForUIValidation",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetOutOfTheRangeOfMinimumSelection(Column column) {
        DataTable table = getCorrectionTable("1dcf668fbf");
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);

        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        comboBox.open();
        open.clickAllCheckBoxes();
        clickOnAnyPlace();
        correctionsPage.clickOnSaveButton();
        pageIssue.clickOnFoundIssueNotification();

        String issueDescription = dataIssuesContainer.getDataIssueDescriptionText();
        String issueCode = dataIssuesContainer.getDataIssueCode().text();
        dataIssuesContainer.getDoneButton().click();
        assertThat(issueDescription).isEqualTo("Response is out of the set range of maximum selection.");
        assertThat(issueCode).isEqualTo("300015");

    }

    @Description("Check warning notification 30015 for Checkbox Questions")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyCheckboxGridColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetOutOfTheRangeOfMinimumSelectionForGridQuestions(Column column) {
        DataTable table = getCorrectionTable("530aab34fe");
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);

        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        comboBox.open();
        open.clickAllCheckBoxes();
        clickOnAnyPlace();
        correctionsPage.clickOnSaveButton();
        pageIssue.clickOnFoundIssueNotification();

        String issueDescription = dataIssuesContainer.getDataIssueDescriptionText();
        String issueCode = dataIssuesContainer.getDataIssueCode().text();
        assertThat(issueDescription).isEqualTo("Response is out of the set range of maximum selection.");
        assertThat(issueCode).isEqualTo("300015");

    }

    @Description("Check warning notification 30004 for Checkbox Questions")
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getOnlyCheckboxColumns",
            dataProviderClass = QuestionTypeColumns.class)
    public void testSetLessOutOfTheRangeOfMinimumSelection(Column column) {
        DataTable table = getCorrectionTable("0734d49919");
        waitUntilSurveyFullyLoaded(table);

        ComboBox comboBox = table.getRows()
                .first()
                .getComboBoxForColumn(column);

        ListBox open = comboBox.open();
        open.clickClearSelectionButton();
        clickOnAnyPlace();
        correctionsPage.getSaveButton().doubleClick();
        pageIssue.clickOnFoundIssueNotification();

        String issueDescription = dataIssuesContainer.getDataIssueDescriptionText();
        String issueCode = dataIssuesContainer.getDataIssueCode().text();

        assertThat(issueDescription).isEqualTo("Response is out of the set range of minimum selection.");
        assertThat(issueCode).isEqualTo("30004");
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
}
