package pages;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.core.hook.wait.Wait;

@Wait
public class BasePage extends FluentPage {
    public void clickOnElement(FluentWebElement element) {
        await().until(element).present();
        element.waitAndClick();
    }
}
