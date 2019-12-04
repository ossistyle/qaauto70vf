package com.verifone.tests.mpTests;

import com.verifone.pages.*;

import com.verifone.pages.cgPages.*;
import com.verifone.pages.mpPages.*;
import com.verifone.tests.BaseTest;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.mpPortal.Steps.*;


public class UploadV2SignedPackageUI extends BaseTest {

    private static String productVersionTitle;

    /*//This test describe update of details in the android manifest file and string.xml file.
    @Test(priority = 1, testName = "LogIn & Generate build apk", description = "Update the AppId in AndroidManifest.xml , AppName in String.xml file and generate apk file. ")
    public static void AndroidAPKGenerateTestUI() {

        //Update app name, app id from the project & build the apk
        AndroidProjectOperationPage androidPage = PageFactory.getAndroidProjectOperationPage();
        androidPage.updateXMLFileWithAppName();
        androidPage.updateXMLFileWithAppId();
        androidPage.generateAndroidAPK();
    }

    //This test describe the v1 signing procedure in cg-gateway portal.
    @Test(priority = 2, testName = "LogIn & Sign Package - CGateway Portal", description = "LogIn into CGateway Portal and sign package using V1 signature.")
    public static void CBASignAPKOnCGatewayPortalTestUI() throws Exception {

        //Login into CGateway portal and sign the package using V1
        //Copy the downloaded package from download directory to project
        loginCGPortal(createCGUser());
    }


    //This test describe the validation of package on cba marketplace for v1 signed package.
    @Test(priority = 3, testName = "LogIn & Validate Package Signature - CBA MarketPlace", description = "LogIn into CBA MarketPlace and verify that, result of appV2Signed should be false")
    public static void CBAVerifyV2SignatureOfThePackageFalseTestUI() throws Exception {

        //LogIn into CBAMarket Place as a EOAdmin User
        loginMPPortalAsEOAdmin();

        productVersionTitle = AndroidProjectOperationPage.androidProjectAppId;

        CBADashboard cbaDashboard = PageFactory.getCBADashboard();
        cbaDashboard.manageMarketpace();

        CBAProducts cbaProducts = PageFactory.getCBAProducts();
        cbaProducts.createStagingProduct();
        cbaProducts.addPlatform();
        cbaProducts.productVersion("appV2Signed : false", productVersionTitle);
        cbaProducts.deleteProductVersion();
        cbaProducts.deleteV1SignedPackage(productVersionTitle);
    }*/

    //This method describe all the actions related validation v2 signed package & delete the added platform
    @Test(priority = 4, testName = "LogIn & Validate Package Signature - CBA MarketPlace", description = "LogIn into CBA MarketPlace and verify that, result of appV2Signed should be true.")
    public static void CBAVerifyV2SignatureOfThePackageTrueTestUI() throws Exception {

        //LogIn into CBAMarket Place as a EOAdmin User
        loginMPPortalAsEOAdmin();

        productVersionTitle = BaseTest.envConfig.getApplicationID();

        CBADashboard cbaDashboard = PageFactory.getCBADashboard();
        cbaDashboard.manageMarketPlaceProducts();

        //Edit product details from the staging catalog and update platform
        CBAProducts cbaProducts = PageFactory.getCBAProducts();
        cbaProducts.editStagingCatalogProduct();
        cbaProducts.productVersion("appV2Signed : true", productVersionTitle);
        cbaProducts.deleteProductVersion();

        //This will implement after we are able to sign the package using V2 through automation
        //cbaProducts.deleteV1SignedPackage(productVersionTitle);
    }

    //This method describe all the actions related to deleting of product from the marketplace.
    @Test(priority = 5, testName = "LogIn & Delete Package - CBA MarketPlace", description = "Log in to CBA MarketPlace and delete package from Staging Catalog")
    public void CBADeletePackageTestUI() throws Exception {
        loginMPPortalAsEOAdmin();

        CBADashboard cbaDashboard = PageFactory.getCBADashboard();
        cbaDashboard.manageMarketPlaceProducts();

        CBAProducts cbaProducts = PageFactory.getCBAProducts();
        cbaProducts.clickStagingProduct();
        cbaProducts.unPublishProduct();
        cbaProducts.deleteSatgingProduct();
    }
}


