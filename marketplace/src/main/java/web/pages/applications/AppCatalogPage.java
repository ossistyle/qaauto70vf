package web.pages.applications;


import web.components.MainMenu;
import web.components.applications.catalog.CardsViewTable;
import web.components.applications.catalog.GridViewTable;
import web.components.applications.catalog.QuickView;
import web.components.applications.common.FilterPanel;
import web.pages.BasePage;

public class AppCatalogPage extends BasePage {

    private final static String URL = "app-catalog";
    private final static String TITLE = " Application Catalog ";

    public MainMenu mainMenu;
    public FilterPanel filterPanel;
    public QuickView quickView;
    public CardsViewTable cardsViewTable;
    public GridViewTable gridViewTable;

    public AppCatalogPage() {
        super(URL);
        this.mainMenu = new MainMenu();
        this.filterPanel = new FilterPanel();
        this.quickView = new QuickView();
        this.cardsViewTable = new CardsViewTable();
        this.gridViewTable = new GridViewTable();
    }
}
