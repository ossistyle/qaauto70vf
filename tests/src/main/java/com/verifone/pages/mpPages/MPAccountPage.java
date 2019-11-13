package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on MP Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class MPAccountPage extends BasePage {

    private final static String url = "";
    private final static String title = "A Smart Way to Manage Your Business\n" +
            "with Vertical Focused Bundle";

//    private By tabUserLoc = By.xpath("//[@class='users-li']");
    private By tabUserLoc = By.xpath("//a[contains(text(),'Users')]");

    public MPAccountPage() {
        super(url, title);
    }

//--------------------------------------------------------------------------
    /**
     * Method: Click on User Tab.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickTabUser () throws InterruptedException {

        click(tabUserLoc);
    }
//--------------------------------------------------------------------------
}

