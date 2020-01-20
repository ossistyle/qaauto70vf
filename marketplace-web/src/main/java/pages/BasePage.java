package pages;

import static com.codeborne.selenide.Selenide.open;

public abstract class BasePage {

    protected String URL;

    public void navigate() {
        open(this.URL);
    }

}
