package pages.corrections.widgets.data.sidebar;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.time.Duration;

@Getter
public class SettingsSideBar extends BasePage {
    @FindBy(xpath = "//div[contains(@class,'bar-element settings')]")
    FluentWebElement settingsSideBar;
    @FindBy(xpath = "//label[contains(text(),'Numeric')]")
    private FluentWebElement numericValueButton;
    @FindBy(xpath = "//label[contains(text(),'Text')]")
    private FluentWebElement textValueButton;
    @FindBy(xpath = "//span[contains(text(),'Apply')]")
    private FluentWebElement applyButton;
    @FindBy(xpath = "//div[h4[contains(text(),'Show original data')]]//mat-slide-toggle//button[@role='switch']")
    private FluentWebElement showOriginalDataSwitcherButtonON;
    @FindBy(xpath = "//button[@role='switch' and @aria-checked='true']")
    private FluentWebElement showOriginalDataSwitcherButtonOff;
    @FindBy(xpath = "//label[text()='Unsaved']")
    private FluentWebElement highlightUnsavedChangesButton;
    @FindBy(xpath = "//label[text()='All']/preceding-sibling::div['radio']")
    private FluentWebElement highlightAllChangesButton;

    @Step("Click on apply button")
    public void clickOnApplyButton() {
        switchTo(applyButton);
        await().until(applyButton).enabled();
        await().until(applyButton).clickable();
        applyButton.mouse().click();
    }

    @Step("Click on switch numeric value radio button")
    public void switchToNumericValueButton() {
        switchTo(settingsSideBar);
        await().until(numericValueButton).clickable();
        numericValueButton.click();
    }

    @Step("Click on switch text value radio button")
    public void switchToTextValueButton() {
        switchTo(settingsSideBar);
        await().until(textValueButton).clickable();
        textValueButton.click();
    }

    @Step("Turn on original data value button")

    public void turnOnToOriginalData() {
        switchTo(settingsSideBar);
        await().until(showOriginalDataSwitcherButtonON).clickable();
        showOriginalDataSwitcherButtonON.click();
    }

    @Step("Turn off original data value button")
    public void turnOffOriginalData() {
        switchTo(settingsSideBar);
        await().until(showOriginalDataSwitcherButtonOff).clickable();
        showOriginalDataSwitcherButtonOff.click();
    }

    @Step("Click on highlight unsaved values button")
    public void clickOnHighlightUnsavedChangesButton() {
        switchTo(settingsSideBar);
        await().until(highlightUnsavedChangesButton).clickable();
        highlightUnsavedChangesButton.waitAndClick();
    }

    @Step("Click on highlight all changes values button")
    public void clickOnHighlightAllChangesButton() {
        switchTo(settingsSideBar);
        await().until(highlightAllChangesButton).present();
        highlightAllChangesButton.waitAndClick();
    }

}
