package pages.corrections.widgets.metadata;

import com.google.common.collect.Iterators;
import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
public class MetadataTable extends BasePage {

    private final String rowAnswerOptions = "//app-variation-cell/div/div//ancestor::mat-cell[contains(@data-cellid,'%s_rows')]";
    private final String columnAnswerOptions = "//app-variation-cell/div/div//ancestor::mat-cell[contains(@data-cellid,'%s_columns')]";
    private final String cellAnswerOptions = "//app-variation-cell/div/div//ancestor::mat-cell[contains(@data-cellid,'%s_cells')]";
    private final By cells = By.xpath("//*[@role='cell']");

    @FindBy(xpath = "//span[contains(text(),'Add question')]")
    private FluentWebElement addQuestionButton;
    @FindBy(xpath = "//app-variation-cell/div/div//ancestor::mat-cell[contains(@data-cellid,'ROW_rows')]")
    private RowsMenu rowsAnswerOptions;
    @FindBy(xpath = "*//app-corrections-add-question-dialog")
    private NewVirtualQuestion newVirtualQuestion;
    @FindBy(xpath = ".//div[@class='cdk-overlay-pane']")
    private QuestionTextMenu questionTextMenu;
    @FindBy(xpath = "//mat-table")
    private FluentWebElement body;
    @FindBy(xpath = "//mat-header-row//div[contains(@class, 'cell-')]")
    private FluentList<FluentWebElement> headerCells;
    @FindBy(xpath = "//mat-row[@role='row']/mat-cell[contains(@class,'question-text-cell')]")
    private FluentList<FluentWebElement> questionTextCell;
    @FindBy(xpath = "//mat-row[@role='row']")
    private FluentList<FluentWebElement> rows;
    @FindBy(xpath = ".//mat-row[@role='row']/mat-cell[contains(@class,'cdk-column-questionText')]")
    private FluentList<FluentWebElement> questionTextList;
    @FindBy(xpath = ".//mat-row[@role='row']/mat-cell[contains(@class,'cdk-column-questionName')]")
    private FluentList<FluentWebElement> questionNamesList;
    @FindBy(xpath = "//span[contains(text(),'Update')]")
    private FluentWebElement updateButton;
    @FindBy(xpath = "//div[@class='dialog-content-actions']/button/span[contains(text(),'ADD')")
    private FluentWebElement addButton;
    @FindBy(xpath = ".//button[@class='button-text-with-left-icon green']")
    private FluentWebElement addRowButton;
    @FindBy(xpath = ".//mat-form-field[@formgroupname='texts'])")
    private FluentList<FluentWebElement> textChoices;
    @FindBy(xpath = ".//mat-form-field[@formgroupname='texts']/div/div/div/input")
    private FluentWebElement textInputField;
    @FindBy(xpath = "//div[@role='listbox']/mat-option")
    private FluentList<FluentWebElement> questionTypes;
    @FindBy(xpath = "//div[@class='row']/mat-form-field[1]")
    private FluentWebElement questionTypeDropdown;
    @FindBy(xpath = "//span[@class='mat-option-text']/div[contains(text(),'Numeric Number')]")
    private FluentWebElement numericNumberQuestion;
    @FindBy(xpath = "//span[@class='mat-option-text']/div[contains(text(),'Essay Textbox')]")
    private FluentWebElement essayTextBoxSingleQuestion;
    @FindBy(xpath = "//span[@class='mat-option-text']/div[contains(text(),'Multi-Select Dropdown')]")
    private FluentWebElement multiSelectDropdownQuestion;
    @FindBy(xpath = "//mat-option/span[contains(text(),'Single')]")
    private FluentWebElement singleQuestionVariation;
    @FindBy(xpath = "//mat-option/span[contains(text(),'Row')]")
    private FluentWebElement rowQuestionVariation;
    @FindBy(xpath = "//mat-option/span[contains(text(),'Grid')]")
    private FluentWebElement gridQuestionVariation;
    @FindBy(xpath = "//div[@class='row']/mat-form-field[2]")
    private FluentWebElement variationTypeDropdown;
    @FindBy(xpath = "//div[@class='row']/mat-form-field[3]")
    private FluentWebElement questionNameField;
    @FindBy(xpath = "//input[@formcontrolname='questionName']")
    private FluentWebElement questionNameInputField;
    @FindBy(xpath = "//textarea[@formcontrolname='questionText']")
    private FluentWebElement questionTextInputField;
    @FindBy(xpath = "//div[@class='row row-question-text']/mat-form-field")
    private FluentWebElement questionText;
    @FindBy(xpath = "//div[@formgroupname='choices']/app-corrections-tabs/div[text()=('Rows')]")
    private FluentWebElement rowsResponseTab;
    @FindBy(xpath = "//div[@formgroupname='choices']/app-corrections-tabs/div[text()=('Columns')]")
    private FluentWebElement columnsResponseTab;
    @FindBy(xpath = "//div[@formgroupname='choices']/app-corrections-tabs/div[text()=('Cells')]")
    private FluentWebElement cellsResponseTab;


