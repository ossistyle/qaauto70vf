package mobile;

import mobile.components.Header;
import mobile.components.MainMenu;

public abstract class MobilePage {

    public Header header;
    public MainMenu mainMenu;

    public MobilePage() {
        this.header = new Header();
        this.mainMenu = new MainMenu();
    }
}
