package commonWidgets;

import io.qameta.allure.Step;
import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentInstantiator;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

import static org.fluentlenium.core.filter.FilterConstructor.withClass;

public class ComboBox extends FluentWebElement {

    @FindBy(xpath = "//div[@role='listbox']")
    private ListBox listBox;

    @FindBy(xpath = ".//app-corrections-cleaned-info")
    private FluentList<FluentWebElement> setToOriginalIcon;


    public ComboBox(WebElement element, FluentControl control, ComponentInstantiator instantiator) {
        super(element, control, instantiator);
    }

    @Step("Click on the dropdown for a selected question")
    public ListBox open() {
        this.await().atMost(Duration.ofSeconds(15)).until().clickable();
        this.click();
        return listBox;
    }

    public ComboBox selectNewOption() {
        open().getUnselectedRadioButtonOptions().first().click();
        return this;
    }

    public ComboBox selectRadioOptionByValue(String value) {
        open().selectRadioButtonByValue(value());
        return this;
    }

    public ComboBox selectCheckBoxOptionByValue(String value) {
        open().selectCheckboxByValue(value());
        return this;
    }

    public static String getComboBoxValue(ComboBox comboBox) {
        return comboBox.text();
    }

    public static String get(ComboBox comboBox) {
        return comboBox.getElement().getCssValue("color");
    }

    public boolean isConfiguredVirtualDataDisplays(String virtualRowOption, List<FluentWebElement> optionAnswers) {
        boolean contains = false;
        for (FluentWebElement inputAnswer : optionAnswers) {
            if (inputAnswer.text().contains(virtualRowOption)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public FluentList<FluentWebElement> getSetToOriginalIcon() {
        return setToOriginalIcon.find(By.xpath("."), withClass().contains("cleaned-indicator-tooltip"));
    }

    public boolean isSetToOriginalIconDisplays() {
        return !setToOriginalIcon.find(By.xpath("."), withClass().contains("cleaned-indicator-tooltip")).isEmpty();
    }

    public void restoreCleanedCheckboxAnswer(ComboBox comboBox) {
        await().until(comboBox).present();
        await().until(comboBox).clickable();
        comboBox.open().getUnselectedCheckBoxButtonOptions().first().click();
    }
}
