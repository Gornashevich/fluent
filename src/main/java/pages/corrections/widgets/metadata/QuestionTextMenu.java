package pages.corrections.widgets.metadata;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

@Getter
public class QuestionTextMenu extends BasePage {
    @FindBy(xpath = "//span[contains(text(),'Update')]")
    private FluentWebElement updateButton;
    @FindBy(xpath = ".//mat-row[@role='row']/mat-cell[contains(@class,'cdk-column-questionText')]")
    private FluentWebElement questionText;
    @FindBy(xpath = "//app-textarea-cell")
    private FluentWebElement appTextArea;
    @FindBy(xpath = "//textarea")
    private FluentWebElement textArea;

    @Step("Click on Update button")
    public void clickOnUpdateButton() {
        await().until(updateButton).clickable();
        updateButton.waitAndClick();

    }

    @Step("Change Question text on metadata page")
    public String changeQuestionText(String newQuestion) {
        appTextArea.conditions().clickable();
        appTextArea.click();
        textArea.conditions().present();
        textArea.conditions().clickable();
        textArea.click();
        textArea.clear();
        textArea.keyboard().sendKeys(newQuestion);
        return appTextArea.text();
    }

}
