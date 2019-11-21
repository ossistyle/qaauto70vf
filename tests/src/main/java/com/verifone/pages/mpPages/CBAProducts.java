package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.verifone.utils.Assertions.assertTextContains;

public class CBAProducts extends BasePage {
    private final static String url = "";
    private final static String title = "";
    private static String getApplicationName = BaseTest.envConfig.getApplicationName();
    private static String getApplicationID = BaseTest.envConfig.getApplicationID();
    private static String getApplicationVersion = BaseTest.envConfig.getApplicationVersion();
    private static String getApplicationVersionCode = BaseTest.envConfig.getApplicationVersionCode();


    public CBAProducts() {
        super(url, title);
    }


    /////Staging Products/////
    private By createProduct = By.cssSelector(".adb-styled.menu.attached.addProductMenuLink button:first-child");

    /////Product Info/////
    private By appName = By.id("appNameField");
    private By modelFree = By.cssSelector("input[id='free'][type='radio']");
    private By createProductBtn = By.cssSelector("button[name='createButton'][type='submit']");
    private By btnCreateProduct = By.xpath("//button[contains(text(),'Create Product')][@class='go-to-import-link adb-button__small']");


    /////Listing Info/////
    private By listingInfo = By.linkText("Listing Info");
    private By wordDescription = By.cssSelector("input[name='blurbBorder:blurbBorder_body:blurbField'][type='text']");
    private By description = By.cssSelector("textarea[name='briefOverviewBorder:briefOverviewBorder_body:briefOverviewField']");
    private By linkPP = By.cssSelector("input[type='url'][name='privacyUrlBorder:privacyUrlBorder_body:privacyUrlField']");
    private By linkTC = By.cssSelector("input[type='url'][name='termsUrlBorder:termsUrlBorder_body:termsUrlField']");
    private By listingLogo = By.cssSelector("input[type='file'][name='uploadListingLogoContainer:fileUploadListingLogo']");
    private By profileLogo = By.cssSelector("input[type='file'][name='uploadProfileLogoContainer:fileUploadProfileLogo']");
    private By selectCategory = By.cssSelector("select[class ='adb-js-dropdown-select ']");
    private By saveBtn = By.cssSelector("button[class ='adb-button__primary adb-toolbar--item'][name='save']");
    private By feedBackPanel = By.xpath("//*[contains(text(),'Your listing')][@class ='feedbackPanelINFO']");
    private By savePreviewBtn = By.cssSelector("button[class ='adb-button__neutral adb-toolbar--item'][id='savePreview']");

    /////Profile/////
    private By addFeatureswBtn = By.xpath("//*[@id=\"main\"]/div/div[2]/section/div/div[1]/div/section[2]/div/div[2]/div/div/div[2]/button");
    private By profile = By.linkText("Profile");
    private By splashTitle = By.cssSelector("input[type='text'][name='slogansAndDescriptionContainer:sloganFieldBorder:sloganFieldBorder_body:sloganField']");
    private By splashDescript = By.cssSelector("textarea[name='slogansAndDescriptionContainer:descriptionFieldBorder:descriptionFieldBorder_body:descriptionField']");
    private By overviewImg = By.cssSelector("input[type='file'][name='uploaderOverviewImageContainer:fileUploadOverviewLogo']");
    private By demoUrl = By.cssSelector("input[type='url'][name='demoUrl']");
    private By docLink = By.cssSelector("input[type='url'][name='helpLinkField']");

    /////Credentials/////
    private By credentials = By.linkText("Credentials");
    private By autorizationType = By.xpath("//*[@class='sc-hgRTRy jFQzJN'][@name='authorizationType']");
    private By generateKey = By.xpath("//button[@class='sc-gldTML eVenQv'][@type='submit']");
    private By doneBtn = By.xpath("//button[@class='sc-gldTML dSzJck'][@type='button']");
    // private By oathFeedbackInfo = By.xpath("//*[contains(text(),'A new OAuth 1.0 consumer was successfully created')]");
    private final By oathFeedbackInfo = By.xpath("//div[@class='sc-dxgOiQ cZsGsY']");

