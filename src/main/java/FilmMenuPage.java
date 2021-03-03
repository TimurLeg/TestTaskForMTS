import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class describing film menu page.
 */
public class FilmMenuPage extends Page {

    private static final String TITLE = "Фильмы — Смотреть в Эфире";
    private static final By HORIZONTAL_CAROUSELS_LOCATOR = By.cssSelector("div[class*=\"Feed-Item Feed-Item_type_carousel\"]");
    private static final By VIDEO_THUMB_META_LOCATOR = By.cssSelector("div[class*=\"VideoThumb-Duration VideoThumb-Item\"]");
    private static final By VIDEO_GROUP_LOCATOR = By.cssSelector("div[class*=\"Carousel-Item Scroller-Item Grid-Item\"]");
    private static final By LINK_LOCATOR = By.cssSelector("a[class*=\"StreamLink StreamLink_block\"]");
    private static final By SEARCH_BAR_LOCATOR = By.cssSelector("input[class*=\"input__control\"]");
    private final WebDriver driver;

    /**
     * Wait for page to be load and check title.
     *
     * @param driver delegated driver;
     */
    public FilmMenuPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        new WebDriverWait(driver, 15).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        if (!TITLE.equals(driver.getTitle())) {
            throw new IllegalStateException("This is not Film page");
        }
    }

    private WebElement getChosenElement(int row, int index) {
        driver.findElement(VIDEO_THUMB_META_LOCATOR);
        return driver.findElements(HORIZONTAL_CAROUSELS_LOCATOR)
                .get(row)
                .findElements(VIDEO_GROUP_LOCATOR)
                .get(index);
    }

    /**
     * Get full url of selected film by row and index in this row.
     * ex: getFilmUrl(2, 3) will return URL in string format of 4th film in third row.
     *
     * @param row   index of row starting from 0.
     * @param index in row starting from 0.
     * @return url in String format.
     */
    public String getFilmUrl(int row, int index) {
        return getChosenElement(row, index).findElement(LINK_LOCATOR).getAttribute("href");
    }

    /**
     * Click on specified film.
     * ex: clickOnAFilm(2, 3) will click on 4th film in third row.
     *
     * @param row   index of row starting from 0.
     * @param index in row starting from 0.
     */
    public FilmPage clickOnAFilm(int row, int index) {
        getChosenElement(row, index).click();
        return new FilmPage(driver);
    }

    /**
     * Input text in search field and press enter.
     *
     * @param text to search.
     */
    public TVOnlinePage searchFor(String text) {
        WebElement element = driver.findElement(SEARCH_BAR_LOCATOR);
        element.sendKeys(text);
        element.sendKeys(Keys.RETURN);
        return new TVOnlinePage(driver);
    }
}
