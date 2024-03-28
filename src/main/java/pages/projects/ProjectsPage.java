package pages.projects;

import lombok.Getter;
import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentInstantiator;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProjectsPage extends FluentWebElement {
    @Getter
    @FindBy(id = "mat-input-0")
    private FluentWebElement inputSearchField;

    @FindBy(xpath = "//*[@id='mat-select-value-19']/span/span")
    private FluentWebElement marketOnceProjectMenu;

    @FindBy(xpath = "//*[@id='mat-option-37']/span")
    private FluentWebElement surveyKickProjectMenu;

    /**
     * Creates a new fluent web element.
     *
     * @param element      underlying element
     * @param control      control interface
     * @param instantiator component instantiator
     */
    public ProjectsPage(WebElement element, FluentControl control, ComponentInstantiator instantiator) {
        super(element, control, instantiator);
    }

}
