package test.web.applications;

import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.merchant.pages.HomePage;
import web.LoginPage;
import web.merchant.pages.applications.AppCatalogPage;
import test.web.BaseWebTest;
import utils.mock.AppMock;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;

public class AppCataloguePageTest extends BaseWebTest {

    // TODO Replace mock data to data from API
    private AppMock app;
    private int testAppIndex = 0;

    private AppCatalogPage appCatalogPage;

    @BeforeClass (description = "Get test data")
    public void getTestData() {
        app = new AppMock();
    }

    @BeforeMethod (description = "Login as merchant > Open app catalog")
    public void beforeTest() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        appCatalogPage = new AppCatalogPage();

        loginPage.navigate();
        loginPage.doLogin(testData.getString("MerchantEmail"), testData.getString("MerchantPassword"));
        // TODO Uncomment after adding route
//        appCatalogPage.navigate();
        homePage.mainMenu.clickApplications();
        homePage.mainMenu.clickAppCatalogue();
    }

    @Test (description = "User be able to see app images, app names, app descriptions on the App Catalog page > Card view test.web.applications", groups = {"ui", "regression"})
    @Epic ("")
    @Feature ("")
    @Link ("")
    @Severity (SeverityLevel.NORMAL)
    public void cardsViewApplicationsList() {
        appCatalogPage.cardsViewTable.getCardImages().shouldHave(sizeGreaterThan(0))
                .get(testAppIndex).should(exist).shouldBe(visible);
        appCatalogPage.cardsViewTable.getAppNames().shouldHave(sizeGreaterThan(0))
                .get(testAppIndex).should(exist).shouldHave(exactText(app.getName()));
        appCatalogPage.cardsViewTable.getAppDescriptions().shouldHave(sizeGreaterThan(0))
                .get(testAppIndex).should(exist).shouldHave(exactText(app.getDescription()));
    }

    @Test (description = "User be able to see app images, app names, app descriptions on the App Catalog page > Grid view test.web.applications", groups = {"ui", "regression"})
    @Feature ("")
    @Link ("")
    @Severity (SeverityLevel.NORMAL)
    public void gridViewApplicationsList() {
        appCatalogPage.filterPanel.clickGridView();

        appCatalogPage.gridViewTable.getAppNames().shouldHave(sizeGreaterThan(0))
                .get(testAppIndex).should(exist).shouldHave(exactText(app.getName()));
        appCatalogPage.gridViewTable.getAppDescriptions().shouldHave(sizeGreaterThan(0))
                .get(testAppIndex).should(exist).shouldHave(exactText(app.getDescription()));
        appCatalogPage.gridViewTable.getAppVersions().shouldHave(sizeGreaterThan(0))
                .get(testAppIndex).should(exist).shouldHave(exactText(app.getVersion()));
    }

    @Test (description = "User be able to see app name, image, id, version, description, publish date, update date on the App Catalog page > Quick view application", groups = {"ui", "regression"})
    @Feature ("")
    @Link ("")
    @Severity (SeverityLevel.NORMAL)
    public void quickViewOfApplication() {
        appCatalogPage.cardsViewTable.clickQuickViewButton(testAppIndex);

        appCatalogPage.quickView.getAppName().should(exist).shouldHave(exactText(app.getName()));
        appCatalogPage.quickView.getAppImage().shouldBe(visible);
        appCatalogPage.quickView.getAppId().should(exist).shouldHave(exactText(app.getId()));
        appCatalogPage.quickView.getAppVersionLabel().should(exist).shouldHave(exactText("Version"));
        appCatalogPage.quickView.getAppVersionValue().should(exist).shouldHave(exactText(app.getVersion()));
        appCatalogPage.quickView.getAppDescriptionLabel().should(exist).shouldHave(exactText("App Desc"));
        appCatalogPage.quickView.getAppDescriptionValue().should(exist).shouldHave(exactText(app.getDescription()));
        appCatalogPage.quickView.getAppPublishDateLabel().should(exist).shouldHave(exactText("Publish date"));
        appCatalogPage.quickView.getAppPublishDateValue().should(exist).shouldHave(exactText(app.getPublishDate()));
        appCatalogPage.quickView.getAppUpdateDateLabel().should(exist).shouldHave(exactText("Update date"));
        appCatalogPage.quickView.getAppUpdateDateValue().should(exist).shouldHave(exactText(app.getUpdateDate()));
    }
}
