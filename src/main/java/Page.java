import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Class with shared logic for pages.
 */
public abstract class Page {

    private final WebDriver driver;

    Page(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Switch to desired tab.
     *
     * @param n tab starts from 0.
     */
    public void switchToTab(int n) {
        List<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(n));
    }
}
