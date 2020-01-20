package web.pages;

import static com.codeborne.selenide.Selenide.open;

public abstract class BasePage {

    protected String url;

    public void navigate() {
        open(this.url);
    }

    protected BasePage(String url) {
        this.url = url;
    }
}
