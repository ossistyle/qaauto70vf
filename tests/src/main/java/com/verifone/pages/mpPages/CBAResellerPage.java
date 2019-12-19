package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;

import static com.verifone.utils.Assertions.assertTextContains;

public class CBAResellerPage extends BasePage {

    private final static String title = "Reseller - User";
    private final static String url = "";

    private By linkManage = By.xpath("//a[@class='ad-component--link ad-component_dropdown--trigger manage-link']");
    private By selectReseller = By.xpath("//a[@class='ad-component--link '][@href='/reseller']");
    private By selectCompanies = By.xpath("//a[@class='adb-link__nav adb-stack--item_content '][@href='./companies']");
    private By searchBox = By.xpath("//*[@type='search']");
    private By searchBtn = By.xpath("//button[@class='adb-input_row--item_content adb-search_field--button adb-button__small']//i[@class='adb-icon__search']");
    private By selectSearchedCompanies = By.xpath("//table[@class='adb-table__actionable']/tbody/tr[1]");
    private By manageApplicationBtn = By.xpath("//button[@class='Button-ktwawd-0 bqscOf']");
    private By selectOption = By.xpath("//div[starts-with(@id,'tooltip')]//ul/li");
    private By txtResult = By.xpath("//*[@class='adb-local_alert--content']");
    private By listOfMerchants = By.xpath("//ul[@class='custom-primary__nav--items']/li[1]/a");
    private By selectMerchant = By.xpath("//a[contains(text(),'MerchantForAutomation')]");
    private By linkMyApps = By.xpath("//a[@id = 'myapps']");

    private String msgJoinCompany = "You have joined this company.";
    private String msgLeaveCompany = "You have left this user's company.";
    private String txtJoinCompany = "Join Company";
    private String txtLeaveCompany = "Leave Company";


    public CBAResellerPage() {
        super(url, title);
    }

    /**
     * This method describe the actions which are require to reach companies section.
     * 01/11/2019
     *
     * @author : Prashant Lokhande
     */
    public void selectCompanies() {
        testLog.info("--------------------------- Navigate to Reseller ------------------------------");
        click(linkManage);
        click(selectReseller);
    }

    /**
     * This method describe the actions to search the company.
     * 01/11/2019
     *
     * @author : Prashant Lokhande
     */
    public void searchCompaniesToManage() {
        testLog.info("--------------------------- Navigate to Companies ------------------------------");
        waitForLoader(selectCompanies);
        click(selectCompanies);

        testLog.info("--------------------------- Select Searched Companies ------------------------------");
        waitForLoader(searchBox);
        scrollToElement(selectSearchedCompanies);
        click(selectSearchedCompanies);
    }

    /**
     * This method describe the actions to join the company.
     * 01/11/2019
     *
     * @author : Prashant Lokhande
     */
    public void manageCompanyOperations() {
        testLog.info("--------------------------- Manage Companies ------------------------------");
        waitForLoader(manageApplicationBtn);
        click(manageApplicationBtn);

        //Do the operation depend on the text present in the manage application dropdown list.
        String txtOption = getText(selectOption);
        if (txtOption.contains(txtJoinCompany)) {
            testLog.info("--------------------------- Join Company ------------------------------");
            click(selectOption);
            assertTextContains(getText(txtResult), msgJoinCompany);
            testLog.info("<b>Info -> Message</b> : " + getText(txtResult));
        } else {
            testLog.info("--------------------------- Leave Company ------------------------------");
            click(selectOption);
            testLog.info("<b>Info -> Message</b> : " + msgLeaveCompany);
        }
    }

    /**
     * Method Reseller Purchase : This method describe all actions which is require to select merchant
     * 01/11/2019
     *
     * @author: Prashant Lokhande
     */
    public void selectMerchantFromTheList() throws Exception {
        testLog.info("--------------------------- Select Merchant ------------------------------");
        click(listOfMerchants);
        click(selectMerchant);
        Thread.sleep(4000);
    }

    /**
     * This method describe the action on My Apps option
     * 01/11/2019
     *
     * @author : Prashant Lokhande
     */
    public void selectMyAppsOption() {
        testLog.info("--------------------------- Navigate to My Apps  ------------------------------");
        click(linkMyApps);
        waitForLoader(linkMyApps);
    }
}
