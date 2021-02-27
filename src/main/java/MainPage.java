import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    public static final String TITLE = "Яндекс";

    public static final By MORE_MENU_LOCATOR = By.cssSelector("a[href*=\"yandex.ru/all\"]");

    public static final By TV_ONLINE_LOCATOR = By.xpath("/html/body/div[3]/div/ul/li[40]/a");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;

        new WebDriverWait(driver, 15).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        if (!TITLE.equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the Main Page");
        }
    }

    public WebElement waitMorePopUpAppearForElementByLocator(By locator){
        return new WebDriverWait(driver,15).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitElementToAppear(WebElement element){

    }
}
