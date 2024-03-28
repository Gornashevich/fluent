package pages.corrections.widgets.data;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

@Getter
public class ImportMenu extends BasePage {
    @FindBy(xpath = "//div[@class='cdk-overlay-pane corrections-import-dialog']")
    private FluentWebElement importMenu;
    @FindBy(xpath = "//input[@type='radio' and @value='numeric']")
    private FluentWebElement numericValueRadioButton;
    @FindBy(xpath = "//input[@type='radio' and @value='text']")
    private FluentWebElement textValueRadioButton;
    @FindBy(xpath = "//div[contains(@class,'import-input')]/input")
    private FluentWebElement dropFileZone;
    @FindBy(xpath = "//button/span[contains(text(),'Import')]")
    private FluentWebElement importButton;
    @FindBy(xpath = "//span[contains(text(),'Done')]")
    private FluentWebElement doneButton;
    @FindBy(id = "//div[@class='loader']")
    private FluentWebElement spinner;
    @FindBy(xpath = "//div[contains(text(),'Rows with data issues*:')]")
    private FluentWebElement rowsWithDataIssueNotificationMessage;
    @FindBy(xpath = "//div[contains(text(),'Metadata issues*:')]")
    private FluentWebElement metadataIssueNotificationMessage;
    @FindBy(xpath = "//span[contains(text(),'Download data issues')]")
    private FluentWebElement downloadDataIssueButton;
    @FindBy(xpath = "//span[contains(text(),'Download metadata issues')]")
    private FluentWebElement downloadMetadataIssueButton;

    @Step("Import Survey file")
    public void importSurveyFile(String surveyFile) {
        WebElement element = getDriver().findElement(By.xpath("//div[contains(@class,'import-input')]/input"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.height='auto'; arguments[0].style.visibility='visible';", element);
        element.sendKeys(surveyFile);
    }
    @Step("Click on Import button")
    public void clickImportButton() {
        await().atMost(5).explicitlyFor(5).until(importButton).present();
        importButton.waitAndClick();
        await().atMost(5).explicitlyFor(10).until(spinner).not().present();
    }
    @Step("Click on Done button")
    public void clickOnDoneButton() {
        await().until(spinner).not().present();
        doneButton.await().until().clickable();
        doneButton.waitAndClick();
    }

    public boolean isDataIssueNotificationMessageDisplay() {
        return rowsWithDataIssueNotificationMessage.displayed();
    }

    public boolean isMetadataIssueNotificationMessageDisplay() {
        return metadataIssueNotificationMessage.displayed();
    }

    public boolean isDownloadDataIssueButtonDisplay() {
        return downloadDataIssueButton.displayed();
    }

    public boolean isDownloadMetadataIssueButtonDisplay() {
        return downloadMetadataIssueButton.displayed();
    }
}
