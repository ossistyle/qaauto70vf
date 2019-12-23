package com.verifone.pages.mpAngularPages.pages;

import com.verifone.pages.BasePage;
import com.verifone.pages.mpAngularPages.components.MainMenu;

public class HomePage extends BasePage {

    public MainMenu mainMenu;

    private final static String URL = "/home";
    private final static String TITLE = "Marketplace";

    public HomePage() {
        super(URL, TITLE);
        this.mainMenu = new MainMenu();
    }

}
