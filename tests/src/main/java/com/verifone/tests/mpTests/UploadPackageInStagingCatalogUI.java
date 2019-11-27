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

    @Test(priority = 0, testName = "LogIn & Delete Package if Exists in Production Catalog ", description = "Log in to CBA MarketPlace and Delete Package if it exists in the Production Catalog.")
    public void CBADeleteProductionPackageTestUI() throws Exception {
        loginMPPortalAsEOAdmin();

        CBADashboard cbaDashboard = PageFactory.getCBADashboard();
        cbaDashboard.manageMarketPlaceProducts();

        //Remove product from the production catalog only if it is present
        CBAProducts cbaProducts = PageFactory.getCBAProducts();
        if (cbaProducts.isProductAvailable()) {
            System.out.println("App present");
            cbaProducts.removeProduct();
            cbaProducts.unPublishProduct();
            cbaProducts.deleteSatgingProduct();
        }
    }

    //This test describe all the action related to package upload on CBA Market place but not publish.
    @Test(priority = 1, testName = "LogIn & Upload Package - CBA MarketPlace", description = "Log in to CBA MarketPlace and upload a package in staging catalog")
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
    @Test(priority = 2, testName = "LogIn & Delete Package - CBA MarketPlace", description = "Log in to CBA MarketPlace and delete package from Staging Catalog")
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
