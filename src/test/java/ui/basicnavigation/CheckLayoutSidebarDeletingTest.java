package ui.basicnavigation;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.fluentlenium.configuration.ConfigurationProperties;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.corrections.widgets.data.DataTable;
import ui.BaseUITest;
import utils.RandomDataGenerator;
import utils.RetryAnalyzer;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@Feature("Data page - Reset and deleting Layouts")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckLayoutSidebarDeletingTest extends BaseUITest {
    @Description("Check the capability of reset custom user layout via Question sidebar")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testResetAppliedUserLayout() {
        DataTable table = getDataTable("ce79f06563");
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.getLayoutsSideBar().click();
        questionSideBar.dragAndDropRandomQuestions();
        List<String> adjustedQuestionList = getLayoutQuestionList();
        layoutsSideBar.getResetButton().click();
        List<String> restoredQuestionList = getLayoutQuestionList();
        boolean isQuestionDataRestores = adjustedQuestionList.equals(restoredQuestionList);
        assertThat(isQuestionDataRestores).isTrue();

    }

    @Description("Check the capability of delete custom user layout via Question sidebar")
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testDeleteAppliedUserLayout() {
        DataTable table = getDataTable("25269bf04d");
        waitUntilSurveyFullyLoaded(table);
        var expectedLayoutName = RandomDataGenerator.getRandomText().toLowerCase(Locale.ROOT);
        correctionsPage.getLayoutsSideBar().click();
        questionSideBar.dragAndDropRandomQuestions();
        questionSideBar.hideRandomQuestions();
        questionSideBar.getSaveLayoutMenu().click();
        questionSideBar.fillLayoutPopUp(expectedLayoutName);
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.getLayoutsSideBar().click();
        layoutsSideBar.getGeneralLayoutDropDownMenu().click();
        layoutsSideBar.switchToCreatedUserLayout(expectedLayoutName);
        layoutsSideBar.getExpandMoreButton().click();
        layoutsSideBar.clickOnDeleteLayoutButton();
        layoutsSideBar.clickOnDeleteButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        correctionsPage.getLayoutsSideBar().click();
        String defaultLayoutName = layoutsSideBar.getGeneralLayoutDropDownMenu().text();
        assertThat(expectedLayoutName).isNotEqualTo(defaultLayoutName);
    }

    private DataTable getDataTable(String projectId) {
        return correctionsPage
                .go(56, projectId)
                .getDataTable();
    }

    private void waitUntilSurveyFullyLoaded(DataTable table) {
        table.waitUntilRowsLoaded();
        table.waitUntilProjectLoaded();
        table.waitUntilProjectLoadedMessageDisappears();
    }

    private List<String> getLayoutQuestionList() {
        FluentList<FluentWebElement> adjustedQuestionListElements = find(By.xpath("//button[@class='dropdown-element ng-star-inserted']"));
        List<String> adjustedQuestionList = adjustedQuestionListElements.stream().map(x -> x.attribute("value")).collect(Collectors.toList());
        return adjustedQuestionList;
    }

    private void reloadProjectPage() {
        correctionsPage.getDriver().navigate().refresh();
    }
}
