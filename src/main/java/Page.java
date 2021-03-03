import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public abstract class Page {

    private final WebDriver driver;

    Page (WebDriver driver){
        this.driver = driver;
    }

    public void switchToTab(int n) {
        List<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(n));
    }
}
