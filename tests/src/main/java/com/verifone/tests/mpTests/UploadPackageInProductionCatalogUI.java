package com.verifone.tests.mpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.AndroidProjectOperationPage;
import com.verifone.pages.mpPages.CBADashboard;
import com.verifone.pages.mpPages.CBAProducts;
import com.verifone.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.cgPortal.Steps.loginAndCheck;
import static com.verifone.tests.steps.mpPortal.Steps.*;


public class UploadPackageInProductionCatalogUI extends BaseTest {
    private static String productVersionTitle;

    /**
     * This test case described package upload on CBA market place, package signing using V1 & V2
     *
     * @author : Prashant Lokhande
     */

   /* @Test(priority = 1, testName = "LogIn & Generate build apk", description = "Update the AppId in AndroidManifest.xml , AppName in String.xml file and generate apk file. ")
    public static void AndroidAPKGenerateTestUI() {

        //Update app name, app id from the project & build the apk
        AndroidProjectOperationPage androidPage = PageFactory.getAndroidProjectOperationPage();
        androidPage.updateXMLFileWithAppName();
        androidPage.updateXMLFileWithAppId();
        androidPage.generateAndroidAPK();
    }

    @Test(priority = 2, testName = "LogIn & Sign Package - CGateway Portal", description = "LogIn into CGateway Portal & sign package using V1 signature.")
    public static void CBASignAPKOnCGatewayPortalTestUI() throws Exception {
        loginCGPortal(createCGUser());
        //loginCGPortal();
    }*/

   /* @Test(priority = 2, testName = "LogIn & Sign Package - Ordlxwolf Portal", description = "LogIn into Ordlxwolf Portal & sign package using V2 signature.")
    public static void CBASignAPKOnOrdlxwolfPortalTestUI() throws Exception {
        loginDLMPortal();
    }*/
    @Test(priority = 2, testName = "LogIn & Delete Package if Exists ", description = "Log in to CBA MarketPlace and Delete Package if it exists in the Production Catalog.")
    public void CBADeletePackageTestUI() throws Exception {
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

    @Test(priority = 3, testName = "LogIn & Upload Package - CBA MarketPlace", description = "LogIn into CBA Portal & upload package on Marketplace.")
    public static void CBAUploadPackageOnMarketPlaceTestUI() throws Exception {

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
        //cbaProducts.approveProductVersion();

        //temporary code - This code will be deleted once we find the way to sign the package using V2
        cbaProducts.deleteProductVersion();

        cbaProducts.publishProduct();
    }

    @Test(priority = 4, testName = "LogIn & Remove, UnPublish, Delete Package ", description = "Log in to CBA MarketPlace and Remove, UnPublish, Delete package from the marketplace.")
    public void CBAUnPublishPackageTestUI() throws Exception {
        loginMPPortalAsEOAdmin();

        CBADashboard cbaDashboard = PageFactory.getCBADashboard();
        cbaDashboard.manageMarketPlaceProducts();

        CBAProducts cbaProducts = PageFactory.getCBAProducts();
        cbaProducts.removeProduct();
        cbaProducts.unPublishProduct();
        cbaProducts.deleteSatgingProduct();
    }
}
