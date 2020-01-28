package mobile;

import mobile.components.Header;
import mobile.components.MainMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MobilePage {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Header header;
    public MainMenu mainMenu;

    public MobilePage() {
        this.header = new Header();
        this.mainMenu = new MainMenu();
    }
}
