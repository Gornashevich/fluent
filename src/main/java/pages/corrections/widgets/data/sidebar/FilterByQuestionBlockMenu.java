package pages.corrections.widgets.data.sidebar;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FilterByQuestionBlockMenu extends BasePage {
    @FindBy(xpath = "//app-corrections-filter-by-question")
    private FilterByQuestionBlockMenu filterByQuestionBlockMenu;

    @FindBy(xpath = "//div[@role='listbox']/mat-option")
    private List<FluentWebElement> surveysQuestionList;

    @FindBy(xpath = "//app-condition-input[@inputlabel='Question']")
    private FluentWebElement surveysQuestionOption;

    @FindBy(xpath = "//app-condition-input[@inputlabel='Answers']")
    private FluentWebElement answerFilterOption;

    @FindBy(xpath = "//app-condition-input[@inputlabel='Methods']")
    private FluentWebElement methodFilterOption;

    @FindBy(xpath = "//div[@role='listbox']/mat-option")
    private List<FluentWebElement> methodsList;

    @FindBy(xpath = "//div[@role='listbox']/mat-option")
    private List<FluentWebElement> answersList;

    @FindBy(xpath = "//mat-label[contains(text(),'Text')]")
    private FluentWebElement textInputField;

    @FindBy(xpath = "//div[@class='fields']/mat-form-field")
    private FluentWebElement valueInputField;

    @FindBy(xpath = "//button[contains(@class,'large-button')]/span[contains(text(),'Apply')]")
    private FluentWebElement applyButton;

    public List<FluentWebElement> chooseSurveyQuestionWithoutMosTabs() {
        List<FluentWebElement> currentQuestionsList = this.surveysQuestionList;
        return currentQuestionsList.stream().filter(i ->
                !i.text().trim().contains("mos_Id")
                        && !i.text().trim().contains("mos_panelid")).collect(Collectors.toList());
    }

    public void chooseSurveyQuestion(List<FluentWebElement> surveysQuestionList) {
        List<FluentWebElement> currentQuestionsList = surveysQuestionList;
        if (!currentQuestionsList.isEmpty()) {
            currentQuestionsList.stream().findFirst().get().click();
        }

    }

    public void chooseAnswer() {
        List<FluentWebElement> currentAnswer = answersList;
        if (!currentAnswer.isEmpty()) {
            currentAnswer.stream().findFirst().get().click();
        }

    }

    public void chooseMethod() {
        List<FluentWebElement> methodOption = methodsList;
        if (!methodOption.isEmpty()) {
            methodOption.stream().findFirst().get().click();
        }
    }
    @Step("Click on Apply button")
    public void clickOnApplyButton() {
        switchTo(applyButton);
        await().until(applyButton).clickable();
        applyButton.click();
    }
    @Step("Full fill question menu with answers")
    public void fullFillQuestionMenuWithAnswerOptions(List<FluentWebElement> list) {
        surveysQuestionOption.click();
        chooseSurveyQuestion(list);
        methodFilterOption.click();
        chooseFirstValueOption(this.methodsList);
        answerFilterOption.click();
        chooseFirstValueOption(this.answersList);
        keyboard().sendKeys(Keys.ESCAPE);

    }
    @Step("Full fill question menu value inputs")
    public void fullFillQuestionMenuWithValueInput(List<FluentWebElement> list, String initialText) {
        surveysQuestionOption.click();
        chooseSurveyQuestion(list);
        methodFilterOption.click();
        chooseFirstValueOption(this.methodsList);
        valueInputField.waitAndClick();
        valueInputField.keyboard().sendKeys(initialText);
    }

    @Step("Choose first value option")
    public void chooseFirstValueOption(List<FluentWebElement> list) {
        List<FluentWebElement> temp = list;
        if (!temp.isEmpty()) {
            temp.stream().findFirst().get().click();
        }
    }
}
