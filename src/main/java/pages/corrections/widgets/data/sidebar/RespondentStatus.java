package pages.corrections.widgets.data.sidebar;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

@Getter
public class RespondentStatus extends BasePage {
    private final String respondentStatuses = "//label[contains(text(),'%s')]";

    @FindBy(xpath = "//section[@formgroupname='status']/p")
    private FluentList<FluentWebElement> surveyStatusesList;

    @FindBy(xpath = "//span[contains(text(),'Complete')]")
    private FluentWebElement completeStatus;

    @FindBy(xpath = "//span[contains(text(),'Terminated')]")
    private FluentWebElement terminatedStatus;

    @FindBy(xpath = "//span[contains(text(),'Over Quota')]")
    private FluentWebElement overQuotaStatus;

    @FindBy(xpath = "//span[contains(text(),'Quality Rejected')]")
    private FluentWebElement qualityRejectedStatus;

    @FindBy(xpath = "//div/button[contains(text(),'Reset')]")
    private FluentWebElement resetButton;



    public void chooseRespondentStatus(String status) {
        String format = String.format("//span[contains(text(),'s'", status);
        List<FluentWebElement> statuses = find(By.xpath("//section/p/mat-checkbox/label/span[@class='mat-checkbox-label']/span"));
        for (FluentWebElement element : statuses) {
            if (element.text().contains(format)) {
                surveyStatusesList.click();
            }
        }
    }
    @Step("Click on appropriate status")
    public void clickOnAppropriateStatus(String status) {
        String formatValue = String.format(respondentStatuses, status);
        FluentWebElement el = el(By.xpath(formatValue));
        el.click();
    }
}
