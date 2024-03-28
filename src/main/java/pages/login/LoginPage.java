package pages.login;


import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.time.Duration;


@PageUrl("/")
@Getter
public class LoginPage extends BasePage {
    @FindBy(id = "Input_Email")
    private FluentWebElement inputEmailField;
    @FindBy(id = "Input_Password")
    private FluentWebElement inputPasswordField;
    @FindBy(xpath = "//*[text()='LOG IN']")
    private FluentWebElement logInButton;
    @FindBy(xpath = "//div[@class='background-image-container-item']")
    private FluentWebElement logoImage;

    @Step("Login with valid credentials")
    public void logIn(String email, String password) {
        this.await().atMost(Duration.ofSeconds(60)).until(logoImage).present();
        await().until(logoImage).displayed();
        this.await().atMost(Duration.ofSeconds(5)).until(inputEmailField).clickable();
        inputEmailField.write(email);
        inputPasswordField.write(password);
        logInButton.click();
    }
}
