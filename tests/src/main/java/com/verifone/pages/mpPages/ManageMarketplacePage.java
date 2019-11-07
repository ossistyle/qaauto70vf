package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on EO Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class ManageMarketplacePage extends BasePage {

    private final static String url = "";
    private final static String title = "Sign Up with Verifone Identity Server";
    private By productTab = By.xpath("//table[@class='adb-item-table']");
    private By reportsTab = By.xpath("//tr[contains(@class, 'TableContainer')]");
    private By tabProductLoc = By.xpath("//*[@id='subnav-header']/div/ul[1]/li[3]/a");
    private By tabReportsLoc = By.xpath("//*[text()='Reports']");

    public ManageMarketplacePage() {
        super(url, title);
    }

//--------------------------------------------------------------------------
    /**
     * Method: Click on Product tab.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickTabProduct () throws InterruptedException {
        click(tabProductLoc);
        waitUntilPageLoad(productTab);
    }
//--------------------------------------------------------------------------
    public void clickTabReports () throws InterruptedException {
        click(tabReportsLoc);
        try{
            Thread.sleep(4000);
        }
        catch(Exception e){
            System.out.println("Oops " + e);
        }
        waitUntilPageLoad(reportsTab);
}
//--------------------------------------------------------------------------
}

