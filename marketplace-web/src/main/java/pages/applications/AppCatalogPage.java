package pages.applications;


import components.MainMenu;
import components.applications.catalog.CardsViewTable;
import components.applications.catalog.GridViewTable;
import components.applications.catalog.QuickView;
import components.applications.common.FilterPanel;
import pages.BasePage;

public class AppCatalogPage extends BasePage {

    private final static String URL = "app-catalog";
    private final static String TITLE = " Application Catalog ";

    public MainMenu mainMenu;
    public FilterPanel filterPanel;
    public QuickView quickView;
    public CardsViewTable cardsViewTable;
    public GridViewTable gridViewTable;

    public AppCatalogPage() {
        super();
        this.mainMenu = new MainMenu();
        this.filterPanel = new FilterPanel();
        this.quickView = new QuickView();
        this.cardsViewTable = new CardsViewTable();
        this.gridViewTable = new GridViewTable();
    }
}
