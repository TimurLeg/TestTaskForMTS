import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MTSTest {

    WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://yandex.ru/");
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.close();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void mainTest() {
        //Ожидаем кнопку "еще"
        //TODO вынести в мейн пейдж
        WebElement moreButton = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(MainPage.MORE_MENU_LOCATOR));

        moreButton.click();
        MainPage mainPage = new MainPage(driver);
        WebElement tvOnlineButton = mainPage.waitMorePopUpAppearForElementByLocator(MainPage.TV_ONLINE_LOCATOR);
        tvOnlineButton.click();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        TVOnlinePage tvOnlinePage = new TVOnlinePage(driver);
        WebElement purchasesButton = driver.findElement(TVOnlinePage.PURCHASES);
        purchasesButton.click();

        PurchasesPage purchasesPage = new PurchasesPage(driver);

        WebElement emptyScreenTitle = driver.findElement(PurchasesPage.EMPTY_SCREEN_TITLE);
        WebElement emptyScreenSubtitle = driver.findElement(PurchasesPage.EMPTY_SCREEN_SUBTITLE);

        Assert.assertTrue(emptyScreenTitle.getText().equals("Покупок пока нет"));
        Assert.assertTrue(emptyScreenSubtitle.getText().equals("Покупайте и смотрите новинки не выходя из дома"));


       /* WebElement title = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*=\"all\"]")));
        WebElement moreButton = driver.findElement(By.cssSelector("a[href*="all"]"));
        moreButton.click();
        Thread.sleep(5000);*/
    }

}
