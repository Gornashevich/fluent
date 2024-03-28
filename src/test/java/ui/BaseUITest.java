package ui;

import config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.fluentlenium.adapter.testng.FluentTestNg;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.utils.SeleniumVersionChecker;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.corrections.CorrectionsPage;
import pages.corrections.widgets.data.*;
import pages.corrections.widgets.data.sidebar.*;
import pages.corrections.widgets.metadata.*;
import pages.login.LoginPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;

import static org.openqa.selenium.UnexpectedAlertBehaviour.ACCEPT;

public class BaseUITest extends FluentTestNg {
    @Page
    protected LoginPage loginPage;
    @Page
    protected CorrectionsPage correctionsPage;
    @Page
    protected MetadataTable metadataTable;
    @Page
    protected QuestionSideBar questionSideBar;
    @Page
    protected LayoutsSideBar layoutsSideBar;
    @Page
    protected FilterSideBar filterSideBar;
    @Page
    protected SettingsSideBar settingsSideBar;
    @Page
    protected FilterByQuestionBlockMenu filterByQuestionBlockMenu;
    @Page
    protected RespondentStatus respondentStatus;
    @Page
    protected FilterDate filterDate;
    @Page
    protected PageIssue pageIssue;
    @Page
    protected DataIssuesContainer dataIssuesContainer;
    @Page
    protected CancelChanges cancelChanges;
    @Page
    protected SetToOriginalMenu setToOriginalMenu;
    @Page
    protected QuestionTextMenu questionTextMenu;
    @Page
    protected RowsMenu rowsMenu;
    @Page
    protected ColumnsMenu columnsMenu;
    @Page
    protected CellsMenu cellsMenu;
    @Page
    protected NewVirtualQuestion newVirtualQuestion;
    @Page
    protected ImportMenu importMenu;

    @Override
    public WebDriver newWebDriver() {
        WebDriverManager.getInstance().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("browser_version=114.0");
        options.addArguments("--incognito");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.setUnhandledPromptBehaviour(ACCEPT);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);


        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444/wd/hub").toURL(),
                    capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().setSize(new Dimension(1920, 1070));
        return driver;
    }

    @BeforeClass
    public void login() {
        SeleniumVersionChecker.checkSeleniumVersion();
        WebDriverManager.getInstance().setup();
        starting(getClass());
        goTo(loginPage).await()
                .atMost(15)
                .untilPage()
                .isLoaded();
        loginPage.logIn(ConfigManager.config.userEmail(), ConfigManager.config.userPassword());

    }

    @Override
    public String getBaseUrl() {
        return ConfigManager.config.baseAppUrl();
    }

    protected String getTextValue(FluentWebElement webElement) {
        await().until(webElement).displayed();
        return webElement.text();
    }

    protected void clickOnAnyPlace() {
        new Actions(getDriver()).sendKeys(Keys.TAB).build().perform();

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Create allure-results directory if it doesn't exist
            File allureResultsDir = new File("./build/allure-results");
            if (!allureResultsDir.exists()) {
                allureResultsDir.mkdirs();
            }

            // Capture screenshot for failed test
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(getDriver());
            String screenshotName = result.getName() + ".png";
            ImageIO.write(screenshot.getImage(), "PNG", new File("./build/allure-results/" + screenshotName));
        }

    }
}