package com.verifone.infra.wd;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

import static com.verifone.infra.wd.Browser.*;

public abstract class WDManager {

    public static void downloadDriver(String browserName) {
        switch (browserName) {
            case FIREFOX:
                FirefoxDriverManager.firefoxdriver().setup();
            default:
                ChromeDriverManager.chromedriver().setup();
        }
    }

}
