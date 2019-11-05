package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.pages.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CBADashboard extends BasePage {

    private final static String url = "";
    private final static String title = "";

    public CBADashboard() {
        super(url, title);
    }

    ////Manage MarketPlace//////
    private By manage = By.cssSelector("a[class ='ad-component--link ad-component_dropdown--trigger manage-link']");
    private By marketPlace = By.cssSelector(".ad-component_list-item.ad-component_list-item--channel>.ad-component--link ");

    /////MarketPlace Products/////
    private By welcomeBack = By.xpath("//div[@class='wm-close-button walkme-x-button']");
    private By productTour = By.xpath("//div[@class='walkme-arrow walkme-override walkme-css-reset']");
    private By products = By.xpath("//*[@id=\"subnav-header\"]/div/ul[1]/li[3]/a");
    //private By products = By.xpath("//a[contains(text(),'Create Product')][@href='./products']");
    private By stagingCatalog = By.xpath("//a[@class='adb-link__nav adb-stack--item_content'][@href='#staging-products']");
    private By createProductBtn = By.xpath("//button[contains(text(),'Create Product')][@class='go-to-import-link adb-button__small']");

    public void manageMarketpace() throws Exception {
        testLog.info("<b>Info -> <u> Go to the Product sections & create product </u></b>");
        click(manage);
        click(marketPlace);
        //ExpectedConditions.presenceOfElementLocated(welcomeBack);
        //click(productTour);
        //click(welcomeBack);
        //click(welcomeBack);
        ExpectedConditions.presenceOfElementLocated(products);
        click(products);
        ExpectedConditions.presenceOfElementLocated(stagingCatalog);
        click(stagingCatalog);

        //delete product if it is already exists.
        CBAProducts isProductExist = PageFactory.getCBAProducts();
        isProductExist.deleteSatgingProduct();

        ExpectedConditions.elementToBeClickable(createProductBtn);
        click(createProductBtn);
    }

    public void manageMarketPlaceProducts() {
        click(manage);
        click(marketPlace);
        click(products);
    }
}
