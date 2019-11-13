package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on MP Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class MPUsersPage extends BasePage {

    private final static String url = "";
    private final static String title = "A Smart Way to Manage Your Business\n" +
            "with Vertical Focused Bundle";

    private By btnCreateGroupLoc = By.xpath("//*[@id=\"mainRegion\"]/div/div/div[2]/div[1]/div/menu");
    private By btnSubmitLoc = By.xpath("//*[@class='adb-button adb-button__emphasis']");
//    private By btnCreateGroupLoc = By.xpath("//a[contains(text(),'Create Group']");
    private By secGroupsLoc = By.xpath("//a[contains(text(),'Groups')]");
    private By fldGroupNameLoc = By.name("name");
    private By fldGroupDescriptionLoc = By.id("c202_description");
    private By btnCreateGroupDlgLoc = By.xpath("//*[@class='adb-button adb-button__primary adb-toolbar--item']");

//    private By elUserLoc = By.xpath("//h4[contains(text(),'Merchant Automation')]");
    private By elUserLoc = By.xpath("//*[@class='adb-table--row_status adb-icon__add']");
    private By elUserRemoveLoc = By.xpath("//*[@class='adb-table--row_status adb-icon__remove']");

    private By msgGroupMembershipLoc = By.xpath("//*[@class='js-content']");

    public MPUsersPage() {
        super(url, title);
    }

//--------------------------------------------------------------------------
    /**
     * Method: Click on Groups section Tab.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickSecGroups () throws InterruptedException {

        click(secGroupsLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Create Group button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnCreateGroups () throws InterruptedException {

        click(btnCreateGroupLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Create Group button on dialog window.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnDlgCreateGroups () throws InterruptedException {

        click(btnCreateGroupDlgLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Input Group Name.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputGroupName(String GName)  throws Exception {
        sendKeys(fldGroupNameLoc, GName);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Input Group Description.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputGroupDescription(String GDescription)  throws Exception {
        click(fldGroupDescriptionLoc);
        sendKeys(fldGroupDescriptionLoc, GDescription);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on + button near User.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickUserAddGroup () throws InterruptedException {
        scrollToElement(elUserLoc);
        click(elUserLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on - button near User.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickUserRemoveGroup () throws InterruptedException {

        click(elUserRemoveLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Submit button on dialog window.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickSubmitButton () throws InterruptedException {

        click(btnSubmitLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Message Text.
     * Return Text as string
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String msgGroupMembershipText () throws InterruptedException {
        return getText(msgGroupMembershipLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: click Gear Group button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickGearGroupButton (String groupName) throws InterruptedException {
        String loc = "//a[contains(text(), " + '"' + groupName + '"' + ")]";
        By GroupsLoc = By.xpath(loc);

        click(GroupsLoc);
    }
//--------------------------------------------------------------------------
}



