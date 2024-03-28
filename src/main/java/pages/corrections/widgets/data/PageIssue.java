package pages.corrections.widgets.data;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

@Getter
public class PageIssue extends BasePage {
    @FindBy(xpath = "//span[contains(@class,'warning-text')]")
    private FluentWebElement foundIssueNotification;

    @Step("Click on validation UI notification")
    public void clickOnFoundIssueNotification() {
        try {
            await().atMost(5000).until(foundIssueNotification).displayed();
            await().until(foundIssueNotification).clickable();
            foundIssueNotification.waitAndClick();
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
        }
    }

}
