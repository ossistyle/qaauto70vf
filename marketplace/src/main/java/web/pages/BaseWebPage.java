package web.pages;

import com.codeborne.selenide.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.open;

public abstract class BaseWebPage {

    protected String url;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void navigate() {
        logger.info(String.format("Navigate to: %s/%s", Configuration.baseUrl, this.url));
        open(this.url);
    }

    protected BaseWebPage() {}
}
