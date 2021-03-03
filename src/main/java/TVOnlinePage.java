import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

/**
 * Class describing TV online page.
 */
public class TVOnlinePage extends Page {
    private static final String TITLE = "Яндекс.Эфир";
    private static final By PURCHASES = By.cssSelector("a[href*=\"purchases\"]");
    private static final By MOVIES_LOCATOR = By.cssSelector("a[href*=\"film\"]");
    private static final By SEARCHED_ELEMENTS_LOCATOR = By.cssSelector("div[class*=\"Feed-Item Feed-Item_type_card Grid-Item\"]");
    private static final By SEARCHED_ELEMENT_WITH_TITLE_LOCATOR = By.cssSelector("div[class*=\"Card-Info Card-ContentItem\"] a[class*=\"StreamLink\"]");
    private static final By SEARCHED_ELEMENT_WITH_SUBTEXT_LOCATOR = By.cssSelector("div[class*=\"Card-Content\"] div[class*=\"BaseCard-TopReasonText\"]");
    private static final By RATING_LOCATOR = By.cssSelector("div[class*=\"RatingVendor\"]");
    private final WebDriver driver;

    /**
     * Wait for page to be load and check title.
     *
     * @param driver delegated driver;
     */
    public TVOnlinePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        new WebDriverWait(driver, 15).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        if (!TITLE.equals(driver.getTitle())) {
            throw new IllegalStateException("This is not TVOnline page");
        }
    }

    /**
     * Click on purchase button and wait screen.
     */
    public PurchasesPage openPurchasesPage() {
        driver.findElement(PURCHASES).click();
        return new PurchasesPage(driver);
    }

    /**
     * Click on film page menu and wait screen.
     */
    public FilmMenuPage openFilmPageFromLeftMenu() {
        driver.findElement(MOVIES_LOCATOR).click();
        return new FilmMenuPage(driver);
    }

    /**
     * Click that video with requested title is presented.
     * @param title that wos requested.
     */
    public boolean checkVideoPresented(String title) {
        List<WebElement> list = driver.findElements(SEARCHED_ELEMENTS_LOCATOR);
        for (WebElement element : list) {
            if (element.findElement(SEARCHED_ELEMENT_WITH_TITLE_LOCATOR).getAttribute("title").equals(title)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Wait that metadata is load on a page by searching a rating element.
     */
    public TVOnlinePage waitMetadataToLoadbyRating() {
        driver.findElement(RATING_LOCATOR);
        return this;
    }

    /**
     * Get description of a video with requested title.
     */
    public String getVidoDiscriptionByTitle(String title) {
        List<WebElement> list = driver.findElements(SEARCHED_ELEMENTS_LOCATOR);
        for (WebElement element : list) {
            if (element.findElement(SEARCHED_ELEMENT_WITH_TITLE_LOCATOR).getAttribute("title").equals(title)) {
                return element.findElement(SEARCHED_ELEMENT_WITH_SUBTEXT_LOCATOR).getText();
            }
        }
        return null;
    }
}
