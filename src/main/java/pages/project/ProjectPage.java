package pages.project;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;


public class ProjectPage {

    @FindBy(xpath = "//span[contains(text(),'Save')]")
    public FluentWebElement saveButton;

    public void clickOnSaveButton() {
        saveButton.click();
    }

}
