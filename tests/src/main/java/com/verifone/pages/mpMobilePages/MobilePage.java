package com.verifone.pages.mpMobilePages;

import com.verifone.pages.BasePage;
import com.verifone.pages.mpMobilePages.components.Header;
import com.verifone.pages.mpMobilePages.components.MainMenu;

public abstract class MobilePage extends BasePage {

    private static final String URL = null;
    private static final String TITLE = "Ionic App";

    public Header header;
    public MainMenu mainMenu;

    public MobilePage() {
        super(URL, TITLE);
        this.header = new Header();
        this.mainMenu = new MainMenu();
    }
}
