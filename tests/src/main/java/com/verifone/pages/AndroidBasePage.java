package com.verifone.pages;

import com.verifone.pages.mpMobilePages.components.Header;
import com.verifone.pages.mpMobilePages.components.MainMenu;

public class AndroidBasePage {

    public Header header;
    public MainMenu mainMenu;

    public AndroidBasePage() {
        this.header = new Header();
        this.mainMenu = new MainMenu();
    }
}
