package com.verifone.pages.selenide.pages;

import com.verifone.pages.selenide.components.MainMenu;

public class HomePage extends Page {

    public MainMenu mainMenu;

    public HomePage() {
        this.mainMenu = new MainMenu();
    }

}
