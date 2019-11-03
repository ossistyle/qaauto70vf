package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import java.awt.*;

public class ProductsTabBundlePage extends BasePage {

    private final static String url = "";
    private final static String title = "";
    private String deleteBtn = "(//a[contains(text(),'Delete Bundle')])";
    private String selectAppBtn = "(//span[text()='Select'])";
    private String checkoutTypeBtn = "(//*[@class='adb-toggle--control'])";
    public static By bundleTbl = By.xpath("//*[@class='adb-styled adb-container']");
    public static By appTbl = By.xpath("//*[@class='adb-container']");
    public By msgConfirmationLoc = By.xpath("(//*[@class='adb-local_alert--content'])[2]");
    public By msgConformationPublish = By.xpath("(//*[@class='FormField__FormLabel-s4rx2c-2 BMYeb Label__LabelWrapper-sc-1llzn56-0 jNHiuP'])[3]");
    public By confirmString = By.xpath("//a[text()='Documentation Center']");
    private By approveDeleteBtn = By.xpath("//span[text()='Yes']");
    private By addBundle = By.xpath("//button[text()[contains(.,'Add Bundle')]]");
    private By approveBCreationScr = By.xpath("//*[text()='Bundle Progress']");
    private By ListingInformationBtn = By.xpath("//a[text()='Listing Information']");
    private By applicationsBtn = By.xpath("//a[text()='Applications']");
    private By CancelRestrictionBtn = By.xpath("//a[text()='Cancellation Restriction']");
    private By headerListingInformation = By.xpath("//h1[contains(text(),'Listing Information')]");
    private By headerCancellationRestr = By.xpath("//h1[contains(text(),'Cancellation Restriction')]");
    private By headerApplications = By.xpath("//h2[contains(text(),'Add Apps to Bundle')]");
    private By nameInput = By.xpath("//*[@name='name']");
    private By descriptionInput = By.xpath("//*[@name='shortDescription']");
    private By longDescriptionInput = By.xpath("//*[@name='overview']");
    private By chooseImageBtn = By.xpath("(//*[@class='adb-upload--button_text'])[2]");
    private By saveBtn = By.xpath("//*[@class='adb-button__primary adb-toolbar--item']");
    private By saveAppScreenBtn = By.xpath("//*[@class='adb-button__primary adb-toolbar--item js-save']");
    private By approveAddAppBtn = By.xpath("//span[text()='Save']");
    private By publishBtn = By.xpath("//*[text()='Publish']");
    private By confirmPublish = By.xpath("//*[text()='Publish & Add to Marketplace']");
    private By nextApp = By.xpath("//*[@class='adb-icon__angle_right adb-pagination--button adb-pagination--button__next ']");
    private String workingDir = System.getProperty("user.dir");
    private String imagePath = workingDir + "\\src\\test\\resources\\apps\\image.jpg";


    public ProductsTabBundlePage() {
        super(url, title);
    }

    public int getTblRowBundle(String name, By tablePath) throws InterruptedException {
        return getRowNumberFromTable(tablePath, name);
    }

    public void clickMenuEditBundle(int row) {
        String a = "/html/body/div[3]/div[3]/div/div[2]/div/div[3]/div[1]/div[2]/table/tbody/tr[" + row + "]/td[4]/div/menu/button";
        By menuMenuEditBundleLoc = By.xpath(a);
        click(menuMenuEditBundleLoc);
    }

    public void clickMenuDeleteBundle(int row) {
        By removeSpecificBundleBtn = By.xpath(deleteBtn + "["+row+"]");
        click(removeSpecificBundleBtn);
        click(approveDeleteBtn);
        try{
            Thread.sleep(6000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addBundle() {
        click(addBundle);
        waitUntilPageLoad(approveBCreationScr);
    }

    public void clickListingInformation() {
        click(ListingInformationBtn);
        waitUntilPageLoad(headerListingInformation);
    }

    public void clickApplications() throws InterruptedException {
        try{
            hoverAndClickOnElement(applicationsBtn);
            waitUntilPageLoad(headerApplications);}
        catch(Exception e){
            click(applicationsBtn);
            waitUntilPageLoad(headerApplications);
        }
    }

    public void clickCancelRestrictionBtn() {
        click(CancelRestrictionBtn);
        waitUntilPageLoad(headerCancellationRestr);
    }

    public void fieldsListingInfo(String bundleName, String bundleDescription, String bundleLongDescription) throws AWTException {
        setTextBoxText(nameInput, bundleName, 30);
        setTextBoxText(descriptionInput, bundleDescription, 30);
        setTextBoxText(longDescriptionInput, bundleLongDescription, 30);
        hoverAndClickOnElement(chooseImageBtn);
        fileUpload(imagePath);
        click(saveBtn);
        waitUntilPageLoad(headerListingInformation);
    }

    public void addApp(String name) throws InterruptedException {
        int appRow = getTblRowBundle(name, appTbl);
        By selectSpecificApp = By.xpath(selectAppBtn + "[" + appRow + "]");
        click(selectSpecificApp);
        hoverAndClickOnElement(approveAddAppBtn);
        click(saveAppScreenBtn);
    }

    public void chooseRestrictionType(String type) {

            if(type.equals("partial")){
                click(saveAppScreenBtn);}
            else if(type.equals("full")){
                By Partial = By.xpath(checkoutTypeBtn+"["+1+"]");
                By notPartial = By.xpath(checkoutTypeBtn+"["+2+"]");
                click(Partial);
                click(notPartial);
                click(saveAppScreenBtn);}
        }

    public void clickPublish(){
        hoverAndClickOnElement(publishBtn);
        hoverAndClickOnElement(confirmPublish);
        waitUntilPageLoad(msgConformationPublish);
        hoverAndClickOnElement(approveAddAppBtn);
        waitUntilPageLoad(confirmString);
    }

    public void clickNextApp(){
        hoverAndClickOnElement(nextApp);
    }
}
