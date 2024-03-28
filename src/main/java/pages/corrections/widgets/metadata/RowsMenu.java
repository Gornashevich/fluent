package pages.corrections.widgets.metadata;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;


@Getter
public class RowsMenu extends BasePage {
    private final String rowAnswerOptions = "//app-variation-cell/div/div//ancestor::mat-cell[contains(@data-cellid,'%s_rows')]";
    @FindBy(xpath = "//app-variation-cell/div/div//ancestor::mat-cell[contains(@data-cellid,'ROW_rows')]")
    private FluentList<FluentWebElement> rowsAnswerOptions;
    @FindBy(xpath = "//span[contains(text(),'Add row')]")
    private FluentWebElement addRowButton;
    @FindBy(xpath = "//div[contains(@class, 'row ng-star-inserted')]")
    private FluentList<FluentWebElement> answerRow;
    @FindBy(xpath = ".//mat-form-field[@formgroupname='texts']")
    private FluentList<FluentWebElement> textOptionList;
    @FindBy(xpath = ".//mat-form-field[@formgroupname='texts']//input")
    private FluentList<FluentWebElement> textOptionInputList;
    @FindBy(xpath = "//span[contains(text(),'Update')]")
    private FluentWebElement updateButton;
    @FindBy(xpath = ".//mat-form-field[@formgroupname='texts']")
    private FluentList<FluentWebElement> textChoices;


    @Step("Click on Add Row button")
    public void clickOnAddRowButton() {
        addRowButton.conditions().clickable();
        addRowButton.click();
    }
    @Step("Click on Update button")
    public void clickOnUpdateButton() {
        updateButton.conditions().clickable();
        updateButton.waitAndClick();
    }
    @Step("Full fill virtual answer options")
    public void fullFillVirtualAnswerOption(String newAnswerOptionText) {
        FluentList<FluentWebElement> answerOptions = find(By.xpath(".//mat-form-field[@formgroupname='texts']"));
        await().until(answerOptions).clickable();
        answerOptions.last().click();
        FluentList<FluentWebElement> answerInputFields = find(By.xpath("//input/ancestor::mat-form-field[@formgroupname='texts']"));
        await().until(answerInputFields).present();
        await().until(answerInputFields).clickable();
        answerInputFields.last().keyboard().sendKeys(newAnswerOptionText);
    }
    @Step("Click on Row menu")
    public void clickOnRowMenu(String questionName) {
        String formatValue = String.format(rowAnswerOptions, questionName);
        FluentWebElement el = el(By.xpath(formatValue));
        el.click();
    }
    @Step("Full fill text values responses")
    public void fullFillTextValueResponses(String newQuestionText) {
        FluentList<FluentWebElement> textChoices = find(By.xpath(".//mat-form-field[@formgroupname='texts']//input"));
        if (!textChoices.isEmpty()) {
            for (FluentWebElement textChoice : textChoices) {
                textChoice.click();
                textChoice.write(newQuestionText);
            }
        }
    }

     public String getInputValue() {
        FluentList<FluentWebElement> textOptionInputList = getTextOptionInputList();
        String value = textOptionInputList.last().attribute("value");
        return value;
    }
    @Step("Choose last answer option")
    public void chooseLastAnswerOption() {
        await().until(textChoices).clickable();
        textChoices.last().click();
    }
}