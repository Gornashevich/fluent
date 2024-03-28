package ui.basicnavigation;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.fluentlenium.configuration.ConfigurationProperties;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.corrections.widgets.data.DataTable;
import ui.BaseUITest;
import utils.RandomDataGenerator;
import utils.RetryAnalyzer;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Correction page")
@Feature("Data page - Creation and applying Layouts")
@FluentConfiguration(driverLifecycle = ConfigurationProperties.DriverLifecycle.CLASS)
public class CheckLayoutSidebarCreationTest extends BaseUITest {
    @Description("Check the capability of creating custom user layout via Question sidebar")
    @Test()
    public void testCreateUserLayout() {
        DataTable table = getDataTable("16737bac78");
        waitUntilSurveyFullyLoaded(table);
        var expectedLayoutName = RandomDataGenerator.getRandomText().toLowerCase(Locale.ROOT);
        correctionsPage.getLayoutsSideBar().click();
        questionSideBar.dragAndDropRandomQuestions();
        questionSideBar.hideRandomQuestions();
        questionSideBar.getSaveLayoutMenu().click();
        questionSideBar.fillLayoutPopUp(expectedLayoutName);
        layoutsSideBar.getGeneralLayoutDropDownMenu().click();
        layoutsSideBar.switchToCreatedUserLayout(expectedLayoutName);
        String actualLayoutName = layoutsSideBar.getGeneralLayoutDropDownMenu().text().toLowerCase(Locale.ROOT).replaceAll("expand_more", "").trim();
        assertThat(expectedLayoutName).isEqualTo(actualLayoutName);
    }

    @Description("Check the capability of applying custom user layout via Question sidebar")
    @Test(groups = {"groupNeedingCleanup"}, retryAnalyzer = RetryAnalyzer.class)
    public void testApplyCreatedUserLayout() {
        DataTable table = getDataTable("6acaa60529");
        waitUntilSurveyFullyLoaded(table);
        List<String> questionsSetBeforeUserLayoutApply = getDataTabPanelList();
        var newRandomLayoutName = RandomDataGenerator.getRandomText();
        correctionsPage.getLayoutsSideBar().click();
        questionSideBar.dragAndDropRandomQuestions();
        questionSideBar.hideRandomQuestions();
        questionSideBar.getSaveLayoutMenu().click();
        questionSideBar.fillLayoutPopUp(newRandomLayoutName);
        layoutsSideBar.getGeneralLayoutDropDownMenu().click();
        layoutsSideBar.switchToCreatedUserLayout(newRandomLayoutName);
        layoutsSideBar.clickOnApplyButton();
        reloadProjectPage();
        waitUntilSurveyFullyLoaded(table);
        List<String> questionsSetAfterResetUserLayoutApply = getDataTabPanelList();

        assertThat(questionsSetAfterResetUserLayoutApply).isNotEqualTo(questionsSetBeforeUserLayoutApply);

    }

    @AfterMethod(groups = {"groupNeedingCleanup"})
    public void cleanup() {
        reloadProjectPage();
        correctionsPage.getLayoutsSideBar().await().until().clickable();
        layoutsSideBar.getSideBarList().first().await().atMost(Duration.ofMillis(3000));
        layoutsSideBar.getSideBarList().first().waitAndClick();
        layoutsSideBar.getGeneralLayoutDropDownMenu().waitAndClick();
        layoutsSideBar.clickOnCreatedUserLayout();
        layoutsSideBar.getExpandMoreButton().waitAndClick();
        layoutsSideBar.clickOnDeleteLayoutButton();
        layoutsSideBar.clickOnDeleteButton();
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

    private void reloadProjectPage() {
        correctionsPage.getDriver().navigate().refresh();
    }

    private List<String> getDataTabPanelList() {
        List<FluentWebElement> initialQuestionSet = find(By.xpath(".//thead[@role='rowgroup']/tr/th"));
        List<String> questionsBeforeReset = initialQuestionSet.stream().map(FluentWebElement::text).filter(text -> !text.isEmpty()).collect(Collectors.toList());
        return questionsBeforeReset;
    }

}
