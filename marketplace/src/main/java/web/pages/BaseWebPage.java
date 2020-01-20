package web.pages;

import static com.codeborne.selenide.Selenide.open;

public abstract class BaseWebPage {

    protected String url;

    public void navigate() {
        open(this.url);
    }

    protected BaseWebPage(String url) {
        this.url = url;
    }
}
