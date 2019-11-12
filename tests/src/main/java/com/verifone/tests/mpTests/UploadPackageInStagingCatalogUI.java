package com.verifone.tests.mpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.CBADashboard;
import com.verifone.pages.mpPages.CBAProducts;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;

public class UploadPackageInStagingCatalogUI extends BaseTest {

    private static String productVersionTitle;

    //This test describe all the action related to package upload on CBA Market place but not publish.
    @Test(priority = 0, testName = "LogIn & Upload Package - CBA MarketPlace", description = "Log in to CBA MarketPlace and upload a package in staging catalog")
    public void CBAUploadAppTestUI() throws Exception {

        loginMPPortalAsEOAdmin();
        productVersionTitle = BaseTest.envConfig.getApplicationID();

        CBADashboard cbaDashboard = PageFactory.getCBADashboard();
        cbaDashboard.manageMarketpace();

        CBAProducts cbaProducts = PageFactory.getCBAProducts();
        cbaProducts.createStagingProduct();
        cbaProducts.listingInfoProduct();
        cbaProducts.profileProduct();
        cbaProducts.credentialsOath();
        cbaProducts.editIntegration();
        cbaProducts.editAuthentication();
        cbaProducts.runPingTests();
        cbaProducts.integrationReport();
        cbaProducts.addPlatform();
        cbaProducts.productVersion("appV2Signed : true", productVersionTitle);
        // cbaProducts.approveProductVersion();

        //temporary code - This code will be deleted once we find the way to sign the package using V2
        cbaProducts.deleteProductVersion();


    }

    //This test describe all the actions to delete the package from the marketplace - Staging Catalog.
    @Test(priority = 1, testName = "LogIn & Delete Package - CBA MarketPlace", description = "Log in to CBA MarketPlace and delete package from Staging Catalog")
    public void CBADeletePackageTestUI() throws Exception {
        loginMPPortalAsEOAdmin();

        // go to the product section
        CBADashboard cbaDashboard = PageFactory.getCBADashboard();
        cbaDashboard.manageMarketPlaceProducts();

        // got to the staging catalog
        CBAProducts cbaProducts = PageFactory.getCBAProducts();
        cbaProducts.clickStagingProduct();
        cbaProducts.deleteSatgingProduct();
    }
}
