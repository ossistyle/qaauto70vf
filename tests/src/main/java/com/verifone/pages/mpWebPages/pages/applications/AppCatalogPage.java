package com.verifone.pages.mpWebPages.pages.applications;

import com.verifone.pages.BasePage;
import com.verifone.pages.mpWebPages.components.MainMenu;
import com.verifone.pages.mpWebPages.components.applications.common.FilterPanel;
import com.verifone.pages.mpWebPages.components.applications.catalog.QuickView;
import com.verifone.pages.mpWebPages.components.applications.catalog.CardsViewTable;
import com.verifone.pages.mpWebPages.components.applications.catalog.GridViewTable;

public class AppCatalogPage extends BasePage {

    private final static String URL = "app-catalog";
    private final static String TITLE = " Application Catalog ";

    public MainMenu mainMenu;
    public FilterPanel filterPanel;
    public QuickView quickView;
    public CardsViewTable cardsViewTable;
    public GridViewTable gridViewTable;

    public AppCatalogPage() {
        super(URL, TITLE);
        this.mainMenu = new MainMenu();
        this.filterPanel = new FilterPanel();
        this.quickView = new QuickView();
        this.cardsViewTable = new CardsViewTable();
        this.gridViewTable = new GridViewTable();
    }
}