    /////Edit Integration/////
    private By integtation = By.linkText("Edit Integration");
    private By subscrCreate = By.cssSelector("input[type='url'][name='subscriptionConfig:createUrlBorder:createUrlBorder_body:createUrl']");
    private By subscrChange = By.cssSelector("input[type='url'][name='subscriptionConfig:upgradeUrlBorder:upgradeUrlBorder_body:upgradeUrl']");
    private By subscrCancel = By.cssSelector("input[type='url'][name='subscriptionConfig:cancelUrlBorder:cancelUrlBorder_body:cancelUrl']");
    private By subscrStatus = By.cssSelector("input[type='url'][name='subscriptionConfig:notifyUrlBorder:notifyUrlBorder_body:notifyUrl']");
    private By getSubscr = By.cssSelector("input[type='url'][name='subscriptionConfig:eventStatusUrlBorder:eventStatusUrlBorder_body:eventStatusUrl']");
    private By userAssign = By.cssSelector("input[type='url'][name='accessManagementConfig:assignUrl']");
    private By userUnassign = By.cssSelector("input[type='url'][name='accessManagementConfig:unassignUrl']");
    private By confirmSaveBtn = By.cssSelector("button[class ='adb-button__primary buttonResponse'][id='posLabel']");
    private By feedBackPanelInfo = By.xpath("//*[contains(text(),'The integration')]");

    /////Edit Authentication/////
    private By authentication = By.linkText("Edit Authentication");
    private By integrationSelect = By.xpath("//select[@aria-label='select'][@name='authenticationMethod'][@class='adb-js-dropdown-select ']");
    private By landingURL = By.xpath("//input[@name='landingUrl'][@type='text']");
    private By saveBut = By.xpath("//button[@class='adb-button adb-toolbar--item adb-button__primary'][@aria-label='save']");
    private By authenticationFeedbackInfo = By.xpath("//*[contains(text(),'The authentication configuration was saved successfully')]");

    /////Run Ping Tests/////
    private By pingTests = By.linkText("Run Ping Tests");
    private By runAllTets = By.xpath("//button[@class='adb-button__primary']");

    /////Integration Report/////
    private By integrationReport = By.xpath("//a[contains(text(),'Integration Report')]");
    private By markComplete = By.xpath("//a[@class='adb-button adb-button__medium adb-button__neutral toggle-button'][1]");
    private By subscribeRun = By.xpath("//a[@class='adb-button adb-button__medium adb-button__neutral'][1]");
    private By createSubscription = By.xpath("//button[@class='createSubscription buttonResponse adb-button__primary continue-to-next']");

    /////Platforms/////
    private By platforms = By.xpath("//a[contains(text(),'Platforms')]");
    private By addPlatformBtn = By.cssSelector("a[class ='add adb-button adb-button__neutral adb-button__small'][href='#product-platforms']");
    private By deviceRadio = By.cssSelector("input[type='radio'][value='[object Object]']");
    private By selectBtn = By.cssSelector("button[type='button'][class='adb-button adb-toolbar--item adb-button__primary']");
    private By headerItem = By.cssSelector("h1[class='adb-header--item']");
    private By addProductVersion = By.cssSelector("button[type='button'][class='adb-button']");

    //////Product Version///////
    private By versionTitle = By.cssSelector("input[type='text'][name='title']");
    private By versionCode = By.cssSelector("input[type='text'][name='versionCode']");
    private By versionName = By.cssSelector("input[type='text'][name='versionName']");

    private By chooseAsset = By.xpath("//div[@class='adb-form--field field-filenameFile']//input[@type='file']");
    private By chooseIcon = By.xpath("(//*[contains(text(),'Choose File')]//preceding::input[@type='file'])[2]");
    private By chooseImage = By.xpath("//*[contains(text(),'Overview Image')]//following::input[@type='file'][1]");

    private By mobileFamily = By.cssSelector("input[type='checkbox'][value='CarbonMobile']");
    private By saveButton = By.cssSelector("button[type='button'][class ='adb-button js-save-button adb-toolbar--item adb-button__primary'][name='save']");
    private By productVersionPanel = By.xpath("//*[contains(text(),'Product version details have been saved')]");
    private By refreshPage = By.xpath("//a[@class='adb-local_alert--link'][contains(text(),'refresh this page.')]");
    private By validatedPackage = By.xpath("//*[contains(text(),'Successfully validated package.')]");
    private By approveButton = By.cssSelector("button[type='button'][class ='adb-button js-approve-button adb-toolbar--item adb-button__primary']");
    private By approvedPackage = By.xpath("//*[contains(text(),'Product version has been approved and is now read-only')]");
    private By managePlatform = By.cssSelector("a[class ='adb-local_alert--link'][href='#product-platforms/a6e7fa19-2d2c-44fa-9274-131f5ed84240']");

