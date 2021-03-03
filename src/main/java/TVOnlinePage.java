import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TVOnlinePage {

    private static final String TITLE = "Яндекс.Эфир";

    private static final By PURCHASES = By.cssSelector("a[href*=\"purchases\"]");

    private static final By MOVIES_LOCATOR = By.cssSelector("a[href*=\"film\"]");

    private final WebDriver driver;

    public TVOnlinePage(WebDriver driver) {
        this.driver = driver;

        new WebDriverWait(driver, 15).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        if (!TITLE.equals(driver.getTitle())) {
            throw new IllegalStateException("This is not TVOnline page");
        }
    }

    public PurchasesPage openPurchasesPage() {
        driver.findElement(PURCHASES).click();
        return new PurchasesPage(driver);
    }

    public FilmMenuPage openFilmPageFromLeftMenu() {
        driver.findElement(MOVIES_LOCATOR).click();
        return new FilmMenuPage(driver);
    }

    public boolean checkVideoPresented(String title) {
        List<WebElement> list = driver.findElements(By.cssSelector("div[class*=\"Feed-Item Feed-Item_type_card Grid-Item\"]"));
        for (WebElement element: list) {
            if(element.findElement(By.cssSelector("div[class*=\"Card-Info Card-ContentItem\"] a[class*=\"StreamLink\"]")).getAttribute("title").equals(title)){
                return true;
            }
        }
        return false;
    }

    public TVOnlinePage waitMetadataToLoadbyRating() {
        driver.findElement(By.cssSelector("div[class*=\"RatingVendor\"]"));
        return this;
    }

    public String getVidoDiscriptionByTitle(String title) {
        List<WebElement> list = driver.findElements(By.cssSelector("div[class*=\"Feed-Item Feed-Item_type_card Grid-Item\"]"));
        for (WebElement element: list) {
            if(element.findElement(By.cssSelector("div[class*=\"Card-Info Card-ContentItem\"] a[class*=\"StreamLink\"]")).getAttribute("title").equals(title)){
                return element.findElement(By.cssSelector("div[class*=\"Card-Content\"] div[class*=\"BaseCard-TopReasonText\"]")).getText();
            }
        }
        return null;
    }
}
