package com.verifone.pages.mpAngularPages.pages.settings;

import com.codeborne.selenide.SelenideElement;
import com.verifone.pages.BasePage;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class UsersPage extends BasePage {

    private final static String URL = "settings/users";
    private final static String TITLE = "Marketplace";

    private List<SelenideElement> userCheckboxes = $$(byCssSelector("input[class*=check]"));
    private List<SelenideElement> userAvatars = $$(byCssSelector("figure[class*=avatar] img"));
    private List<SelenideElement> userNames = $$(byCssSelector(".content strong"));
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
     * Get User avatar
     * @return SelenideElement User Avatar
    */
    public SelenideElement getUserAvatar(int index) {
        testLog.info(String.format("Get avatar for user with %d index", index));
        return userAvatars.get(index);
    }

    /**
     * Get User name
     * @return SelenideElement User Name
     */
    public SelenideElement getUserName(int index) {
        testLog.info(String.format("Get username for user with %d index", index));
        return userNames.get(index).should(exist);
    }

    /**
     * Get User email
     * @return SelenideElement User Email
     */
    public SelenideElement getUserEmail(int index) {
        testLog.info(String.format("Get email for user with %d index", index));
        return userEmails.get(index).should(exist);
    }

    /**
     * Get User role
     * @return SelenideElement User Role
     */
    public SelenideElement getUserRole(int index) {
        testLog.info(String.format("Get role for user with %d index", index));
        return userRoles.get(index).should(exist);
    }

    /**
     * Click 'User actions' button
     * @param index Button index
     */
    public void clickUserActions(int index) {
        testLog.info(String.format("Click 'User actions' button with %d index", index));
        userActionButtons.get(index).should(exist).click();
    }

    public UsersPage() {
        super(URL, TITLE);
    }
}