    //////Publish Product///////
    //private By publishButton = By.cssSelector("button[type='button'][class ='adb-button__primary adb-button__small js-publish_button']");
    private By publishButton = By.xpath("//button[@class='adb-button__primary adb-button__small js-publish_button']");
    private By addToMarket = By.xpath("//button[@class ='adb-button__primary buttonResponse']");
    //private By saveMarket = By.xpath("//button[@class ='sc-fUKxqW bKnBhH sc-bAtgIc bePPjr']");
    private By saveMarket = By.xpath("//button[@type='submit']");
    private By addedFeedbackInfo = By.xpath("//*[contains(text(),'You have successfully added')]");

    //////Remove Product///////
    private By searchInput = By.xpath("//input[@class='js-search-input adb-input_row--item_content adb-search_field--input adb-text__small']");
    private By searchIcon = By.xpath("//button[@class='adb-input_row--item_content adb-search_field--button adb-button__small']");
    private By productLink = By.xpath("//td[@class='product-name']");
    private By triggerMenu = By.xpath("//menu[@class='adb-js-context_menu adb-context_menu']//button[@class='adb-button adb-button__small adb-button__secret adb-context_menu--trigger adb-js-context_menu--trigger']");
    private By remove = By.xpath("//a[@class='adb-link__option adb-stack--item_content']//span[contains(text(),'Remove')]");
    private By removeProduct = By.xpath("//button[@class='adb-button__primary buttonResponse'][@id='posLabel']");
    private By removedFeedbackInfo = By.xpath("//*[contains(text(),'You successfully removed application')]");
    private By stagingCatalogLink = By.xpath("//a[@class='adb-link__nav adb-stack--item_content'][@href='#staging-products']");

    //////Unpublish Product///////
    private By unpublish = By.xpath("//button[@class='adb-button adb-button__neutral adb-button__small button-unpublish'][1]");
    private By confirmUnpublish = By.xpath("//button[@class='adb-button adb-toolbar--item adb-button__primary']");
    private By unpublishedFeedbackInfo = By.xpath("//*[contains(text(),'You have successfully unpublished')]");

    //////Delete Staging Product///////
    private By stagingProductLink = By.xpath("//a[@data-truncate='line']");
    private By deleteButton = By.xpath("//button[@class ='adb-button__danger adb-button__small'][contains(@name,'wrapper:contentPanel:productTable:tbody')]");
    private By confirmDelete = By.cssSelector("button[type='button'][class ='adb-button adb-toolbar--item adb-button__primary']");
    private By deleteFeedbackInfo = By.xpath("//*[contains(text(),'You have successfully deleted')]");
    private By txtAdditionalNotes = By.xpath("//*[@class='js-validation-notes-region']");
    private By linkAdditionalDetails = By.xpath("//a[@class='adb-local_alert--link' and text()='View additional details.']");
    private By btnClose = By.xpath("//button[@class='adb-button adb-toolbar--item adb-button__secret']");
    private By btnDelete = By.xpath("//button[@class='adb-button adb-button__danger']");
    private By txtDeleteProductVersion = By.xpath("//*[@class='adb-modal--header']");
    private By descDeleteProductVersion = By.xpath("//*[@class='adb-modal--content modal-content']");
    private By btnDeleteConfirmProduct = By.xpath("//*[@class='adb-button adb-toolbar--item adb-button__danger']");
    private By txtConfirmDelete = By.xpath("//*[@class='js-content']/p");
    private By txtInfoFeedBackPanel = By.xpath("//li[@class='feedbackPanelINFO']/span");
    private By btnEditStagingProduct = By.xpath("//*[@class='adb-button adb-button__neutral adb-button__small']");
    private By txtVerifoneDevice = By.xpath("//span[contains(text(),'Verifone Device')]");
    // private By txtVerifoneDevice = By.cssSelector("a[class ='adb-link__nav adb-stack--item_content adb-is-selected']");

