package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.appUtils.MPUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.verifone.tests.steps.mpPortal.Steps.createAssignUser;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class CBAAccountPage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "Dashboard | Verifone Australia";

    private static String appName = BaseTest.envConfig.getAppName();
    private By account = By.xpath("//*[@id='account']");
    private By manageApps = By.xpath("//*[@class='adb-link__nav adb-stack--item_content js-count'][@href='/account/apps']");
    private By mySearch = By.xpath("//input[@class='js-search-input adb-input_row--item_content adb-search_field--input adb-text__small']");
    private By iconSearch = By.xpath("//button[@class='adb-input_row--item_content adb-search_field--button adb-button__small']");
    private By allertContent = By.xpath("(//*[@class='adb-local_alert--content'])[3]");
    private By manage = By.xpath("//button[@class='adb-button adb-button__neutral adb-js-context_menu--trigger adb-context_menu--trigger']");
    private By cancelSubscr = By.xpath("//a[@class='cancelSubscriptionLink adb-link__option adb-stack--item_content']");
    private By cancelCheckboxBundle = By.xpath("//input[@id='checkbox-cancel-bundle']");
    private By cancelBundle = By.xpath("//span[text()='Cancel Bundle']");
    private By feedBack = By.xpath("//*[contains(text(),'successfully')]");

    private By search = By.cssSelector("input[class='js-search-input adb-input_row--item_content adb-search_field--input adb-text__small']");
    private By searchBtn = By.xpath("//*[@id=\"appsTable\"]/div/div[1]/div[1]/menu/div[2]/div/div[2]/button");
    private By yesBtn = By.xpath("//button[@class='adb-button__primary buttonResponse']");
    private By linkManage = By.xpath("//*[text()='Manage']");
    private By linkMyApps = By.xpath("//a[@id = 'myapps']");
    private By subscriptionsEditUpdate = By.xpath("//button[@data-auto='subscriptionsEditUpdate']");
    private By otpApp = By.xpath("//label[text()='One Time Pay - Custom']");
    //private By linkManage = By.xpath("//*[@class='custom-primary__nav--items']//li[1]//a[1]");
    public static String jobCreatedOnUnsubscription;

    String textSuccess;

    public CBAAccountPage() {
        super(url, title);
    }

    private List<WebElement> purchasedApps;


    public void cancelSubscribsion(String appName) throws InterruptedException {
        textSuccess = "Your subscription to " + appName + " was successfully removed.";
        testLog.info("--------------------------- Navigate to Applications ------------------------------");
        /* Click is only available when Manage option is visible in menu bar*/
        waitUntilPageLoad(linkMyApps);
        purchasedApps = driver.findElements(linkManage);
        if (purchasedApps.size() != 0)
            click(linkManage);

        click(account);
        click(manageApps);
        sendKeys(mySearch, appName);
        click(iconSearch);
        //ExpectedConditions.presenceOfElementLocated(manage);
        hoverAndClickOnElement(manage);

        testLog.info("--------------------------- Cancel Subscription ------------------------------");
        hoverAndClickOnElement(cancelSubscr);
        ExpectedConditions.presenceOfElementLocated(yesBtn);
        click(yesBtn);
        ExpectedConditions.textToBe(feedBack, textSuccess);

        //get the date as per GMT+03:00 time zone
        jobCreatedOnUnsubscription = MPUtils.getDownloadScheduleTime();

        System.out.println("GMT time UnSubscription : " + jobCreatedOnUnsubscription);
        testLog.info("----- App UnSubscription created date & Time : " + jobCreatedOnUnsubscription + " -----");

        Thread.sleep(7000);
        testLog.info(getText(feedBack));
        //jobCreatedOnUnsubscription = dateFormat.format(new Date());
    }

    public void cancelBundleSubsucribsion(String appName) throws InterruptedException {
        textSuccess = "Your subscription to " + appName + " was successfully removed.";

        /* Click is only available when Manage option is visible in menu bar*/
        waitUntilPageLoad(linkMyApps);
        purchasedApps = driver.findElements(linkManage);
        if (purchasedApps.size() != 0)
            click(linkManage);

        click(account);
        click(manageApps);
        sendKeys(mySearch, appName);
        click(iconSearch);
        //ExpectedConditions.presenceOfElementLocated(manage);
        hoverAndClickOnElement(manage);
        hoverAndClickOnElement(cancelSubscr);


        WebElement element = getWebElement(allertContent, 30, ExpectedConditions.presenceOfElementLocated(allertContent));
        List<WebElement> tr = element.findElements(By.className("app-tile"));
        for (WebElement w : tr) {
            System.out.println(w.getText());
            testLog.info("App that are part of bundle: " + w);
        }

        hoverAndClickOnElement(cancelCheckboxBundle);
        hoverAndClickOnElement(cancelBundle);

        //get the date as per GMT+03:00 time zone
        jobCreatedOnUnsubscription = MPUtils.getDownloadScheduleTime();

        System.out.println("GMT time UnSubscription : " + jobCreatedOnUnsubscription);
        testLog.info("----- App UnSubscription created date & Time : " + jobCreatedOnUnsubscription + " -----");

        Thread.sleep(7000);
        testLog.info(getText(feedBack));

    }

    /**
     * Click on Account option
     *
     * @author Prashant Lokhande
     */
    public void clickOnAccountOption() {
        testLog.info("------------------------------ Navigate to Account ------------------------------");
        click(account);
    }

    /**
     * Method : Click on Manage Apps option
     *
     * @author Prashant Lokhande
     */
    public void clickOnManageApps() {
        testLog.info("------------------------------ Navigate to Manage Apps ------------------------------");
        click(manageApps);
    }

    /**
     * Method : Click on search app in Manage App section
     * Manage App
     *
     * @author Prashant Lokhande
     */
    public void searchAppInManageApps(String appName) {
        sendKeys(mySearch, appName);
        click(iconSearch);
    }

    /**
     * Method: Click on Update Subscription
     *
     * @author Prashant Lokhande
     */
    public void clickOnUpdateSubscriptionBtn() {
        testLog.info("------------------------------ Navigate to Update Subscription ------------------------------");
        waitUntilPageLoad(subscriptionsEditUpdate);
        click(subscriptionsEditUpdate);
    }

    /**
     * Method : Check edition added in update subscription section
     * Update Subscription
     *
     * @author Prashant Lokhande
     */
    public boolean checkAddedEditionInManageApps() {
        waitUntilPageLoad(otpApp);
        List<WebElement> editionApp = driver.findElements(otpApp);
        System.out.println("edition app size" + editionApp.size());
        return editionApp.size() != 0;
    }

}