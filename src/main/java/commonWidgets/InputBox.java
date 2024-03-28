package commonWidgets;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentInstantiator;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.fluentlenium.core.filter.FilterConstructor.withClass;

@Getter
public class InputBox extends FluentWebElement {
    @FindBy(xpath = ".//td[contains(@class,'Q1_R3_OTHER')]//div[@class='text']")
    public FluentWebElement otherSpecifyOptionField;
    @FindBy(xpath = ".//app-table-number-input//input[contains(@data-keyboard-trigger,'true')]")
    public FluentWebElement numericInputFieldValue;
    @FindBy(xpath = "//app-table-number-input//input[contains(@data-keyboard-trigger,'true')]")
    public static List<FluentWebElement> numericInputFieldValueRow;
    @FindBy(xpath = ".//div[contains(@class,'mat-form-field')]/input[contains(@data-keyboard-trigger,'true')]")
    public List<FluentWebElement> numericInputList;
    @FindBy(xpath = ".//app-table-cell-wrapper/div/app-table-text")
    public FluentWebElement essayInputField;
    @FindBy(xpath = ".//div/textarea")
    public FluentWebElement essayInputFieldValue;
    @FindBy(xpath = "//mat-icon[contains(@class,'warning")
    public FluentWebElement warningIcon;
    @FindBy(xpath = "//textarea[contains(@id,'mat-input')]")
    public static FluentWebElement textArea;
    @FindBy(xpath = ".//app-corrections-cleaned-info")
    private FluentList<FluentWebElement> setToOriginalIcon;

    public InputBox(WebElement element, FluentControl control, ComponentInstantiator instantiator) {
        super(element, control, instantiator);
    }

    public static String getNumericInputFieldValue(InputBox inputBox) {
        return inputBox.find(By.xpath(".//app-table-number-input//input[contains(@data-keyboard-trigger,'true')]")).first().attribute("value");
    }

    public static String getEssayInputField(InputBox inputBox) {
        inputBox.await().until().displayed();
        return inputBox.find(By.xpath(".//div/app-table-text/div")).first().text();
    }

    public static String getOtherSpecifyInputField(InputBox inputBox) {
        return inputBox.find(By.xpath(".//app-table-text/div")).first().text();

    }

    @Step("Click and change other specify response")
    public FluentWebElement clickAndChangeOtherSpecify(int newValue) {
        FluentWebElement otherSpecifyOptionField = el(By.xpath("//td[contains(@class,'Q1_R3_OTHER')]//div[@class='text']"));
        otherSpecifyOptionField.await().until().displayed();
        otherSpecifyOptionField.mouse().click();
        FluentWebElement el = el(By.xpath("//div/textarea"));
        el.conditions().present();
        el.waitAndClick().clear();
        el.keyboard().sendKeys(String.valueOf(newValue));
        return otherSpecifyOptionField;
    }

    public static void setRepeatedValueForRankedQuestion(String repeatedValue) {
        List<FluentWebElement> inputAnswers = numericInputFieldValueRow;
        if (inputAnswers.size() > 0) {
            for (int i = 0; i < inputAnswers.size(); i++) {
                inputAnswers.get(i).click().write(repeatedValue);
            }
        }
    }
    @Step("Change essay answer field")
    public FluentWebElement clearAndChangeEssayField(int newValue) {
        essayInputField.conditions().clickable();
        essayInputField.waitAndClick();
        FluentWebElement el = el(By.xpath("//div[@role='menu']/div/div/mat-form-field"));
        await().until(el).clickable();
        el.keyboard().sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        el.keyboard().sendKeys(String.valueOf(newValue));
        return essayInputField;
    }

    @Step("Clear essay answer field")
    public void clearEssayField() {
        essayInputField.conditions().clickable();
        essayInputField.waitAndClick();
        essayInputField.find(By.xpath("//textarea[contains(@id,'mat-input')]")).click().clear();
    }
    @Step("Set new essay answer option")
    public void setEssayFieldValue(int newValue) {
        essayInputField.conditions().clickable();
        essayInputField.el(By.xpath("//textarea[contains(@id,'mat-input')]")).waitAndClick();
        FluentWebElement el = essayInputField.el(By.xpath("//textarea[contains(@id,'mat-input')]"));
        await().until(el).present();
        el.keyboard().sendKeys(String.valueOf(newValue));
    }
    @Step("Change numeric answer field")
    public FluentWebElement changeNumericInputFieldValue(int newValue) {
        numericInputFieldValue.conditions().clickable();
        numericInputFieldValue.waitAndClick();
        numericInputFieldValue.keyboard().sendKeys(String.valueOf(newValue));
        return numericInputFieldValue;
    }
    @Step("Clear inputBox field")
    public FluentWebElement clearInputBox() {
        numericInputFieldValue.clear();
        return this;
    }
    @Step("Click on set to original button")
    public FluentList<FluentWebElement> getSetToOriginalIcon() {
        return setToOriginalIcon.find(By.xpath("."), withClass().contains("cleaned-indicator-tooltip"));
    }
}
