import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PurchasesPage {

    private final WebDriver driver;

    public static final String TITLE = "Мои покупки";

    public static final By EMPTY_SCREEN_TITLE = By.cssSelector("div[class*=\"EmptyScreen Screen-Empty\"] div[class*=\"EmptyScreen-Title\"]");

    public static final By EMPTY_SCREEN_SUBTITLE = By.cssSelector("div[class*=\"EmptyScreen Screen-Empty\"] div[class*=\"EmptyScreen-Subtitle\"]");

    public PurchasesPage(WebDriver driver) {
        this.driver = driver;

        new WebDriverWait(driver, 15).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        if (!TITLE.equals(driver.getTitle())) {
            throw new IllegalStateException("This is not Purchases page");
        }
    }
}
