package pages.corrections.widgets.data.sidebar;

import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;


@Getter
public class QuestionSideBar extends BasePage {
    @FindBy(xpath = "//app-corrections-data-questions[contains(@class,'filter-by-question')]")
    private QuestionSideBar questionSideBarMenu;

    @FindBy(xpath = "//button[@class='button-icon visibility-indicator']")
    private FluentList<FluentWebElement> hideIndicatorsList;

    private final By questionList = By.xpath("//button[@class='button-icon visibility-indicator']");

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    private FluentWebElement saveLayoutMenu;

    @FindBy(xpath = "//button[contains(@class,'standard button')]")
    private FluentWebElement saveLayoutButton;

    @FindBy(xpath = "//div[@class='dialog-container']")
    private FluentWebElement saveLayoutPopUp;

    @FindBy(xpath = "//div[@class='dialog-content']//input[@type='text']")
    private FluentWebElement layoutNameField;

    @FindBy(xpath = "//input[@formcontrolname='description']")
    private FluentWebElement layoutDescriptionField;

    @Step("Hide Question sidebars' questions")
    public void hideRandomQuestions() {
        FluentList<FluentWebElement> questionType = getHideIndicatorsList();
        //  Random r = new Random();
        for (int i = 0; i <= 2; i++) {
            if (!questionType.isEmpty()) {
                //    int randomElement = r.nextInt(questionType.size() / 2);
                await().atMost(2000);
                //  questionType.get(randomElement + i).scrollIntoView();
                questionType.get(i).click();

            }
        }
    }
    @Step("Drag and drop random questions")
    public void dragAndDropRandomQuestions() {
        Actions action = new Actions(getDriver());
        List<WebElement> questionList = getDriver().findElements(getQuestionList());
        action.clickAndHold(questionList.get(1)).perform();
        action.dragAndDrop(questionList.get(0), questionList.get(questionList.size() / 2)).perform();

    }
    @Step("Fill layout pop up")
    public void fillLayoutPopUp(String layoutName) {
        await().until(saveLayoutPopUp).present();
        await().until(layoutNameField).clickable();
        layoutNameField.write(layoutName);
        saveLayoutButton.await().until().clickable();
        saveLayoutButton.click();
    }


}
