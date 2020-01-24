package web.merchant.pages.applications;


import web.merchant.components.MainMenu;
import web.merchant.components.applications.catalog.CardsViewTable;
import web.merchant.components.applications.catalog.GridViewTable;
import web.merchant.components.applications.catalog.QuickView;
import web.merchant.components.applications.common.FilterPanel;
import web.BaseWebPage;

public class AppCatalogPage extends BaseWebPage {

    private final String url = "app-catalog";

    public MainMenu mainMenu;
    public FilterPanel filterPanel;
    public QuickView quickView;
    public CardsViewTable cardsViewTable;
    public GridViewTable gridViewTable;

    public AppCatalogPage() {
        super();
        super.url = this.url;
        this.mainMenu = new MainMenu();
        this.filterPanel = new FilterPanel();
        this.quickView = new QuickView();
        this.cardsViewTable = new CardsViewTable();
        this.gridViewTable = new GridViewTable();
    }
}
