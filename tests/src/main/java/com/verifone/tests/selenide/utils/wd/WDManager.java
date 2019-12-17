package com.verifone.tests.selenide.utils.wd;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

import static com.verifone.tests.selenide.utils.wd.Browser.FIREFOX;

public abstract class WDManager {

    public static void downloadDriver() {
        switch (Configuration.browser) {
            case FIREFOX:
                FirefoxDriverManager.firefoxdriver().setup();
            default:
                ChromeDriverManager.chromedriver().setup();
        }
    }

}