    public MetadataTable waitUntilMetaDataRowsLoaded() {
        await()
                .atMost(Duration.ofSeconds(15))
                .until(rows)
                .size()
                .greaterThan(0);
        return this;
    }

    @Step("Click on Question text menu")
    public void clickOnQuestionTextMenu(int rowIndex) {
        FluentWebElement cell = getCell("Question Text", rowIndex, true);
        FluentWebElement qNameCell = getCell("Question Text", rowIndex, true);
        cell.conditions().clickable();
        cell.click();
    }

    public void clickAndAddRowChoice(String newQuestion) {
        textChoices.last().click();
        textInputField.conditions().present();
        textChoices.write(newQuestion);
    }

    //Without headers
    public List<FluentWebElement> getRows() {
        FluentList<FluentWebElement> elements = find(By.xpath("//mat-row[@role='row']"));
        return elements.stream().filter(i ->
                !i.text().trim().contains("mos_Id")
                        && !i.text().trim().contains("mos_panelid")).collect(Collectors.toList());
    }

    public List<FluentWebElement> getQuestionTypeList() {
        return questionTypes.stream().filter(i ->
                !i.text().trim().contains("Video Player Video Emotion Tracker")
                        && !i.text().trim().contains("Static Text")).collect(Collectors.toList());
    }

    public void selectRandomQuestionType() {
        FluentList<FluentWebElement> questionType = getQuestionTypes();
        Random r = new Random();
        for (int i = 0; i < 1; i++) {
            if (!questionType.isEmpty()) {
                int randomElement = r.nextInt(questionType.size());
                await().atMost(5000);
                questionType.get(randomElement + i).click();

            }
        }
    }

    public FluentWebElement getRow(int index) {
        return this.getRows().get(index);
    }

    public int getColumnIndexByName(String text, boolean equals) {

        List<String> columns = getHeaderCells().stream()
                .map(e -> e.text().trim())
                .collect(Collectors.toList());

        return equals
                ? columns.indexOf(text) + 1
                : Iterators.indexOf(columns.iterator(), s -> s.contains(text)) + 1;
    }

    /**
     * @return The whole cell in a row
     */
    public List<FluentWebElement> getCells(int rowIndex) {
        return getRow(rowIndex).find(cells);
    }

    /**
     * Cell by index
     */
    public FluentWebElement getCell(String column, int rowIndex, boolean equals) {
        var index = this.getColumnIndexByName(column, equals);

        return getCells(rowIndex).get(index);
    }

    public List<FluentWebElement> getHeaderCells() {
        return find(By.xpath("//mat-header-row//div[contains(@class, 'cell-')]"));
    }

    public List<String> getQuestionTextCells() {
        FluentList<FluentWebElement> elements = find(By.xpath("//mat-row[@role='row']/mat-cell[contains(@class,'question-text-cell')]"));

        List<String> columns = elements.stream()
                .map(e -> e.text().trim())
                .filter(e -> !e.contains("MOS ID") && !e.contains("Paneld ID"))
                .collect(Collectors.toList());

        return columns;
    }


    public boolean isConfiguredVirtualQuestionDataDisplays(String newQuestionText, List<FluentWebElement> inputAnswers) {
        boolean contains = false;
        for (FluentWebElement inputAnswer : inputAnswers) {
            if (inputAnswer.text().contains(newQuestionText)) {
                contains = true;
                break;
            }

        }
        return contains;
    }


    public void fullFillTextValueResponses(String newQuestionText) {
        FluentList<FluentWebElement> textChoices = find(By.xpath(".//mat-form-field[@formgroupname='texts']//input"));
        if (!textChoices.isEmpty()) {
            for (FluentWebElement textChoice : textChoices) {
                textChoice.click();
                textChoice.write(newQuestionText);
            }
        }
    }

    public void fullFillQuestionResponseData(String newQuestionText) {
        rowsResponseTab.conditions().present();
        rows.click();
        fullFillTextValueResponses(newQuestionText);
        columnsResponseTab.conditions().present();
        columnsResponseTab.click();
        fullFillTextValueResponses(newQuestionText);
        cellsResponseTab.conditions().present();
        cellsResponseTab.click();
        fullFillTextValueResponses(newQuestionText);
    }

    public void clickOnRowMenu(String questionName) {
        String formatValue = String.format(rowAnswerOptions, questionName);
        FluentWebElement el = el(By.xpath(formatValue));
        el.click();
    }

    public void clickOnColumnMenu(String questionName) {
        String formatValue = String.format(columnAnswerOptions, questionName);
        FluentWebElement el = el(By.xpath(formatValue));
        el.click();
    }


}