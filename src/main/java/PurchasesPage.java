import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PurchasesPage extends Page {

    private final WebDriver driver;

    private static final String TITLE = "Мои покупки";

    private static final By EMPTY_SCREEN_TITLE = By.cssSelector("div[class*=\"EmptyScreen Screen-Empty\"] div[class*=\"EmptyScreen-Title\"]");

    private static final By EMPTY_SCREEN_SUBTITLE = By.cssSelector("div[class*=\"EmptyScreen Screen-Empty\"] div[class*=\"EmptyScreen-Subtitle\"]");

    public PurchasesPage(WebDriver driver) {
        super(driver);

        this.driver = driver;

        new WebDriverWait(driver, 15).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        if (!TITLE.equals(driver.getTitle())) {
            throw new IllegalStateException("This is not Purchases page");
        }

    }

    public String getEmptyScreenTitleText() {
        WebElement element = driver.findElement(EMPTY_SCREEN_TITLE);
        if(!element.isEnabled())
            throw new IllegalStateException("Empty Purchase screen is not visible");
        return element.getText();
    }

    public String getEmptyScreenSubtitleText() {
        WebElement element = driver.findElement(EMPTY_SCREEN_SUBTITLE);
        if(!element.isEnabled())
            throw new IllegalStateException("Empty Purchase screen is not visible");
        return element.getText();
    }
}
