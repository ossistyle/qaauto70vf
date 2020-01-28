package utils.driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

import static com.codeborne.selenide.Browsers.CHROME;

public abstract class WebDriverManager {

    public static void downloadDriver(String browserName) {
        switch (browserName) {
            case CHROME:
                ChromeDriverManager.chromedriver().setup();
            default:
                FirefoxDriverManager.firefoxdriver().setup();
        }
    }
}