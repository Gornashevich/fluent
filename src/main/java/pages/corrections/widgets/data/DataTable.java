package pages.corrections.widgets.data;

import com.google.common.collect.Iterators;
import io.qameta.allure.Step;
import lombok.Getter;
import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentInstantiator;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.corrections.widgets.Row;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DataTable extends FluentWebElement {
    @FindBy(xpath = ".//thead[@role='rowgroup']/tr/th")
    private List<FluentWebElement> respondentRowList;
    @FindBy(xpath = "//thead/tr[not(contains(@class,'columns'))]/th")
    private List<FluentWebElement> headerCells;
    private final By cells = By.className("mat-cell");
    @FindBy(xpath = ".//tbody/tr")
    private FluentList<Row> rows;
    @FindBy(xpath = ".//tbody/tr[contains(@class,'original')]")
    private FluentList<Row> originalDataRows;
    @FindBy(xpath = "//div[contains(@class,'mdc-circular-progress__circle-left')]")
    private FluentWebElement circleSpinner;
    @FindBy(xpath = "//simple-snack-bar[contains(@class,'mat-simple-snackbar')]")
    private FluentWebElement projectLoadedMessage;
    @FindBy(xpath = "//td[contains(@class,'respondentId')]")
    private List<FluentWebElement> respondentIdList;
    @FindBy(xpath = "//button[contains(@class,'reset-button')]")
    private FluentWebElement resetAllButton;
    @FindBy(xpath = "//p[contains(text(),' Changes are not saved')]")
    private FluentWebElement changesAreNotSavedNotificationMessage;
    @FindBy(xpath = "//p[contains(text(),' All changes saved')]")
    private FluentWebElement allChangesSavedNotificationMessage;
    @FindBy(xpath = "//td[contains(@class, 'status')]")
    private List<FluentWebElement> statusesList;
    @FindBy(xpath = "//app-corrections-highlight-changes/mat-radio-group/mat-radio-button")
    private FluentWebElement unsavedHighlightChangesButton;
    @FindBy(xpath = "//app-corrections-highlight-changes/mat-radio-group/mat-radio-button[2]")
    private FluentWebElement allHighlightChangesButton;
    @FindBy(xpath = "//input[@value='numeric']")
    private FluentWebElement switchNumericValueButton;
    @FindBy(xpath = "//button[contains(@class,'sort-active')]")
    private FluentWebElement activeSortArrow;
    @FindBy(xpath = "//button[contains(@class,'export-button')]/mat-icon")
    private FluentWebElement expandImportButton;
    @FindBy(xpath = "//button[contains(@class,'import-option')]")
    private FluentWebElement importButton;
    @FindBy(id = "//*[@id='new-export-apply-btn']")
    private FluentWebElement importFileButton;
    @FindBy(xpath = "//span[contains(@class,'mat-button-wrapper') and text()='Done']")
    private FluentWebElement doneButton;
    @FindBy(xpath = "//app-applied-filters-menu/button")
    private FluentWebElement filtersMenu;

    private final String hiddenSortArrow = "//th[contains(@class,'%s')]/div/app-corrections-sorting/button[contains(@class,'button-icon sorting')]";
    private final String columnAnswersList = "//td[contains(@class,'%s')]/app-table-cell-wrapper/div";
    private final String iteratedColumn = "//th[contains(@class,'%s')]/div/app-corrections-sorting";
    private final String column = "//thead/tr[not(contains(@class,'columns'))]/th[contains(@class,'%s')]";


    public DataTable(WebElement element, FluentControl control, ComponentInstantiator instantiator) {
        super(element, control, instantiator);
    }

    public DataTable waitUntilRowsLoaded() {
        await()
                .atMost(Duration.ofSeconds(20))
                .until(rows)
                .size()
                .greaterThan(0);
        return this;
    }

    public DataTable waitUntilProjectLoaded() {
        await()
                .atMost(Duration.ofSeconds(20))
                .until(circleSpinner)
                .not()
                .present();
        return this;
    }

    public DataTable waitUntilProjectLoadedMessageDisappears() {
        await()
                .atMost(Duration.ofSeconds(10))
                .until(projectLoadedMessage)
                .not()
                .present();
        return this;
    }

    public List<String> getHeadersText() {
        return headerCells.stream().map(FluentWebElement::text).collect(Collectors.toList());
    }
    @Step("Get the first respondent Id")
    public String getFirstRespondentId() {
        List<FluentWebElement> respondentIdList = this.respondentIdList;
        String respondentId = respondentIdList.stream().findFirst().get().text();
        return respondentId;
    }
    @Step("Get the last respondent Id")
    public String getLastRespondentStatus() {
        List<FluentWebElement> status = this.statusesList;
        long count = status.size();
        String chosenStatus = status.stream().skip(count - 1).findFirst().get().text();
        return chosenStatus;
    }

    public FluentWebElement getRow(int index) {
        return this.getRows().get(index);
    }

    public int getColumnIndexByName(String text, boolean equals) {

        List<String> columns = getHeaderCells().stream()
                .map(e -> e.text().trim())
                .collect(Collectors.toList());

        return equals
                ? columns.indexOf(text) + 1
                : Iterators.indexOf(columns.iterator(), s -> s.contains(text)) + 1;
    }

    /**
     * @return The whole cell in a row
     */
    public List<FluentWebElement> getCells(int rowIndex) {
        return getRow(rowIndex).find(cells);
    }

    /**
     * Cell ny index
     */
    public FluentWebElement getCell(String column, int rowIndex, boolean equals) {
        var index = this.getColumnIndexByName(column, equals);

        return getCells(rowIndex).get(index);
    }

    public boolean isConfiguredVirtualDataDisplays(String newQuestionText, List<FluentWebElement> respondentData) {
        boolean contains = false;
        for (FluentWebElement inputAnswer : respondentData) {
            if (inputAnswer.text().contains(newQuestionText)) {
                contains = true;
                break;
            }

        }
        return contains;
    }

    public boolean isConfiguredVirtualResponseDisplays(String newQuestionText, List<FluentWebElement> inputAnswers) {
        boolean contains = false;
        List<FluentWebElement> temp = inputAnswers;
        temp.stream().findFirst().get().click();
        for (int i = 0; i < 1; i++) {
            temp.get(0).click();
        }
        for (FluentWebElement inputAnswer : inputAnswers) {
            if (inputAnswer.text().contains(newQuestionText)) {
                contains = true;
                break;
            }

        }
        return contains;
    }

    public boolean isCreatedVirtualResponseDisplays(String newQuestionText, List<FluentWebElement> list) {
        boolean contains = false;
        FluentList<FluentWebElement> temp = (FluentList<FluentWebElement>) list;
        temp.first().waitAndClick();
        FluentList<FluentWebElement> elements = find(By.xpath(".//mat-option[.//mat-pseudo-checkbox and not(contains(@class, 'selected')) and not(./span/div[@class='reset'])]"));
        for (FluentWebElement element : elements) {
            contains = element.text().contains(newQuestionText);
        }
        return contains;
    }

    public boolean isProperRespondentInfoDisplays(String columnName, List<FluentWebElement> respondentRow) {
        boolean contains = false;
        if (respondentRow.size() != 0) {
            for (FluentWebElement inputAnswer : respondentRow) {
                if (inputAnswer.text().contains(columnName)) {
                    contains = true;
                    break;
                }
            }
        }

        return contains;
    }

    @Step("All Changes Saved message is displayed")
    public boolean isAllChangesSavedNotificationMessageDisplays() {
        return allChangesSavedNotificationMessage.displayed();
    }

    @Step("All Changes Are Not Saved message is displayed")
    public boolean isChangesAreNotSavedNotificationMessageDisplays() {
        return changesAreNotSavedNotificationMessage.displayed();
    }

    public void clickResetAllButton() {
        resetAllButton.await().until().clickable();
        resetAllButton.waitAndClick();
    }
    @Step("Click on Default arrow")
    public void clickOnDefaultArrow(String columName) {
        String formatValue = String.format(hiddenSortArrow, columName);
        FluentWebElement el = el(By.xpath(formatValue));
        el.waitAndClick();


    }
    @Step("Reset filters from Data tab only")
    public void resetFiltersFromDataTab() {
        filtersMenu.await().until().enabled();
        filtersMenu.click();
        resetAllButton.await().until().displayed();
        resetAllButton.click();
    }
    @Step("Get respondent answers")
    public List<FluentWebElement> getColumnAnswersList(String columName) {
        String formatValue = String.format(columnAnswersList, columName);
        return find(By.xpath(formatValue));
    }
    @Step("Sort column answers by Ascending order")
    public List<String> sortColumnAnswersByAscOrder(List<FluentWebElement> columnAnswers) {
        List<String> sortedAnswers = columnAnswers.stream()
                .map(FluentWebElement::text).sorted().collect(Collectors.toList());
        return sortedAnswers;
    }
    @Step("Sort column answers by Descending order")
    public List<String> sortColumnAnswersByDescOrder(List<FluentWebElement> columnAnswers) {
        List<String> sortedAnswers = columnAnswers.stream()
                .map(FluentWebElement::text).sorted(Collections.reverseOrder()).collect(Collectors.toList());
        return sortedAnswers;
    }

    public void hoverOverIteratedColumn(String columName) {
        String formatValue = String.format(iteratedColumn, columName);
        FluentWebElement el = el(By.xpath(formatValue));
        el.hoverOver();
    }
    @Step("Hover over column")
    public void hoverOverColumn(String columName) {
        String formatValue = String.format(iteratedColumn, columName);
        FluentWebElement el = el(By.xpath(formatValue));
        el.hoverOver();
    }

    public List<String> getColumnAnswers(List<FluentWebElement> answers) {
        List<String> names = answers.stream()
                .map(FluentWebElement::text)
                .collect(Collectors.toList());
        return names;
    }
}
