package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on EO Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class ProductsTabProductCatalogPage extends BasePage {

    String removeButton="(//*[text()='Remove'])";
    private final static String url = "";
    private final static String title = "Segment Groups";
    private By fldSearchLoc = By.xpath("//*[@placeholder='Search']");
    private By btnSearchProductLoc = By.xpath("//*[@class='adb-icon__search']");
    private By btnConfigProductLoc = By.xpath("//*[@class='adb-js-context_menu adb-context_menu']");
    private By menuEditMarketplaceSettingsLoc = By.xpath("//span[text()='Edit Marketplace Settings']");
    private By tblProductLoc = By.xpath("//*[@class='adb-styled adb-container']");
    private By approveRemoveBtn = By.xpath("//span[text()='Remove Product']");
    private By btnClickOnLastPage = By.xpath("//*[@class='adb-icon__angle_double_right adb-pagination--button adb-pagination--button__last']");


    public ProductsTabProductCatalogPage() {
        super(url, title);
    }

//--------------------------------------------------------------------------
    /**
     * Method: Input Product.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputSearchProduct(String SName)  throws Exception {
        sendKeysNoClear(fldSearchLoc, SName);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click Search.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnSearchProduct()  throws InterruptedException {
        click(btnSearchProductLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click Config Product.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnConfigProduct()  throws InterruptedException {
        click(btnConfigProductLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click Edit Marketplace Settings menu.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickMenuEditMarketplaceSettings()  throws InterruptedException {
        click(menuEditMarketplaceSettingsLoc);
    }

//--------------------------------------------------------------------------
    public Boolean verifyProductExist(By name) throws Exception {
       return isExists(name,20);
    }
//--------------------------------------------------------------------------
    public void removeProduct(By name) throws Exception {
     isExists(name,20);
}
//--------------------------------------------------------------------------
    public int getTblRowProduct(String name) throws InterruptedException {
        return getRowNumberFromTable(tblProductLoc, name);
    }

    public void clickLastPage(){
        try{
            hoverAndClickOnElement(btnClickOnLastPage);}
        catch(Exception e){
            click(btnClickOnLastPage);
        }
    }

    public void clickMenuEditProduct(int row) {
        String a = "html/body/div[3]/div[3]/div/div[2]/div/div[5]/div[1]/div[2]/table/tbody/tr[" + row + "]/td[4]/div/menu/button/i";
        By menuMenuEditProductLoc = By.xpath(a);
        click(menuMenuEditProductLoc);
    }

    public void clickMenuRemoveProduct(int row){
        By removeSpecificProductBtn = By.xpath(removeButton+ "[" + row + "]");
        click(removeSpecificProductBtn);
        click(approveRemoveBtn);
    }

    //need to be on page that has the specific bundle
    public void unpublishBundleIfExist(String bundleName) throws InterruptedException {
        int actualRow = getTblRowProduct(bundleName);
        if(actualRow!=0) {
            while (actualRow != 0) {
                clickMenuEditProduct(actualRow);
                clickMenuRemoveProduct(actualRow);
                actualRow = getTblRowProduct(bundleName);
            }
        }
    }

}

