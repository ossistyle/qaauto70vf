package com.verifone.pages.mpPages;


import com.verifone.pages.BasePage;
import org.openqa.selenium.By;


public class ProductsTabStagingCatalogPage extends BasePage {

    private final static String url = "";
    private final static String title = "Staging Catalog";

    public ProductsTabStagingCatalogPage() {
        super(url, title);
    }

    private By CreateProductButton = By.xpath("//button[text()[contains(.,'Create Product')]]");


    //--------------------------------------------------------------------------
    public void clickBtnCreateProduct()  throws InterruptedException {
        waitForLoader(CreateProductButton);
        click(CreateProductButton);
    }
    //--------------------------------------------------------------------------


}
