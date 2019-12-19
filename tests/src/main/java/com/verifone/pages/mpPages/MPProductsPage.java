package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.appUtils.MPUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.verifone.utils.appUtils.MPUtils.*;


public class MPProductsPage extends BasePage {
    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "Verifone Australia Sandbox | Verifone Australia";

    private static String getApplicationVersion = BaseTest.envConfig.getApplicationVersion();
    private static String getApplicationVersionCode = BaseTest.envConfig.getApplicationVersionCode();
    private static String productVersionTitle = "Missing Data";

    private By createProductBtn = By.xpath("//button[contains(text(),'Create Product')][@class='go-to-import-link adb-button__small']");
    private By listingInfo = By.linkText("Listing Info");
    private By linkPP = By.cssSelector("input[type='url'][name='privacyUrlBorder:privacyUrlBorder_body:privacyUrlField']");
    private By linkTC = By.cssSelector("input[type='url'][name='termsUrlBorder:termsUrlBorder_body:termsUrlField']");
    private By listingLogo = By.cssSelector("input[type='file'][name='uploadListingLogoContainer:fileUploadListingLogo']");
    private By profileLogo = By.cssSelector("input[type='file'][name='uploadProfileLogoContainer:fileUploadProfileLogo']");
    private By saveBtn = By.cssSelector("button[class ='adb-button__primary adb-toolbar--item'][name='save']");
    private By feedBackPanelError = By.xpath("//*[@id='edit-branding-panel']/form/div[2]/ul/li/span");
    private By profile = By.linkText("Profile");
    private By splashTitle = By.cssSelector("input[type='text'][name='slogansAndDescriptionContainer:sloganFieldBorder:sloganFieldBorder_body:sloganField']");
    private By splashDescript = By.cssSelector("textarea[name='slogansAndDescriptionContainer:descriptionFieldBorder:descriptionFieldBorder_body:descriptionField']");
    private By overviewImg = By.cssSelector("input[type='file'][name='uploaderOverviewImageContainer:fileUploadOverviewLogo']");
    private By demoUrl = By.cssSelector("input[type='url'][name='demoUrl']");
    private By docLink = By.cssSelector("input[type='url'][name='helpLinkField']");
    private By leavePage = By.id("leave");
    private By feedBackPanelErrorProfile = By.xpath("//*[@id='edit-overview-panel']/form/div[2]/ul/li/span");
    private By edition = By.xpath("//*[@id='editionsAndPricing']/div/a");
    private By freeEdition = By.xpath("//*[@id='editionsAndPricing']/div/ul/li/a");
    private By editionCode = By.name("editionCodeBorder:editionCodeBorder_body:editionCode");
    private By feedBackPanelErrorEdition = By.xpath("//*[@id='edit-pricing-panel']/form/div[2]/ul/li/span");
    private By savePlan = By.xpath("//*[@id='edit-pricing-panel']/form/menu/menu/button[1]");
    private By authentication = By.linkText("Edit Authentication");
    private By integrationSelect = By.xpath("//select[@aria-label='select'][@name='authenticationMethod'][@class='adb-js-dropdown-select ']");
    private By saveAuth = By.xpath("//button[@class='adb-button adb-toolbar--item adb-button__primary'][@aria-label='save']");
    private By errorAuth = By.xpath("//span[@class='adb-label--error js-error-message']");
    private By platforms = By.xpath("//a[contains(text(),'Platforms')]");
    private By addPlatformBtn = By.cssSelector("a[class ='add adb-button adb-button__neutral adb-button__small'][href='#product-platforms']");
    private By deviceRadio = By.cssSelector("input[type='radio'][value='[object Object]']");
    private By selectBtn = By.cssSelector("button[type='button'][class='adb-button adb-toolbar--item adb-button__primary']");
    private By headerItem = By.cssSelector("h1[class='adb-header--item']");
    private By addProductVersion = By.cssSelector("button[type='button'][class='adb-button']");

    private By saveButton = By.cssSelector("button[type='button'][class ='adb-button js-save-button adb-toolbar--item adb-button__primary'][name='save']");
    private By mobileFamily = By.cssSelector("input[type='checkbox'][value='CarbonMobile']");
    private By versionTitle = By.cssSelector("input[type='text'][name='title']");
    private By versionCode = By.cssSelector("input[type='text'][name='versionCode']");
    private By versionName = By.cssSelector("input[type='text'][name='versionName']");
    private By errorProductVersion = By.xpath("//*[@class='js-content']/p");

    public MPProductsPage() {
        super(url, title);
    }

    /**
     * Click on Create Product option
     *
     * @author : Prashant Lokhande
     */
    public void clickOnCreateProduct() {
        click(createProductBtn);
    }

    /**
     * Click on Listing Info option
     *
     * @author : Prashant Lokhande
     */
    public void clickOnListingInfo() {
        waitForLoader(listingInfo);
        click(listingInfo);
    }

    /**
     * This method describe to add the non mandatory details in Listing info
     *
     * @author : Prashant Lokhande
     */
    public void setListingInfoText() {
        //add legal details
        sendKeys(linkPP, MPUtils.privacyPolicy);
        sendKeys(linkTC, MPUtils.termsAndConditions);

        sendKeys(listingLogo, MPUtils.imagePath);
        sendKeys(profileLogo, MPUtils.imagePath);
    }

