package com.verifone.tests.mpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBADashboardPage;
import com.verifone.pages.mpPages.CBAProductsPage;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.loginMPPortal;
import static com.verifone.tests.steps.mpPortal.Steps.navigateCBAHome;

public class UnpublishProdPackageUI extends BaseTest {

    @Test(priority = 1, testName = "LogIn & remove package", description = "log in to MarketPlace and unpublish package from Production Catalog")
    public void CBAUnpublishPackageTestUI() throws Exception
    {
        User EOAdminSupport = EntitiesFactory.getEntity("EOAdminSupport");
        String EOAdminSupportMail = EOAdminSupport.getUserName();
        String EOAdminSupportPwd = EOAdminSupport.getPassword();
        String EOAdminSupportAnsw = EOAdminSupport.getSecurityAnswer();

        navigateCBAHome();

        loginMPPortal(EOAdminSupportMail, EOAdminSupportPwd, EOAdminSupportAnsw);
        CBADashboardPage cbaDashboard = PageFactory.getCBADashboard();
        cbaDashboard.manageMarketpace();

        CBAProductsPage cbaProducts = PageFactory.getCBAProducts();
        cbaProducts.removeProduct();
        cbaProducts.unPublishProduct();
        cbaProducts.deleteSatgingProduct();
    }
}