    ////Data/////
    private String productName = BaseTest.envConfig.getCbaProductName();
    private String productVersionCode = getApplicationVersionCode;
    private String productVersionName = getApplicationVersion;
    String productDescription = "CBA Test App short description";
    String privacyPolicy = "https://www.commbank.com.au/security-privacy/general-security/privacy.html";
    String termsAndConditions = "https://www.samplestore.com/legal/terms_of_use_mobile";
    String feedBack = "Your listing info details have been saved.";
    String splash = "A Test App for CBA";
    String videoURL = "https://www.youtube.com/watch?v=jWFcGoGgnu4";
    String documentLink = "https://help.appdirect.com/appmarket/Default.htm#GettingStarted/gsg-intro.htm%3FTocPath%3DGetting%2520Started%7C_____0/?location%20=%20appmarket";
    String notificationURL = "https://verifoneausandbox.byappdirect.com/api/integration/v1/dummy/success";
    String feedBackInfo = "The integration configuration was saved successfully.";
    String headerInfo = "Manage Platform";
    String validationInfo = "Successfully validated package. ";
    String authenticationInfo = "The authentication configuration was saved successfully.";
    String addedInfo = "You have successfully added " + productName + " to your Production Catalog.";
    String removedInfo = "You successfully removed application " + productName + " from the Production Catalog. It is not visible in the marketplace, but it is still present in the Staging Catalog.";
    String unpublishedInfo = "You have successfully unpublished " + productName + ".";
    String oathKeyInfo = "A new OAuth 1.0 consumer was successfully created for your product.";
    String deleteVersion = "Delete Product Version";
    String msgDeleteProductVersion = "Product Version " + getApplicationName + "_" + getApplicationVersion + " has successfully been deleted";
    String infoFeedBackPanel = "You have successfully deleted " + productName;

    private static String generatedAppId = AndroidProjectOperationPage.androidProjectAppId;
    private static String userDir = BaseTest.envConfig.getAppsDirectoryPath();
    //private static String packagePath = userDir + File.separator + generatedAppId + "-" + getApplicationVersion + ".zip";
    private static String imagePath = userDir + File.separator + "apps" + File.separator + "image.jpg";

    public void createStagingProduct() {
        testLog.info("------------------------------------- Create Staging Catalog -------------------------------------");
        waitForLoader(appName);
        sendKeys(appName, productName);
        click(modelFree);
        click(createProductBtn);
    }

    public void listingInfoProduct() throws Exception {
        testLog.info("------------------------------------- Navigate to Listing Info and Create it-------------------------------------");
        waitForLoader(listingInfo);
        click(listingInfo);
        sendKeys(wordDescription, productDescription);
        sendKeys(description, productDescription);
        sendKeys(linkPP, privacyPolicy);
        sendKeys(linkTC, termsAndConditions);
        sendKeys(listingLogo, imagePath);
        sendKeys(profileLogo, imagePath);
        click(saveBtn);

        if (isExists(feedBackPanel, 18)) {
            ExpectedConditions.textToBe(feedBackPanel, feedBack);
            testLog.info("<b>" + driver.findElement(feedBackPanel).getText() + "</b>");
        }

        click(savePreviewBtn);
        click(addFeatureswBtn);
    }

    public void profileProduct() {
        testLog.info("------------------------------------- Navigate to Profile & Branding -------------------------------------");
        click(profile);
        sendKeys(splashTitle, splash);
        sendKeys(splashDescript, splash);
        sendKeys(overviewImg, imagePath);
        sendKeys(demoUrl, videoURL);
        sendKeys(docLink, documentLink);
        click(savePreviewBtn);
        click(addFeatureswBtn);
    }

    public void credentialsOath() {
        testLog.info("------------------------------------- Navigate to Integration -------------------------------------");
        waitForLoader(credentials);
        click(credentials);
        ExpectedConditions.presenceOfElementLocated(autorizationType);
        select(autorizationType, "shared");
        ExpectedConditions.elementToBeClickable(generateKey);
        click(generateKey);
        ExpectedConditions.elementToBeClickable(doneBtn);
        click(doneBtn);

        waitUntilPageLoad(oathFeedbackInfo);
        ExpectedConditions.textToBe(oathFeedbackInfo, oathKeyInfo);
        testLog.info("<b>" + getText(oathFeedbackInfo) + "</b>");
        //click(listingInfo);
        //click(saveBtn);
    }

