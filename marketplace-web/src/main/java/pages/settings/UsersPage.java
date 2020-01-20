package pages.settings;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import pages.BasePage;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$$;

public class UsersPage extends BasePage {

    private final static String URL = "settings/users";
    private final static String TITLE = "Marketplace";

    private ElementsCollection userCheckboxes = $$("input[class*=check]");
    private ElementsCollection userAvatars = $$("figure[class*=avatar] img");
    private ElementsCollection userNames = $$(".content strong");
    private ElementsCollection userEmails = $$(".content p > span:last-child");
    private ElementsCollection userRoles = $$("div[class*=tags] span");
    private ElementsCollection userActionButtons = $$("button[aria-controls*=dropdown]");

    @Step("Click 'User avatar' for user with index")
    public void clickUserProfile(int index) {
        userAvatars.get(index).click();
    }

    @Step("Check 'User checkbox' for user with index")
    public void checkUser(int index, boolean selected) {
        userCheckboxes.get(index).setSelected(selected);
    }

    @Step("Get user avatars")
    public ElementsCollection getUserAvatars() {
        return userAvatars;
    }

    @Step("Get user names")
    public ElementsCollection getUserNames() {
        return userNames;
    }

    @Step("Get user emails")
    public ElementsCollection getUserEmails() {
        return userEmails;
    }

    @Step("Get user roles")
    public ElementsCollection getUserRoles() {
        return userRoles;
    }

    @Step("Click 'User actions' button for user with index")
    public void clickUserActions(int index) {
        userActionButtons.get(index).should(exist).click();
    }

    public UsersPage() {
        super();
    }
}
