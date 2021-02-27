import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TVOnlinePage {

    public static final String TITLE = "Яндекс.Эфир";

    public static final By PURCHASES = By.cssSelector("a[href*=\"purchases\"]");

    private final WebDriver driver;

    public TVOnlinePage(WebDriver driver) {
        this.driver = driver;

        new WebDriverWait(driver, 15).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        if (!TITLE.equals(driver.getTitle())) {
            throw new IllegalStateException("This is not TVOnline page");
        }
    }

    public void waitPage(){

    }

}