    public void editIntegration() {
        testLog.info("<b>Info -> Integration : <u> Edit Integration </u></b> ");
        testLog.info("------------------------------------- Navigate to Integration -------------------------------------");
        click(integtation);
        sendKeys(subscrCreate, notificationURL);
        sendKeys(subscrChange, notificationURL);
        sendKeys(subscrCancel, notificationURL);
        sendKeys(subscrStatus, notificationURL);
        sendKeys(getSubscr, notificationURL);
        sendKeys(userAssign, notificationURL);
        sendKeys(userUnassign, notificationURL);
        click(saveBtn);
        //click(confirmSaveBtn);
        ExpectedConditions.textToBe(feedBackPanelInfo, feedBackInfo);
        testLog.info("<b>" + getText(feedBackPanelInfo) + "</b>");
    }

    public void editAuthentication() {
        testLog.info("------------------------------------- Navigate to Authentication -------------------------------------");
        waitForLoader(authentication);
        click(authentication);
        ExpectedConditions.elementToBeClickable(integrationSelect);
        select(integrationSelect, "BOOKMARK");
        sendKeys(landingURL, notificationURL);
        click(saveBut);
//        ExpectedConditions.textToBe(authenticationFeedbackInfo, authenticationInfo);
//        testLog.info(driver.findElement(authenticationFeedbackInfo).getText());

    }

    public void runPingTests() throws InterruptedException {
        testLog.info("------------------------------------- Navigate Integration : Run Ping Tests -------------------------------------");
        waitForLoader(pingTests);
        click(pingTests);
        waitForLoader(runAllTets);
        click(runAllTets);
        Thread.sleep(7000);
    }

    public void integrationReport() {
        testLog.info("<b>Info -> Integration : <u> Integration Report </u></b> ");
        testLog.info("------------------------------------- Navigate Integration Report -------------------------------------");
        waitForLoader(integrationReport);
        click(integrationReport);

        List<WebElement> verifyAPI = getWebElements(markComplete, 500, ExpectedConditions.presenceOfElementLocated(markComplete));
        for (WebElement elem : verifyAPI) {
            elem.click();
        }
    }

    public void addPlatform() {
        testLog.info("------------------------------------- Platforms & Versions -------------------------------------");
        waitForLoader(platforms);
        click(platforms);
        click(addPlatformBtn);
        click(deviceRadio);
        click(selectBtn);
        ExpectedConditions.textToBe(headerItem, headerInfo);
        click(addProductVersion);
    }

    public void productVersion(String app2Signed, String productVersionTitle) throws InterruptedException, AWTException {

        testLog.info("------------------------------------- Platforms : Add Product Version -------------------------------------");

        WebDriver driver = new CBAProducts().getDriver();
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(availableWindows.get(0));

        String packagePath = userDir + File.separator + "downloads" + File.separator + productVersionTitle + "-" + getApplicationVersion + ".zip";

        sendKeys(versionTitle, productVersionTitle);
        sendKeys(versionCode, productVersionCode);
        sendKeys(versionName, productVersionName);

        WebElement choosePackage = driver.findElement(chooseAsset);
        WebElement choosePackageIcon = driver.findElement(chooseIcon);
        WebElement choosePackageImage = driver.findElement(chooseImage);

        scrollToElement(chooseAsset);
        choosePackage.sendKeys(packagePath);
        waitUntilPageLoad(chooseIcon);

        scrollToElement(chooseIcon);
        choosePackageIcon.sendKeys(imagePath);
        waitUntilPageLoad(chooseImage);

        scrollToElement(chooseImage);
        choosePackageImage.sendKeys(imagePath);
        waitUntilPageLoad(chooseImage);

        click(mobileFamily);

        scrollToElement(saveButton);
        click(saveButton);

        WebElement productVersion = getWebElement(productVersionPanel, 500, ExpectedConditions.visibilityOfElementLocated(productVersionPanel));
        testLog.info(productVersion.getText());

        waitForLoader(managePlatform);
        scrollToElement(managePlatform);
        List<WebElement> getElement = driver.findElements(refreshPage);
        System.out.println("size of the element :" + getElement.size());

        if (getElement.size() != 0) {
            System.out.println("inside if refresh");
            click(refreshPage);
        }

        waitForLoader(validatedPackage);
        ExpectedConditions.textToBe(validatedPackage, validationInfo);
        testLog.info(driver.findElement(validatedPackage).getText());

        // Get -> Additional details after package validation
        testLog.info("<b>Info-> <u>Test : </u></b> : Verify signature of the package ");
        testLog.info("------------------------------------- Navigate to View Additional Details Window and Verify signature of the package -------------------------------------");
        getStatusOfPackageValidation(app2Signed);
        /*List<WebElement> eleAdditionalDetails = driver.findElements(linkAdditionalDetails);
        System.out.println("size of eleAdditionalDetails :" + eleAdditionalDetails.size());
        if (eleAdditionalDetails.size() != 0) {
            click(linkAdditionalDetails);
            waitUntilPageLoad(txtAdditionalNotes);
            scrollToElement(txtAdditionalNotes);

            // Verify -> Package is signed with V2 signature or not
            if (isContain(getText(txtAdditionalNotes), "appV2Signed : true")) {
                System.out.println("true");
                testLog.info("<b>Info-> <u>Additional Notes</u></b> :" + getText(txtAdditionalNotes));
            } else {
                System.out.println("false");
                testLog.info("<b>Info-> <u>Additional Notes</u></b> :" + getText(txtAdditionalNotes));
            }

            //close -> Package Validation Details
            click(btnClose);
        }*/


    }

