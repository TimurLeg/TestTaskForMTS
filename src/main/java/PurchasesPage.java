import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class describing Purchases page in TV online.
 */
public class PurchasesPage extends Page {
    private final WebDriver driver;
    private static final String TITLE = "Мои покупки";
    private static final By EMPTY_SCREEN_TITLE = By.cssSelector("div[class*=\"EmptyScreen Screen-Empty\"] div[class*=\"EmptyScreen-Title\"]");
    private static final By EMPTY_SCREEN_SUBTITLE = By.cssSelector("div[class*=\"EmptyScreen Screen-Empty\"] div[class*=\"EmptyScreen-Subtitle\"]");

    /**
     * Wait for page to be load and check title.
     *
     * @param driver delegated driver;
     */
    public PurchasesPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        new WebDriverWait(driver, 15).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        if (!TITLE.equals(driver.getTitle())) {
            throw new IllegalStateException("This is not Purchases page");
        }
    }

    /**
     * Check that empty screen is presented and return its text.
     */
    public String getEmptyScreenTitleText() {
        return driver.findElement(EMPTY_SCREEN_TITLE).getText();
    }

    /**
     * Check that empty screen is presented and return its subtext.
     */
    public String getEmptyScreenSubtitleText() {
        return driver.findElement(EMPTY_SCREEN_SUBTITLE).getText();
    }
}
