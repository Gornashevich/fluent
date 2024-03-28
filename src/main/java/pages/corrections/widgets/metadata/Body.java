package pages.corrections.widgets.metadata;

import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentInstantiator;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.corrections.widgets.Row;
import pages.corrections.widgets.webtables.TableBody;

import java.util.List;
import java.util.stream.Collectors;

public class Body extends FluentWebElement implements TableBody {
    @FindBy(xpath = "//mat-row[@role='row']")
    private FluentList<Row> rows;

    /**
     * Creates a new fluent web element.
     *
     * @param element      underlying element
     * @param control      control interface
     * @param instantiator component instantiator
     */
    public Body(WebElement element, FluentControl control, ComponentInstantiator instantiator) {
        super(element, control, instantiator);
    }

    @Override
    public Row getRow(int index) {
        return this.getRows().get(index);
    }

    @Override
    public List<Row> getRows() {
        return rows.stream().filter(i ->
                !i.text().trim().contains("mos_Id")
                        && !i.text().trim().contains("mos_panelid")).collect(Collectors.toList());
    }
}
