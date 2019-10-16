package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on EO Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class EditSegmentGroupPage extends BasePage {

    private final static String url = "";
    private final static String title = "Create Segment Groups";

    private By fldSegmentGroupNameLoc = By.name("name");
    private By editSegmentFolderName = By.name("pencil");
    private By titleCreateSegmentGroupLoc = By.xpath("//h1[text()='Segment Group 1']");
    private By titleSegmentLoc = By.xpath("//div[text()='Segment folder']");
    private By btnSaveLoc = By.xpath("//span[text()='Save']");
    private By titleFilters = By.xpath("//*[text()='Build with filters']");
    private By titleManual = By.xpath("//*[text()='Build manually']");
    private By btnCreateSegmentLoc = By.xpath("//span[text()='Create segment']");
    private By createButtonListLoc = By.xpath("//*[@class='sc-iIHSe cQEaAU sc-ileJJU bOLSpV']");
    private By tblSegmentsLoc = By.xpath("//*[@data-auto-container='company-groups-segment-list:table']");
    private By msgConfirmationLoc = By.xpath("//*[@class='adb-local_alert--content']");
    private By editNameSaveButton = By.xpath("//span[text()='Save']");

    private List<WebElement> createButtonList;

    public EditSegmentGroupPage() {
        super(url, title);
    }

//--------------------------------------------------------------------------
    /**
     * Method: Input Segment Group Name.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputSegmentGroupName(String SName)  throws Exception {
        sendKeys(fldSegmentGroupNameLoc, SName);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Get Segment Group Name.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String GetSegmentGroupName()  throws Exception {
        return getText(titleCreateSegmentGroupLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Update Segment Group Name.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void UpdateSegmentGroupName(String SName)  throws Exception {
        click(editSegmentFolderName);
        sendKeys(fldSegmentGroupNameLoc, SName);
        click(editNameSaveButton);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Title 'Create Segment Groups' exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean existsTitleCreateSegmentGroup () throws Exception {
        return isExists(titleCreateSegmentGroupLoc, 20);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Title 'Segment folder' exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean existsTitleSegment () throws Exception {
        return isExists(titleSegmentLoc, 20);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click on Save button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnSave () throws InterruptedException {

        click(btnSaveLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Get Message Text.
     * Return Text as string
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String msgConfirmationText () throws InterruptedException {
        return getText(msgConfirmationLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Button 'Create Segment' exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean existsBtnCreateSegment () throws Exception {
        return isExists(btnCreateSegmentLoc, 20);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click on Create Segment button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnCreateSegment () {
        click(btnCreateSegmentLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Get Segment table content as Text.
     * Return String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getTblSegments() throws InterruptedException {
        System.out.println(getTextFromTable(tblSegmentsLoc));
        return getTextFromTable(tblSegmentsLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Search for Segment by Name.
     * Return row number
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public int getTblRowSegments(String name) throws InterruptedException {
        System.out.println(getRowNumberFromTable(tblSegmentsLoc, name));
        return getRowNumberFromTable(tblSegmentsLoc, name);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click on Segment Menu Trigger.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickMenuTriggerSegment (int row) throws InterruptedException {
        String a = "/div/div[2]/div/div[2]/div[3]/div[7]/div[2]/div/div/div/table/tbody/tr[" + row + "]/td[3]/div/menu";
        By menuTriggerSegmentLoc = By.xpath("//*[@id=\"main\"]"+a);
        click(menuTriggerSegmentLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click on Context Segment Menu: Edit.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickMenuContextEditSegment (String ind) throws InterruptedException {
        String a = "(//a[text()='Edit'])";
        By menuContextEditSegmentLoc = By.xpath(a+"["+ind+"]");

        click(menuContextEditSegmentLoc);
    }
//--------------------------------------------------------------------------
    public List<WebElement> getCreateBtnList (){
        createButtonList = getWebElements(btnCreateSegmentLoc, 500, ExpectedConditions.presenceOfElementLocated(btnCreateSegmentLoc));
        return createButtonList;
    }
//--------------------------------------------------------------------------
    public boolean existsFilterBuild () throws Exception {
        return isExists(titleFilters, 20);
    }
//--------------------------------------------------------------------------
    public boolean existsManualBuild () throws Exception {
        return isExists(titleManual, 20);
    }
//--------------------------------------------------------------------------
    public void createSegmentManual(){
        List<WebElement> list = getCreateBtnList();
        list.get(1).click();
    }
//--------------------------------------------------------------------------
    public void clickOnManualSegment(){
        click(titleManual);
    }
//--------------------------------------------------------------------------
}
