import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends Page {

    private static final String TITLE = "Яндекс";

    private static final By MORE_MENU_LOCATOR = By.cssSelector("a[href*=\"yandex.ru/all\"]");

    private static final By TV_ONLINE_LOCATOR = By.cssSelector("a[data-id*=\"tvonline\"]");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

        new WebDriverWait(driver, 15).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        if (!TITLE.equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the Main Page");
        }
    }

    public MainPage openMoreMenu() {
        driver.findElement(MORE_MENU_LOCATOR).click();
        return new MainPage(driver);
    }

    public TVOnlinePage openTVOnlineThroughMoreMenu(){
        openMoreMenu();
        driver.findElement(TV_ONLINE_LOCATOR).click();
        switchToTab(1);
        return new TVOnlinePage(driver);
    }


}
