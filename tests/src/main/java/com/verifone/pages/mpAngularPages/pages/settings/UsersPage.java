package com.verifone.pages.mpAngularPages.pages.settings;

import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;
import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;

public class UsersPage extends BasePage {

    private final static String URL = "settings/users";
    private final static String TITLE = "Marketplace";

    private List<SelenideElement> userCheckboxes = $$(byCssSelector("input[class*=check]"));
    private List<SelenideElement> userAvatars = $$(byCssSelector("figure[class*=avatar] img"));
    private List<SelenideElement> userNames = $$(byCssSelector(".media-content strong"));
    private List<SelenideElement> userEmails = $$(byCssSelector(".content p > span:last-child"));
    private List<SelenideElement> userRoles = $$(byCssSelector("div[class*=tags] span"));
    private List<SelenideElement> userActionButtons = $$(byCssSelector("button[aria-controls*=dropdown]"));

    /**
     * Click 'User avatar'
     * @param index User Avatars index
     */
    public void clickUserProfile(int index) {
        testLog.info(String.format("Click 'User Avatar' with %d index", index));
        userAvatars.get(index).click();
    }

    /**
     * Select user checkbox
     * @param index Checkbox index
     * @param selected Set checkbox selected
     */
    public void checkUser(int index, boolean selected) {
        testLog.info(String.format("Set %b in 'User checkbox' with %d index", selected, index));
        userCheckboxes.get(index).setSelected(selected);
    }

    /**
     * Get User avatars
     * @return List<SelenideElement> User Avatars
    */
    public List<SelenideElement> getUserAvatars() {
        return userAvatars;
    }

    /**
     * Get User names
     * @return List<SelenideElement> User Names
     */
    public List<SelenideElement> getUserNames() {
        return userNames;
    }

    /**
     * Get User emails
     * @return List<SelenideElement> User Emails
     */
    public List<SelenideElement> getUserEmails() {
        return userEmails;
    }

    /**
     * Get User roles
     * @return List<SelenideElement> User Roles
     */
    public List<SelenideElement> getUserRoles() {
        return userRoles;
    }

    /**
     * Click 'User actions' button
     * @param index Button index
     */
    public void clickSearch(int index) {
        testLog.info(String.format("Click 'User actions' button with %d index", index));
        userActionButtons.get(index).should(exist).click();
    }

    public UsersPage() {
        super(URL, TITLE);
    }
}
