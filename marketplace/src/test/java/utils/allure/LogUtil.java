package utils.allure;

import io.qameta.allure.Step;

public final class LogUtil {

    public LogUtil() {
    }

    @Step("{0}")
    public static void reportMessage(final String message){
        //intentionally empty
    }
}
