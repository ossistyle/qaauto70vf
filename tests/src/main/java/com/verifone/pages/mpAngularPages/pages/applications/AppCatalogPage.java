package com.verifone.pages.mpAngularPages.pages.applications;

import com.verifone.pages.BasePage;
import com.verifone.pages.mpAngularPages.components.MainMenu;
import com.verifone.pages.mpAngularPages.components.applications.common.FilterPanel;
import com.verifone.pages.mpAngularPages.components.applications.catalog.QuickView;
import com.verifone.pages.mpAngularPages.components.applications.catalog.CardsViewTable;
import com.verifone.pages.mpAngularPages.components.applications.catalog.ListViewTable;

public class AppCatalogPage extends BasePage {

    private final static String URL = "app-catalog";
    private final static String TITLE = " Application Catalog ";

    public MainMenu mainMenu;
    public FilterPanel filterPanel;
    public QuickView quickView;
    public CardsViewTable cardsView;
    public ListViewTable listView;

    public AppCatalogPage() {
        super(URL, TITLE);
        this.mainMenu = new MainMenu();
        this.filterPanel = new FilterPanel();
        this.quickView = new QuickView();
        this.cardsView = new CardsViewTable();
        this.listView = new ListViewTable();
    }
}
