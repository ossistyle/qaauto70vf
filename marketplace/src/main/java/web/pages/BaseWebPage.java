package web.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.open;

public abstract class BaseWebPage {

    protected String url;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void navigate() {
        open(this.url);
    }

    protected BaseWebPage(String url) {
        this.url = url;
    }
}
