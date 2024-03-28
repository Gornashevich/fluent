package commonWidgets;

import io.qameta.allure.Step;
import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentInstantiator;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.fluentlenium.core.filter.FilterConstructor.withClass;
import static org.fluentlenium.core.filter.FilterConstructor.withText;

public class ListBox extends FluentWebElement {

    @FindBy(xpath = ".//mat-option[.//mat-radio-button]")
    private FluentList<FluentWebElement> radioButtonOptions;

    @FindBy(xpath = ".//mat-option[.//mat-pseudo-checkbox and not(./span/div[@class='reset'])]")
    private FluentList<FluentWebElement> checkBoxButtonOptions;

    @FindBy(xpath = ".//div[@class='reset']")
    private FluentWebElement clearSelectionButton;

    @FindBy(xpath = "*//mat-pseudo-checkbox[contains(@class,'checked')]")
    private FluentWebElement checkedCheckbox;

    @FindBy(xpath = ".//mat-option[contains(@class,'mat-option') and@aria-selected='false']")
    private FluentList<FluentWebElement> uncheckedCheckbox;


    private final String setOriginalIcon = "//td[contains(@class,%s)]/app-table-cell-wrapper/div/app-corrections-cleaned-info";


    public ListBox(WebElement element, FluentControl control, ComponentInstantiator instantiator) {
        super(element, control, instantiator);
    }


    @Step("Select some other option")
    public FluentList<FluentWebElement> getUnselectedRadioButtonOptions() {
        return radioButtonOptions.find(By.xpath("."), withClass().notContains("selected"));
    }

    public FluentList<FluentWebElement> getUnselectedCheckBoxButtonOptions() {
        return checkBoxButtonOptions.find(By.xpath("."), withClass().notContains("checked"));
    }

    public FluentWebElement getSelectedRadioButtonOption() {
        return radioButtonOptions.el(By.xpath("."), withClass().contains("selected"));
    }

    public void selectRadioButtonByValue(String value) {
        radioButtonOptions.el(withText(value)).click();
    }

    public void selectCheckboxByValue(String value) {
        checkBoxButtonOptions.el(withText(value)).click();
    }

    @Step("Click on clear selection button")
    public void clickClearSelectionButton() {
        await().until(clearSelectionButton).displayed();
        await().atMost(1000).until(clearSelectionButton).clickable();
        clearSelectionButton.waitAndClick();
    }

    public void clickAllCheckBoxes() {
        FluentList<FluentWebElement> elements = find(By.xpath(".//mat-option[.//mat-pseudo-checkbox and not(contains(@class, 'selected')) and not(./span/div[@class='reset'])]"));
        if (elements.size() != 0) {
            for (FluentWebElement element : elements) {
                if (element.enabled() && element.displayed()) {
                    element.click();
                }
            }
        }

    }

    public void clickOnSetToOriginalMenu(String columName) {
        String formatValue = String.format(setOriginalIcon, columName);
        FluentWebElement el = el(By.xpath(formatValue));
        el.click();
    }

    public String getCellColorValue(String columName) {
        String formatValue = String.format("//td[contains(@class,'%s')]/app-table-cell-wrapper/div", columName);
        FluentWebElement el = el(By.xpath(formatValue));
        String cssValue = el.cssValue("background-color");
        return cssValue;
    }
}
