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
public class LayoutsSideBar extends BasePage {
    @FindBy(xpath = "//app-corrections-data-layouts[contains(@class,'filter-by-question')]")
    LayoutsSideBar layoutsSideBarMenu;

    @FindBy(xpath = "//button[contains(@class,'dropdown-element')]")
    private FluentList<FluentWebElement> layoutsList;

    @FindBy(xpath = "//ul[@class='sidebar-wrapper']/li")
    private FluentList<FluentWebElement> sideBarList;

    @FindBy(xpath = "//div[@class='cdk-drop-list questions-items']/app-questions-item")
    private FluentList<FluentWebElement> layoutsQuestionList;

    @FindBy(xpath = "//span[contains(text(),'Apply')]")
    private FluentWebElement applyButton;

    @FindBy(xpath = "//button[contains(text(),'Reset')]")
    private FluentWebElement resetButton;

    @FindBy(xpath = "//div[@class='dialog-container']")
    private FluentWebElement deleteLayoutPopUp;

    @FindBy(xpath = "//span[contains(text(),'Delete')]")
    private FluentWebElement deleteButtonFromExpandedMenu;

    @FindBy(xpath = "//button[contains(@class,'dialog-save-button')]")
    private FluentWebElement deleteLayoutButton;

    @FindBy(xpath = "//span[@class='button-title']/..")
    private FluentWebElement generalLayoutDropDownMenu;

    @FindBy(xpath = "//div[@class='footer']//mat-icon[contains(text(),'expand_more')]/..")
    private FluentWebElement expandMoreButton;


    public boolean isCreatedLayoutPresent(String newQuestionText, List<FluentWebElement> inputAnswers) {
        boolean contains = false;
        for (FluentWebElement inputAnswer : inputAnswers) {
            if (inputAnswer.text().contains(newQuestionText)) {
                contains = true;
                break;
            }

        }
        return contains;
    }

    @Step("Switch on created User layout")
    public void switchToCreatedUserLayout(String userLayout) {
        String formatValue = String.format("//button[contains(text(),'%s') and contains(@class,'dropdown-element')]", userLayout);
        FluentWebElement el = el(By.xpath(formatValue));
        el.doubleClick();
    }

    @Step("Apply User layout")
    public void clickOnCreatedUserLayout() {
        FluentList<FluentWebElement> list = getLayoutsList();
        if (list.size() > 3) {
            list.last().waitAndClick();
        }
    }

    @Step("Click on Apply button")
    public void clickOnApplyButton() {
        applyButton.await().until().clickable();
        applyButton.click();
    }

    @Step("Click on Reset button")
    public void clickOnResetButton() {
        resetButton.await().until().clickable();
        resetButton.click();
    }

    @Step("Delete User layout")
    public void deleteUserLayout() {
        deleteLayoutButton.await().until().clickable();
        deleteLayoutButton.click();
        deleteLayoutPopUp.await().until().displayed();
        deleteLayoutButton.waitAndClick();
    }

    @Step("Click on Expand button")
    public void clickOnExpandButton() {
        if (expandMoreButton.displayed()) {
            expandMoreButton.waitAndClick();
        }

    }

    @Step("Click on Delete layout button")
    public void clickOnDeleteLayoutButton() {
        deleteButtonFromExpandedMenu.await().until().clickable();
        deleteButtonFromExpandedMenu.click();
    }

    @Step("Click on Delete button")
    public void clickOnDeleteButton() {
        switchTo(deleteLayoutPopUp);
        deleteLayoutButton.waitAndClick();
    }
}

