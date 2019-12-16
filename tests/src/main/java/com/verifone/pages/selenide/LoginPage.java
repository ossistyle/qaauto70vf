package com.verifone.pages.selenide;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends Page {

    private SelenideElement loginField = $(byId("username"));
    private SelenideElement passwordField = $(byId("ipassword"));

}
