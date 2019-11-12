package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on MP Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class MPGroupsPage extends BasePage {

    private final static String url = "";
    private final static String title = "A Smart Way to Manage Your Business\n" +
            "with Vertical Focused Bundle";

    private By titleGroupLoc = By.xpath("//*[@class='adb-summary--title']");
    private By menuSettingsLoc = By.xpath("//a[contains(@class, 'adb-stack--item') and text() = 'Settings']");
    private By menuDeleteLoc = By.xpath("//a[contains(@class, 'adb-stack--item') and text() = 'Delete']");
    private By gearGrpoupLoc = By.xpath("//*[@class='adb-context_menu']");

    private By btnSubmitLoc = By.xpath("//*[@class='adb-button adb-toolbar--item adb-button__primary']");

    public MPGroupsPage() {
        super(url, title);
    }

//--------------------------------------------------------------------------
    /**
     * Method: Click on Groups Settings menu.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickMenuSettings () throws InterruptedException {

        click(menuSettingsLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Groups Delete menu.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickMenuDelete () throws InterruptedException {

        click(menuDeleteLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Group Title.
     * Return Text as string
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getGroupTitle () throws InterruptedException {
        return getText(titleGroupLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: click Gear Group button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickGearGroupButton () throws InterruptedException {

        click(gearGrpoupLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: click Submit button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickSubmitButton () throws InterruptedException {

        click(btnSubmitLoc);
    }
//--------------------------------------------------------------------------
}



