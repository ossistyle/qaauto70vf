package com.verifone.pages;

//import com.verifone.utils.CGLoginPage.CGLoginPage;

import com.verifone.pages.cgPages.CGApplicationPage;
import com.verifone.pages.cgPages.CGLoginPage;
import com.verifone.pages.cpPages.*;
import com.verifone.pages.eoPages.*;
import com.verifone.pages.vfMpPages.SimpleTestAngular8Page;
import com.verifone.pages.vhqPages.*;
import com.verifone.pages.mpPages.*;
import com.verifone.utils.Mail.InboxGetnada;

public class PageFactory {

    ///////////////////MarketPlace Pages/////////////////////////
    public static CBAHomePage getCBAHomePage() {return new CBAHomePage();}
    public static CBALoginPage getCBALoginPage() {return new CBALoginPage();}
    public static CBAMyAppsPage getCBAMyApps() {return new CBAMyAppsPage();}
    public static CBAMarketplacePage getCBAMarketplace() {return new CBAMarketplacePage();}
    public static CBAAccountPage getCBAAccount() {return new CBAAccountPage();}

    public static CBAProductsPage getCBAProducts() {return new CBAProductsPage();}
    public static CBADashboardPage getCBADashboard () {return new CBADashboardPage();}
    public static LoginMPPortalPage getLoginMPPortal() {return new LoginMPPortalPage();}
    public static AndroidProjectOperationPage getAndroidProjectOperationPage() {return new AndroidProjectOperationPage();}
    public static CBAResellerPage getCBAResellerPage(){return new CBAResellerPage();}

    public static CBAAssignPage getAssignAppPage() {return new CBAAssignPage();}
    public static ProdTabCreateProductPage getCreateProduct() {return new ProdTabCreateProductPage();}
    public static ProductsTabStagingCatalogPage getStagingCatalog() {return new ProductsTabStagingCatalogPage();}
    public static ReportsPage getReportsPage() {return new ReportsPage();}

    ///////////////////VHQ Pages///////////////////////////////
    public static VHQLogin getVHQLogin() {return new VHQLogin();}
    public static VHQHomePage getVHQHomePage() {return new VHQHomePage();}
    public static VHQDownloadLibrary getVHQDownloadLibrary() {return new VHQDownloadLibrary();}
    public static VHQDeviceSearch getVHQDeviceSearch() {return new VHQDeviceSearch();}

    public static CGLoginPage getCGLoginPage(){return new CGLoginPage();}
    public static DLMLoginPage getDLMLoginPage(){return new DLMLoginPage();}
    public static CBAAssignGroupPage getCBAAssignGroupPage(){return  new CBAAssignGroupPage();}
    public static MPProductsPage getMPProductsPage(){return new MPProductsPage();}

    ///////////////////Verifone MarketPlace Pages/////////////////////////
    public static SimpleTestAngular8Page getSimpleTestAngular8Page() {return new SimpleTestAngular8Page();}



    public static BasePage getPage(String page) {
        switch (page) {
            case "LoginPage":
                return new LoginPage();
            case "LoginEOPortal":
                return new LoginEOPortal();
            case "OktaLogin":
                return new OktaLogin();
            case "LoginSSOPage":
                return new LoginSSOPage();

            case "CreateMerchantPage":
                return new CreateMerchantPage();

            case "SignUpPage":
                return new SignUpPage();

            case "CGLoginPage":
                return new CGLoginPage();

            case "CBAHomePage":
                return new CBAHomePage();

            case "CGApplicationPage":
                return new CGApplicationPage();

            case "DevSupportHomePage":
                return new DevSupportHomePage();

            case "DevUsersPage":
                return new DevUsersPage();
//
//            case "VerifoneAccountLoginPage":
//                return new VerifoneAccountLoginPage();


            case "SetupPasswordPage":
                return new SetupPasswordPage();

            case "AcceptMerchantAgreementPage":
                return new AcceptMerchantAgreementPage();

            case "MerchantViewPage":
                return new MerchantViewPage();
            case "ForgotPasswordPage":
                return new ForgotPasswordPage();
            case "EmailConfirmPage":
                return new EmailConfirmPage();
            case "ResetPasswordPage":
                return new ResetPasswordPage();
            case "ResetThankYou":
                return new ResetThankYou();
            case "WelcomePage":
                return new WelcomePage();
            case "PasswordSetupPage":
                return new PasswordSetupPage();
            case "ChangePasswordPage":
                return new ChangePasswordPage();
            case "HomePage":
                return new HomePage();
            case "MPHomePage":
                return new MPHomePage();
            case "UsersPage":
                return new UsersPage();
            case "AddUserPage":
                return new AddUserPage();
            case "EditUserPage":
                return new EditUserPage();
            case "EditProfilePage":
                return new EditProfilePage();
            case "EditRolePage":
                return new EditRolePage();
            case "EditBusinessPage":
                return new EditBusinessPage();
            case "UserDetailsPage":
                return new UserDetailsPage();
            case "EmailTemplatePage":
                return new EmailTemplatePage();
            case "MerchantsPage":
                return new MerchantsPage();
            case "MerchantDetailsPage":
                return new MerchantDetailsPage();
            case "MerchantThankYouPage":
                return new MerchantThankYouPage();
            case "MyProfilePage":
                return new MyProfilePage();
            case "NoAccessPage":
                return new NoAccessPage();
//            case "VerifoneAccountLoginPage":
//                return new VerifoneAccountLoginPage();
//
            case "DevHomePage":
                return new DevHomePage();
//
            case "DevProfilePage":
                return new DevProfilePage();

            case "NewAppFormPage":
                return new NewAppFormPage();

            case "InboxGetnada":
                return new InboxGetnada();
            case "ActionRequiredPage":
                return new ActionRequiredPage();
            case "AgreementPage":
                return new AgreementPage();
            case "ManageMarketplacePage":
                return new ManageMarketplacePage();
            case "ProductsTab":
                return new ProductsTabPage();
            case "CreateSegmentGroupPage":
                return new CreateSegmentGroupPage();
            case "EditSegmentGroupPage":
                return new EditSegmentGroupPage();
            case "CreateSegmentPage":
                return new CreateSegmentPage();
            case "EditSegmentPage":
                return new EditSegmentPage();
            case "ProductsTabProductCatalogPage":
                return new ProductsTabProductCatalogPage();
            case "ProductSettingsProductsTab":
                return new ProductSettingsProductsTabPage();
            case "MarketplacePage":
                return new MarketplacePage();
            case "LoginMPPortal":
                return new LoginMPPortalPage();
            case "ProductsTabBundlePage":
                return new ProductsTabBundlePage();
            case "MPUsersPage":
                return new MPUsersPage();
            case "MPGroupsPage":
                return new MPGroupsPage();
            case "MPAccountPage":
                return new MPAccountPage();
            case "SimpleTestAngular8Page":
                return new SimpleTestAngular8Page();
            default:
                System.out.println("Can not create a Page, missing implementation of class " + page);
        }
        return null;
    }

}
