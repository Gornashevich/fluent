package pages.corrections.widgets.data;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

@Getter
public class DataIssuesContainer extends BasePage {
    @FindBy(xpath = "//div[@class='dialog-container']")
    public FluentWebElement dataIssuesFrame;
    @FindBy(xpath = "//mat-row/mat-cell[contains(@class,'issueCode')]")
    private FluentWebElement dataIssueCode;
    @FindBy(xpath = "//mat-row/mat-cell[contains(@class,'cdk-column-issue')]")
    public FluentWebElement dataIssueDescription;
    @FindBy(xpath = "//span[contains(text(),'Done')]")
    public FluentWebElement doneButton;

    @Step("Get data issue description")
    public String getDataIssueDescriptionText() {
        await().atMost(3000).until(dataIssueDescription).displayed();
        String text = dataIssueDescription.text();
        return text;
    }
}