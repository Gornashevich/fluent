package pages.corrections.widgets.metadata;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

@Getter
public class NewVirtualQuestion extends BasePage {
    @FindBy(xpath = "//div[@class='row']/mat-form-field[1]")
    private FluentWebElement questionTypeDropdown;
    @FindBy(xpath = "//div[@role='listbox']/mat-option")
    private FluentList<FluentWebElement> questionTypes;
    @FindBy(xpath = "//div[@class='row']/mat-form-field[2]")
    private FluentWebElement variationTypeDropdown;
    @FindBy(xpath = "//div[@role='listbox']/mat-option")
    private FluentList<FluentWebElement> variationTypes;
    @FindBy(xpath = "//div[@class='row']/mat-form-field[3]")
    private FluentWebElement questionNameField;
    @FindBy(xpath = "//div[@class='row row-question-text']/mat-form-field")
    private FluentWebElement questionText;
    @FindBy(xpath = "//div[@class='dialog-content-actions']/button/span[contains(text(),'CANCEL')]")
    private FluentWebElement cancelButton;
    @FindBy(xpath = "//div[@class='dialog-content-actions']/button/span[contains(text(),'ADD')]")
    private FluentWebElement addButton;
    @FindBy(xpath = "//div[@formgroupname='choices']/app-corrections-tabs/div[text()=('Rows')]")
    private FluentWebElement rowsResponseTab;
    @FindBy(xpath = "//div[@formgroupname='choices']/app-corrections-tabs/div[text()=('Columns')]")
    private FluentWebElement columnsResponseTab;
    @FindBy(xpath = "//div[@formgroupname='choices']/app-corrections-tabs/div[text()=('Cells')]")
    private FluentWebElement cellsResponseTab;
    @FindBy(xpath = "//div[contains(text(),'Numeric Number')]")
    private FluentWebElement numericNumberQuestion;
    @FindBy(xpath = "//div[contains(text(),'Multi-Select Dropdown')]")
    private FluentWebElement multiSelectDropdownQuestion;
    @FindBy(xpath = "//mat-option/span[contains(text(),'Grid')]")
    private FluentWebElement gridQuestionVariation;
    @FindBy(xpath = "//mat-option/span[contains(text(),'Single')]")
    private FluentWebElement singleQuestionVariation;
    @FindBy(xpath = "//input[@formcontrolname='questionName']")
    private FluentWebElement questionNameInputField;
    @FindBy(xpath = "//textarea[@formcontrolname='questionText']")
    private FluentWebElement questionTextInputField;
    @FindBy(xpath = "//div[contains(text(),'Essay Textbox')]")
    private FluentWebElement essayTextBoxSingleQuestion;

    @Step("Click on Question type dropdown")
    public void clickOnQuestionTypeDropdown() {
        questionTypeDropdown.waitAndClick();
    }

    public void clickOnQuestionTypesDropdown() {
        questionTypes.waitAndClick();
    }
    @Step("Click on Question name field")
    public void clickOnQuestionNameField() {
        await().until(questionNameField).clickable();
        questionNameField.waitAndClick();
    }

    public void clickOnQuestionTextField() {
        await().until(questionText).clickable();
        questionText.waitAndClick();
    }

    public void clickOnCancelButton() {
        cancelButton.waitAndClick();
    }
    @Step("Click on Add button")
    public void clickOnAddButton() {
        addButton.waitAndClick();
    }
    @Step("Add single punch virtual question")
    public void clickAndAddSinglePunchVirtualQuestion(String newQuestion) {
        clickOnQuestionTypeDropdown();
        numericNumberQuestion.click();
        variationTypeDropdown.click();
        singleQuestionVariation.click();
        questionNameField.click();
        clickOnQuestionNameField();
        questionNameInputField.keyboard().sendKeys("Test" + newQuestion);
        questionText.click();
        questionTextInputField.keyboard().sendKeys(newQuestion);
    }
    @Step("Add single multi punch virtual question")
    public void clickAndAddMultiPunchPunchVirtualQuestion(String newQuestion) {
        clickOnQuestionTypeDropdown();
        multiSelectDropdownQuestion.click();
        variationTypeDropdown.click();
        gridQuestionVariation.click();
        questionNameField.click();
        clickOnQuestionNameField();
        questionNameInputField.keyboard().sendKeys(newQuestion);
        questionText.click();
        questionTextInputField.keyboard().sendKeys(newQuestion);
    }
    @Step("Full fill value responses")
    public void fullFillTextValueResponses(String newQuestionText) {
        FluentList<FluentWebElement> textChoices = find(By.xpath("//mat-form-field[@formgroupname='texts']"));
        if (!textChoices.isEmpty()) {
            for (FluentWebElement textChoice : textChoices) {
                textChoice.keyboard().sendKeys(newQuestionText);
            }
        }
    }
    @Step("Add single essay virtual  question")
    public void clickAndAddEssaySingleVirtualQuestion(String newQuestion) {
        clickOnQuestionTypeDropdown();;
        essayTextBoxSingleQuestion.click();
        variationTypeDropdown.click();
        singleQuestionVariation.click();
        questionNameField.click();
        clickOnQuestionNameField();
        questionNameInputField.keyboard().sendKeys("Test" + newQuestion);
        questionText.click();
        questionTextInputField.keyboard().sendKeys(newQuestion);
    }
    @Step("Full fill question response data")
    public void fullFillQuestionResponseData(String newQuestionText) {
        FluentWebElement rows = el(By.xpath("//span[@class='mdc-tab__text-label' and text()='Rows']"));
        rows.click();
        fullFillTextValueResponses(newQuestionText);
        FluentWebElement columns = el(By.xpath("//span[@class='mdc-tab__text-label' and text()='Columns']"));
        columns.click();
        fullFillTextValueResponses(newQuestionText);
        FluentWebElement cells = el(By.xpath("//span[@class='mdc-tab__text-label' and text()='Cells']"));
        cells.click();
        fullFillTextValueResponses(newQuestionText);
    }
}