    public void deleteProductVersion() {

        testLog.info("------------------------------------- Platforms : Delete Product Version -------------------------------------");

        click(btnDelete);
        waitUntilPageLoad(txtDeleteProductVersion);
        assertTextContains(getText(txtDeleteProductVersion), deleteVersion);
        testLog.info("<b>Info -> <u>" + getText(txtDeleteProductVersion) + " </u></b> : " + getText(descDeleteProductVersion));
        click(btnDeleteConfirmProduct);
        waitForLoader(headerItem);
        assertTextContains(msgDeleteProductVersion, getText(txtConfirmDelete));
        testLog.info("<b>Info -> <u> Delete Product : </u> " + getText(txtConfirmDelete) + "</b>");
    }

    public void approveProductVersion() {
        testLog.info("------------------------------------- Platforms : Approved Product Version -------------------------------------");
        click(approveButton);
        waitForLoader(approvedPackage);
        testLog.info(driver.findElement(approvedPackage).getText());
        click(managePlatform);
        //waitForLoader(publishButton);
    }

    public void publishProduct() {
        waitForLoader(publishButton);
        testLog.info("------------------------------------- Product : Publish Product -------------------------------------");

        scrollToElement(publishButton);
        click(publishButton);
        waitForLoader(addToMarket);
        click(addToMarket);
        waitForLoader(saveMarket);
        click(saveMarket);
        waitForLoader(addedFeedbackInfo);
        ExpectedConditions.textToBe(addedFeedbackInfo, addedInfo);
        testLog.info("<b>" + driver.findElement(addedFeedbackInfo).getText() + "/<b>");
    }

    public void removeProduct() {
        testLog.info("------------------------------------- Navigate to Production Catalog and remove product from it -------------------------------------");
        sendKeys(searchInput, productName);
        click(searchIcon);
        hoverAndClickOnElement(triggerMenu);

        hoverAndClickOnElement(remove);
        waitForLoader(removeProduct);
        click(removeProduct);
        waitForLoader(removedFeedbackInfo);
        ExpectedConditions.textToBe(removedFeedbackInfo, removedInfo);
        testLog.info("<b>Info -> <u>Remove Product</u> : " + driver.findElement(removedFeedbackInfo).getText() + "</b>");
        hoverAndClickOnElement(stagingCatalogLink);
    }

    public void unPublishProduct() {
        testLog.info("-------------------------------------  Staging Catalog : UnPublish Package -------------------------------------");
        waitForLoader(searchInput);
        sendKeys(searchInput, productName);
        click(searchIcon);
        hoverAndClickOnElement(unpublish);

        waitForLoader(confirmUnpublish);
        click(confirmUnpublish);
        waitForLoader(unpublishedFeedbackInfo);
        ExpectedConditions.textToBe(unpublishedFeedbackInfo, unpublishedInfo);
        testLog.info("<b>Info -> <u>UnPublish Product</u> : " + driver.findElement(unpublishedFeedbackInfo).getText() + "</b>");
    }

