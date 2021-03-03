import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class describing film page.
 */
public class FilmPage extends Page{

    private static final By RELEASE_YEAR_LOCATOR = By.cssSelector("span[class*=\"stream-program-title__subtitle stream-watching__player-header-subtitle\"]");
    private static final By RESTRICTION_AGE_LOCATOR = By.cssSelector("span[class*=\"stream-watching__restriction stream-program-title__restriction\"]");
    private static final By PLAYER_FULL_SCREEN_BUTTON_LOCATOR = By.cssSelector("button[aria-label*=\"Развернуть во весь экран\"]");
    private static final By TIME_LOCATOR = By.cssSelector("div[aria-label*=\"Ползунок поиска\"]");
    private final WebDriver driver;

    /**
     * Wait for page to be load and check title.
     *
     * @param driver delegated driver;
     */
    public FilmPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        new WebDriverWait(driver, 15).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Get year from film description.
     */
    public String getFilmYear() {
        return driver.findElement(RELEASE_YEAR_LOCATOR).getText().substring(0, 4);
    }

    /**
     * Get restriction age from film description.
     */
    public String getRestrictionAge() {
        String pageRestrictionAge = driver.findElement(RESTRICTION_AGE_LOCATOR).getText();
        return pageRestrictionAge.substring(0, pageRestrictionAge.length() - 1);
    }

    /**
     * Get restriction age from film description.
     */
    public FilmPage fullScreen() {
        driver.findElement(FilmPage.PLAYER_FULL_SCREEN_BUTTON_LOCATOR).click();
        return this;
    }

    /**
     * Check that film starts to play, by time.
     */
    public boolean verifyFilmStart() {
        String t = driver.findElement(TIME_LOCATOR).getAttribute("aria-valuetext");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String t1 = driver.findElement(TIME_LOCATOR).getAttribute("aria-valuetext");
        return !t.equals(t1);
    }
}
