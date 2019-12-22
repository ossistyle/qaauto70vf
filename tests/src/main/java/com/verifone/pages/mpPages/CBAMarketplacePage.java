package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CBAMarketplacePage extends BasePage {
    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "Verifone Australia Sandbox | Verifone Australia";

    //private By marketPlace = By.xpath("//*[@id=\"header\"]/header/div/nav/ul/li[3]");
    private By marketPlace = By.xpath("//*[@id='shop']");
    private By findApp = By.xpath("//input[@class='adb-input_row--item_content adb-search_field--input adb-text__small'][@placeholder='Find Applications']");
    private By searchBtn = By.xpath("//*[@class='adb-input_row--item_content adb-search_field--button adb-button adb-button__neutral adb-button__small'][@type='button']");

    private By listing = By.cssSelector("a[class='listing-row-content-title js-title--link']");
    private By continueBtn = By.cssSelector("button[class='adb-button__primary continue buttonResponse continue-to-next']");
    private By placeOrderBtn = By.cssSelector("button[id='placeOrder']");
    private By goToMyAppsBtn = By.xpath("//button[@class='adb-button adb-toolbar--item adb-button__primary']");
    private By myAppBtn = By.xpath("//button[@class='adb-button__emphasis adb-button__large buttonResponse']");
    private By tryFree = By.xpath("//button[@class='adb-button adb-toolbar--item adb-button__emphasis adb-button__large'][1]");

    //private By btnStartFreeTrial = By.xpath("//*[@class='adb-button adb-toolbar--item adb-button__emphasis adb-button__large'] //*[text() = 'Start a Free Trial']");
    //private By btnContinue = By.xpath("//*[@class='adb-button__primary continue buttonResponse continue-to-next']//*[text()='Continue']");
    //private By btnPlaceOrder = By.xpath("//*[@id='placeOrder']//*[text()='Place Order']");
    //private By btnBuyNow = By.xpath("//*[@type='button'] //*[text()='Buy Now']");
    //private By txtAppAlreadyPurchase = By.xpath("//*[@class = 'adb-caption adb-profile_header--caption js-header-caption js-scroll-hide']");
    private By btnManage = By.xpath("//button[@class='adb-button adb-toolbar--item adb-button__neutral adb-button__large'][1]");
    private By btnRemoveApp = By.xpath("//*[@id = 'cancel']");
    private By btnYes = By.xpath("//*[@id = 'posLabel']");
    private By txtManageApp = By.xpath("//p[@class='adb-caption adb-profile_header--caption js-header-caption js-scroll-hide']");

    private By btnManageApp = By.xpath("//*[@id='return']");
    private By btnAssignApp = By.xpath("//*[@class = 'adb-button adb-button__primary adb-toolbar--item']");
    private By appToUsers = By.xpath("//*[contains(@aria-label,'appToUsers')]");
    private By feedBack = By.xpath("//*[@id='orderReceipt']/child::div/child::p");
    private By agreeToTermsCheckbox = By.xpath("//*[@id='agreeToTermsCheckbox']");
    private By clickOnSettingsIcon = By.xpath("//button[@class='adb-button adb-button__neutral adb-context_menu--trigger adb-js-context_menu--trigger']");

    private List<WebElement> listingApps;

    public CBAMarketplacePage() {
        super(url, title);
    }

    public void searchForApp(String appName) {
        testLog.info("--------------------------- Navigate to Marketplace and Search App ------------------------------");
        System.out.println(appName);
        click(marketPlace);
        sendKeys(findApp, appName);
        click(searchBtn);
    }

    public void veryfyListingApps() {
        testLog.info("--------------------------- Verify App ------------------------------");
        listingApps = getWebElements(listing, 500, ExpectedConditions.presenceOfElementLocated(listing));
        int appsNumber = listingApps.size();
        testLog.info(appsNumber + " apps in listing");

        for (WebElement listingApp : listingApps) {

            testLog.info(listingApp.getText() + " in listing");

        }
    }

    public void buyFreeApp() {
        testLog.info("--------------------------- By Free App ------------------------------");
        click(listing);
        ExpectedConditions.visibilityOfElementLocated(tryFree);
        click(tryFree);
        click(goToMyAppsBtn);
    }

    public void buyFreeBundle() throws Exception {
        hoverAndClickOnElement(tryFree);
        hoverAndClickOnElement(continueBtn);
        if (isExists(agreeToTermsCheckbox, 30))
            click(agreeToTermsCheckbox);
        hoverAndClickOnElement(placeOrderBtn);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hoverAndClickOnElement(myAppBtn);
    }

    /**
     * Method : This method described the application is already purchased or not
     *
     * @author: Prashant Lokhande
     */

    public void isAppPurchased(String appName) throws Exception {
        testLog.info("--------------------------- Navigate to Manage Application ------------------------------");
        click(listing);
        Thread.sleep(2000);

        ExpectedConditions.elementToBeClickable(btnManage);
        listingApps = driver.findElements(btnManage);
        System.out.println("size of apps :" + listingApps);
        if (listingApps.size() != 0) {
            testLog.info("--------------------------- Application Purchased ------------------------------");
            click(btnManage);

            testLog.info("--------------------------- Remove Purchased Application ------------------------------");
            Thread.sleep(2000);
            listingApps = driver.findElements(clickOnSettingsIcon);
            System.out.println("remove button size :" + listingApps.size());
            if (listingApps.size() != 0) {
                hoverAndClickOnElement(clickOnSettingsIcon);
            }
            click(btnRemoveApp);
            click(btnYes);

        } else {
            testLog.info("--------------------------- Application doesn't purchase ------------------------------");
        }
    }

    /**
     * Method : This method described all the actions and elements to buy the One Time Pay App
     *
     * @author: Prashant Lokhande
     */

    public void buyOneTimeApp() throws Exception {

        /* Select One Time App. */
        click(listing);

        WebDriverWait element = new WebDriverWait(driver, 18);

        /* Select Buy Now button. */
        click(tryFree);

        Thread.sleep(2000);
        /* verify the flow of app purchase */
        List<WebElement> btnVal = driver.findElements(continueBtn);
        System.out.println("continue btn" + btnVal.size());
        if (btnVal.size() == 0) {
            System.out.println("Go to My Apps...");
            testLog.info("--------------------------- Go to My Apps  ------------------------------");
            click(goToMyAppsBtn);
        } else {
            System.out.println(" continue button ...");

            testLog.info("--------------------------- Create Order  ------------------------------");
            /* Locate the Continue button and click on it. */
            element.until(ExpectedConditions.visibilityOfElementLocated(continueBtn));
            scrollToElement(continueBtn);
            click(continueBtn);

            testLog.info("--------------------------- Confirm Order  ------------------------------");
            /* Locate the Place Order button and click on it. */
            element.until(ExpectedConditions.visibilityOfElementLocated(placeOrderBtn));
            scrollToElement(placeOrderBtn);

            /* Select checkbox if present to accept the terms */
            listingApps = driver.findElements(agreeToTermsCheckbox);
            if (listingApps.size() != 0) {
                testLog.info("--------------------------- Select Agreement Checkbox  ------------------------------");
                click(agreeToTermsCheckbox);
            }

            testLog.info("--------------------------- Place Order  ------------------------------");
            click(placeOrderBtn);

            //ExpectedConditions.visibilityOfElementLocated(feedBack);
            testLog.info(getText(feedBack));
            /* Locate the Manage App button and click on it. */
            element.until(ExpectedConditions.visibilityOfElementLocated(btnManageApp));
            scrollToElement(btnManageApp);
            click(btnManageApp);

            /* Assign App to User */
            ExpectedConditions.visibilityOfElementLocated(btnAssignApp);
            click(btnAssignApp);

            element.until(ExpectedConditions.visibilityOfElementLocated(appToUsers));
            scrollToElement(appToUsers);
            Thread.sleep(5000);
        }
    }

    public static void buyBundle(String appName) throws Exception {
        CBAMarketplacePage market = PageFactory.getCBAMarketplace();
        market.searchForApp(appName);
        market.veryfyListingApps();
        market.isAppPurchased(appName);
        market.buyFreeBundle();
    }

    public void clickMarketplaceBtn() {
        click(marketPlace);
    }

    /* Select Buy Now button. */
    public void clickOnBuyProductBtn() {
        testLog.info("--------------------------- Click on Buy option  ------------------------------");
        waitUntilPageLoad(tryFree);
        click(tryFree);
    }

    /* Locate the Continue button and click on it. */
    public void clickOnContinueBtn() {
        testLog.info("--------------------------- Click on Continue option  ------------------------------");
        waitUntilPageLoad(continueBtn);
        scrollToElement(continueBtn);
        click(continueBtn);
    }

    // Agreement info should be visible
    public boolean checkAgreementInfoVisible() {
        waitUntilPageLoad(placeOrderBtn);
        listingApps = driver.findElements(agreeToTermsCheckbox);
        if (listingApps.size() != 0) {
            testLog.info("--------------------------- Agreement details available  ------------------------------");
            return true;
        }
        return false;
    }

    // Agreement info should not be visible
    public boolean checkAgreementInfoInvisible() {
        waitUntilPageLoad(placeOrderBtn);
        listingApps = driver.findElements(agreeToTermsCheckbox);
        if (listingApps.size() == 0) {
            testLog.info("--------------------------- Agreement details not available  ------------------------------");
            return true;
        }
        return false;
    }

}
