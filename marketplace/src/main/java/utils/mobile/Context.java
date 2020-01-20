package utils.mobile;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;

import java.util.Set;

public abstract class Context {

    public static String NATIVE = "NATIVE_APP";
    public static String WEBVIEW = "WEBVIEW_com.verifone.marketplace";

    /**
     * Switch to context
     * @param context String Context
     * @return Boolean Is switched
     */
    public static boolean switchTo(String context) {
        AppiumDriver<SelenideElement> driver = (AppiumDriver<SelenideElement>) WebDriverRunner.getWebDriver();
        Set<String> contextHandles = driver.getContextHandles();
        if (contextHandles.contains(context)) {
            driver.context(context);
            return true;
        } else {
            return false;
        }

    }

}
