package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;

public class ProductsTabBundlePage extends BasePage {

    private final static String url = "";
    private final static String title = "";
    private String deleteBtn = "(//a[contains(text(),'Delete Bundle')])";
    private By bundleTbl = By.xpath("//*[@class='adb-styled adb-container']");
    private By approveDeleteBtn = By.xpath("//span[text()='Yes']");
    private By addBundle = By.xpath("//button[text()[contains(.,'Add Bundle')]]");
    private By approveBCreationScr = By.xpath("//*[text()='Bundle Progress']");
    private By btnListingInformation = By.xpath("//a[text()='Listing Information']");
    private By headerListingInformation = By.xpath("//h1[contains(text(),'Listing Information')]");
    private By nameInput = By.xpath("//*[@name='name']");

    public ProductsTabBundlePage() {
        super(url, title);
    }

    public int getTblRowBundle(String name) throws InterruptedException {
        return getRowNumberFromTable(bundleTbl, name);
    }

    public void clickMenuEditBundle(int row) {
        String a = "/html/body/div[3]/div[3]/div/div[2]/div/div[3]/div[1]/div[2]/table/tbody/tr[" + row + "]/td[4]/div/menu/button";
        By menuMenuEditBundleLoc = By.xpath(a);
        click(menuMenuEditBundleLoc);
    }

    public void clickMenuDeleteBundle(int row){
        By removeSpecificBundleBtn = By.xpath(deleteBtn+ "[1]");
        click(removeSpecificBundleBtn);
        click(approveDeleteBtn);
    }

    public void addBundle(){
        click(addBundle);
        waitUntilPageLoad(approveBCreationScr);
    }

    public void clickListingInformation(){
        click(btnListingInformation);
        waitUntilPageLoad(headerListingInformation);
    }

    public void fillTheFields(String bundleName,String bundleDescription,String bundleLongDescription){

        //clearTextBoxValue(nameInput);
        setTextBoxText(nameInput,"Autocreated1",30);

    }

}
