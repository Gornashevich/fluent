package pages.corrections.widgets.data;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.time.Duration;

@Getter
public class SetToOriginalMenu extends BasePage {
    @FindBy(xpath = "//div[@class='cdk-overlay-pane']")
    private FluentWebElement setToOriginalMenu;
    @FindBy(xpath = "//p[@class='name']")
    private FluentWebElement userName;
    @FindBy(xpath = "//p[@class='date']")
    private FluentWebElement dateAnswer;
    @FindBy(xpath = "//p[@class='original-responses']")
    private FluentWebElement originalResponse;
    @FindBy(xpath = "//button/span[contains(text(),'Set to original')]")
    private FluentWebElement setToOriginalButton;

    @Step("Click on set to original button")
    public void clickOnSetToOriginalButton() {
        FluentList<FluentWebElement> el = find(By.xpath("//button/span[contains(text(),'Set to original')]"));
        await().atMost(3000).until(el).clickable();
        if(el.present()){
            el.doubleClick();
        }

    }
    @Step("Get initial respondent response ")
    public String getOriginalRespondentResponse(){
        await().atMost(2000).until(originalResponse).displayed();
        String text = originalResponse.text();
        return text;
    }

    public void waitUntilSetToOriginalMenuDisappears() {
        this.await().atMost(Duration.ofSeconds(2)).until(setToOriginalMenu).not().present();
    }

    public boolean isInitialRespondentDataDisplays() {
//        await().until(dateAnswer).present();
        return userName.displayed() && dateAnswer.displayed() && originalResponse.displayed();
    }
}
