package pages.corrections.widgets;

import commonWidgets.ComboBox;
import commonWidgets.InputBox;
import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentInstantiator;
import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.core.filter.FilterConstructor;
import org.openqa.selenium.WebElement;
import pages.project.Column;

public class Row extends FluentWebElement {

    /**
     * Creates a new fluent web element.
     *
     * @param element      underlying element
     * @param control      control interface
     * @param instantiator component instantiator
     */
    public Row(WebElement element, FluentControl control, ComponentInstantiator instantiator) {
        super(element, control, instantiator);
    }

    public FluentWebElement cellForColumn(String columnName) {
        return el("td", FilterConstructor.withClass().contains(columnName));
    }

    public String colorForColumn(String columnName) {
        return el("td", FilterConstructor.withClass().contains(columnName)).getElement().getCssValue("background-color");
    }

    public ComboBox getComboBoxForColumn(Column columnName) {
        return cellForColumn(columnName.getValue()).as(ComboBox.class);
    }

    public InputBox getInputBoxForColumn(Column columnName) {
        return cellForColumn(columnName.getValue()).as(InputBox.class);
    }
}
