package pages.corrections.widgets.metadata;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

@Getter
public class ColumnsMenu extends BasePage {
    private final String columnAnswerOptions = "//app-variation-cell/div/div//ancestor::mat-cell[contains(@data-cellid,'%s_columns')]";
    @FindBy(xpath = "//span[contains(text(),'Add row')]")
    private FluentWebElement addRowButton;

    @Step("Click on Column menu")
    public void clickOnColumnMenu(String questionName) {
        String formatValue = String.format(columnAnswerOptions, questionName);
        FluentWebElement el = el(By.xpath(formatValue));
        el.click();
    }

    @Step("Click on Add row button")
    public void clickOnAddRowButton() {
        addRowButton.conditions().clickable();
        addRowButton.click();
    }

    @Step("Full fill value responses")
    public void fullFillTextValueResponses(String newQuestionText) {
        FluentList<FluentWebElement> textChoices = find(By.xpath(".//mat-form-field[@formgroupname='texts']//input"));
        if (!textChoices.isEmpty()) {
            for (FluentWebElement textChoice : textChoices) {
                textChoice.click();
                textChoice.write(newQuestionText);
            }
        }
    }

}
