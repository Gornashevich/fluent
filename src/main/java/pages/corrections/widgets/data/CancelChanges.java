package pages.corrections.widgets.data;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

@Getter
public class CancelChanges extends BasePage {
    @FindBy(xpath = "//span[contains(text(),'Yes, proceed')]")
    private FluentWebElement yesProceedButton;
    @FindBy(xpath = "//button[contains(text(),'back')]")
    private FluentWebElement backButton;

    @Step("Click on Yes proceed button")
    public void clickOnYesProceedButton() {
        await().until(yesProceedButton).clickable();
        yesProceedButton.waitAndClick();
    }
    @Step("Click on No proceed button")
    public void clickOnBackButton() {
        await().until(backButton).clickable();
        backButton.waitAndClick();
    }
}
