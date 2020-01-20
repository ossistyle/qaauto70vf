package test.web.applications;

import com.verifone.infra.User;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.pages.HomePage;
import web.pages.LoginPage;
import web.pages.applications.AppCatalogPage;
import test.web.BaseWebTest;
import utils.mock.AppMock;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;

public class AppCataloguePageTest extends BaseWebTest {

    // TODO Replace mock data to data from API
    private AppMock app;
    private User merchant;
    private int testAppIndex = 0;

    private AppCatalogPage appCatalogPage;

    @BeforeClass (description = "Get test data")
    public void getTestData() {
        merchant = envConfig.getCredentials().getVFMPMer();
        app = new AppMock();
    }

    @BeforeMethod (description = "Login as merchant > Open app catalog")
    public void beforeTest() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        appCatalogPage = new AppCatalogPage();

        loginPage.navigate();
        loginPage.doLogin(merchant);
        // TODO Uncomment after adding route
//        appCatalogPage.navigate();
        homePage.mainMenu.clickApplications();
        homePage.mainMenu.clickAppCatalogue();
    }

    @Test (description = "User be able to see app images, app names, app descriptions on the App Catalog page > Card view test.web.applications", groups = {"ui", "regression"}, testName = "Card view test.web.applications")
    @Epic ("")
    @Feature ("")
    @Link ("10204018")
    @Severity (SeverityLevel.NORMAL)
    @Description ("User be able to see app images, app names, app descriptions on the App Catalog page > Card view test.web.applications")
    public void cardsViewApplicationsListUI() {
        appCatalogPage.cardsViewTable.getCardImages().shouldHave(sizeGreaterThan(0))
                .get(testAppIndex).should(exist).shouldBe(visible);
        appCatalogPage.cardsViewTable.getAppNames().shouldHave(sizeGreaterThan(0))
                .get(testAppIndex).should(exist).shouldHave(textCaseSensitive(app.getName()));
        appCatalogPage.cardsViewTable.getAppDescriptions().shouldHave(sizeGreaterThan(0))
                .get(testAppIndex).should(exist).shouldHave(textCaseSensitive(app.getDescription()));
    }

    @Test (description = "User be able to see app images, app names, app descriptions on the App Catalog page > Grid view test.web.applications", groups = {"ui", "regression"}, testName = "Grid view test.web.applications")
    @Feature ("")
    @Link ("")
    @Severity (SeverityLevel.NORMAL)
    @Description ("User be able to see app images, app names, app descriptions on the App Catalog page > Grid view test.web.applications")
    public void gridViewApplicationsListUI() {
        appCatalogPage.filterPanel.clickGridView();

        appCatalogPage.gridViewTable.getAppNames().shouldHave(sizeGreaterThan(0))
                .get(testAppIndex).should(exist).shouldHave(textCaseSensitive(app.getName()));
        appCatalogPage.gridViewTable.getAppDescriptions().shouldHave(sizeGreaterThan(0))
                .get(testAppIndex).should(exist).shouldHave(textCaseSensitive(app.getDescription()));
        appCatalogPage.gridViewTable.getAppVersions().shouldHave(sizeGreaterThan(0))
                .get(testAppIndex).should(exist).shouldHave(textCaseSensitive(app.getVersion()));
    }

    @Test (description = "User be able to see app name, image, id, version, description, publish date, update date on the App Catalog page > Quick view application", groups = {"ui", "regression"},testName = "Quick view application")
    @Feature ("")
    @Link ("")
    @Severity (SeverityLevel.NORMAL)
    @Description ("User be able to see app name, image, id, version, description, publish date, update date on the App Catalog page > Quick view application")
    public void quickViewOfApplicationUI() {
        appCatalogPage.cardsViewTable.clickQuickViewButton(testAppIndex);

        appCatalogPage.quickView.getAppName().should(exist).shouldHave(textCaseSensitive(app.getName()));
        appCatalogPage.quickView.getAppImage().shouldBe(visible);
        appCatalogPage.quickView.getAppId().should(exist).shouldHave(textCaseSensitive(app.getId()));
        appCatalogPage.quickView.getAppVersionLabel().should(exist).shouldHave(textCaseSensitive("Version"));
        appCatalogPage.quickView.getAppVersionValue().should(exist).shouldHave(textCaseSensitive(app.getVersion()));
        appCatalogPage.quickView.getAppDescriptionLabel().should(exist).shouldHave(textCaseSensitive("App Desc"));
        appCatalogPage.quickView.getAppDescriptionValue().should(exist).shouldHave(textCaseSensitive(app.getDescription()));
        appCatalogPage.quickView.getAppPublishDateLabel().should(exist).shouldHave(textCaseSensitive("Publish date"));
        appCatalogPage.quickView.getAppPublishDateValue().should(exist).shouldHave(textCaseSensitive(app.getPublishDate()));
        appCatalogPage.quickView.getAppUpdateDateLabel().should(exist).shouldHave(textCaseSensitive("Update date"));
        appCatalogPage.quickView.getAppUpdateDateValue().should(exist).shouldHave(textCaseSensitive(app.getUpdateDate()));
    }
}