    public void deleteSatgingProduct() throws Exception {
        testLog.info("------------------------------------- Delete Staging Product " + productName + " -------------------------------------");

        waitForLoader(searchInput);
        sendKeys(searchInput, productName);
        click(searchIcon);
        waitUntilPageLoad(searchIcon);

        Thread.sleep(2000);
        List<WebElement> productList = driver.findElements(productLink);
        System.out.println("Size of product list :" + productList.size());

        if (productList.size() != 0) {
            testLog.info("<b>Product is available to delete.</b>");
            hoverAndClickOnElement(deleteButton);

            waitForLoader(confirmDelete);
            hoverAndClickOnElement(confirmDelete);
            waitForLoader(txtDeleteProductVersion);
            assertTextContains(infoFeedBackPanel, getText(txtInfoFeedBackPanel));
            testLog.info("<b>Info -> <u>Delete Staging Product</u> : " + driver.findElement(txtInfoFeedBackPanel).getText() + "</b>");
        } else {
            testLog.info("<b>Product is not available to delete.</b>");
        }
    }

    public void getStatusOfPackageValidation(String app2Signed) {
        List<WebElement> eleAdditionalDetails = driver.findElements(linkAdditionalDetails);
        System.out.println("size of eleAdditionalDetails :" + eleAdditionalDetails.size());
        if (eleAdditionalDetails.size() != 0) {
            click(linkAdditionalDetails);
            waitUntilPageLoad(txtDeleteProductVersion);
            scrollToElement(txtAdditionalNotes);
            // Verify -> Package is signed with V2 signature or not
            if (isContain(getText(txtAdditionalNotes), app2Signed)) {
                System.out.println("true");
                testLog.info("<b>Info -> <u>Success :</u></b> : Package validated successfully.");
                testLog.info("<b>Info -> <u>Additional Notes</u></b> :" + getText(txtAdditionalNotes));
            } else {
                System.out.println("false");
                testLog.info("<b>Info-> <u>Additional Notes</u></b> :" + getText(txtAdditionalNotes));
                Assert.fail("<b>Info-> <u>Error :</u></b> Package failed to validate.");
            }

            //close -> Package Validation Details
            click(btnClose);
        }
    }

    /**
     * This method delete the signed package after all operation
     * 25/10/2019
     *
     * @author : Prashant Lokhande
     */
    public void deleteV1SignedPackage(String productVersionTitle) {
        testLog.info("--------------------------------------------- Delete package from the user directory --------------------------------------------------- ");

        String packagePath = userDir + File.separator + "downloads" + File.separator + productVersionTitle + "-" + getApplicationVersion + ".zip";
        File deleteFilePath = new File(packagePath);
        if (isFileExists(deleteFilePath, 10)) {
            deleteFilePath.delete();
        }
    }

    /**
     * This method edit the product which is present in staging catalog
     * 31/10/2019
     *
     * @author : Prashant Lokhande
     */
    public void editStagingCatalogProduct() throws Exception {
        testLog.info("---------------------------------------- Update staging catalog product --------------------------------------------- ");
        click(stagingCatalogLink);

        waitForLoader(searchInput);
        sendKeys(searchInput, productName);
        click(searchIcon);
        waitUntilPageLoad(searchIcon);

        //verify app is available to edit. if not then create new product
        Thread.sleep(2000);
        List<WebElement> isAppPresent = driver.findElements(btnEditStagingProduct);
        System.out.println(" isAppPresent : " + isAppPresent.size());

        if (isAppPresent.size() == 0) {
            testLog.info("<b> Create Product </b>");
            click(btnCreateProduct);
            createStagingProduct();
            addPlatform();
        } else {
            testLog.info("<b> Edit Product </b>");
            click(btnEditStagingProduct);
            waitForLoader(platforms);

            scrollToElement(platforms);
            click(platforms);

            hoverAndClickOnElement(txtVerifoneDevice);

            waitForLoader(headerItem);
            ExpectedConditions.textToBe(headerItem, headerInfo);
            click(addProductVersion);
        }
    }

    public void clickStagingProduct() {
        click(stagingCatalogLink);
    }
}