    /**
     * This method describe to save details of page
     *
     * @author : Prashant Lokhande
     */
    public void clickOnSaveBtn() {
        click(saveBtn);
    }

    /**
     * @param errorMsg display if mandatory field is missing
     * @return
     */
    public boolean compareErrorMessage(String errorMsg) {
        String feedBackError = getText(feedBackPanelError);
        testLog.info("------ Listing info - ErrorMsg : " + feedBackError + " ------------------------------");
        return assertRowContains(errorMsg, feedBackError);
    }

    /**
     * This method describe to click on Profile option
     *
     * @author : Prashant Lokhande
     */

    public void clickOnProfile() {
        click(profile);
    }

    /**
     * This method describe to add the non mandatory details in Profile
     *
     * @author : Prashant Lokhande
     */
    public void setProfileProduct() {
        sendKeys(overviewImg, MPUtils.imagePath);
        sendKeys(demoUrl, MPUtils.videoURL);
        sendKeys(docLink, MPUtils.documentLink);
    }

    /**
     * This method describe selection of option Leave this Page on confirm navigation window
     *
     * @author :Prashant Lokhande
     */
    public void clickOnLeaveThisPage() {
        click(leavePage);
    }

    /**
     * @param errorMsg display if mandatory field is missing
     * @return
     */
    public boolean profileErrorMessage(String errorMsg) {
        String feedBackError = getText(feedBackPanelErrorProfile);
        testLog.info("------ Profile - Error Msg : " + feedBackError + " ------------------------------");
        return assertRowContains(errorMsg, feedBackError);
    }

    /**
     * Click on Edition option
     *
     * @author : Prashant Lokhande
     */
    public void clickEdition() {
        click(edition);
    }

    /**
     * Click on free edition option
     *
     * @author : Prashant Lokhande
     */
    public void clickOnFreeEdition() {
        click(freeEdition);
    }

    /**
     * remove text from Edition Code text box
     *
     * @author : Prashant Lokhande
     */
    public void removeEditionCode() {
        sendKeys(editionCode, "");
    }

    /**
     * @param errorMsg display if mandatory field is missing
     * @return
     */
    public boolean editionErrorMessage(String errorMsg) {
        String feedBackError = getText(feedBackPanelErrorEdition);
        testLog.info("------ Edition - Error Msg : " + feedBackError + " ------------------------------");
        return assertRowContains(errorMsg, feedBackError);
    }

    /**
     * Click on Save Plan option
     *
     * @author : Prashant Lokhande
     */
    public void clickOnSavePlanBtn() {
        click(savePlan);
    }

    /**
     * Click on Edit Authentication
     *
     * @author : Prashant Lokhande
     */
    public void clickOnEditAuthentication() {
        click(authentication);
    }

    /**
     * Click on method dropdown
     *
     * @author : Prashant Lokhande
     */
    public void clickOnAuthenticationMethod() {
        ExpectedConditions.elementToBeClickable(integrationSelect);
        select(integrationSelect, "BOOKMARK");
    }

    /**
     * Click on save button to save authentication button
     *
     * @author : Prashant Lokhande
     */
    public void clickOnSaveAuthBtn() {
        click(saveAuth);
    }

    /**
     * @param errorMsg compare error message in authentication
     * @author : Prashant Lokhande
     */
    public boolean authenticationErrorMessage(String errorMsg) {
        waitUntilPageLoad(errorAuth);
        String feedBackError = getText(errorAuth);
        testLog.info("---------------------- Authentication - Error Msg : " + feedBackError + " ------------------------------");
        return assertRowContains(errorMsg, feedBackError);
    }

    /**
     * Click on Platforms option
     *
     * @author Prashant Lokhande
     */

    public void clickOnPlatforms() {
        click(platforms);
    }

    /**
     * Click on Add Platform option
     *
     * @author Prashant Lokhande
     */

    public void clickOnAddPlatform() {
        click(addPlatformBtn);
    }

    /**
     * Select Verifone Device option
     *
     * @author Prashant Lokhande
     */
    public void selectVerifoneDevice() {
        click(deviceRadio);
    }

    /**
     * Click on Select button
     *
     * @author Prashant Lokande
     */
    public void clickOnSelectBtn() {
        click(selectBtn);
    }

    /**
     * Click on Add Product Version
     *
     * @author Prashant Lokhande
     */
    public void clickOnAddProductVersion() {
        ExpectedConditions.textToBe(headerItem, headerInfo);
        click(addProductVersion);
    }

    /**
     * Add product related details in the platform
     *
     * @author Prashant Lokhande
     */
    public void setDetailsInPlatform() {
        sendKeys(versionTitle, productVersionTitle);
        sendKeys(versionCode, getApplicationVersionCode);
        sendKeys(versionName, getApplicationVersion);
        click(mobileFamily);
    }

    /**
     * Click on button to save platfrom details
     *
     * @author Prashant Lokhande
     */

    public void clickOnSavePlatformBtn() {
        click(saveButton);
    }

    /**
     * @param errorMsg compare error message in authentication
     * @author : Prashant Lokhande
     */
    public boolean productVersionErrorMessage(String errorMsg) {
        waitUntilPageLoad(errorProductVersion);
        String feedBackError = getText(errorProductVersion);
        testLog.info("---------------------- Product Version - Error Msg : " + feedBackError + " ------------------------------");
        return assertRowContains(errorMsg, feedBackError);
    }

}
