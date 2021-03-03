import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


public class MTSTest {

    WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://yandex.ru/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void mainTest() {
        PurchasesPage purchasesPage = new MainPage(driver)
                .openTVOnlineThroughMoreMenu()
                .openPurchasesPage();
        Assert.assertEquals("Покупок пока нет", purchasesPage.getEmptyScreenTitleText());
        Assert.assertEquals("Покупайте и смотрите новинки не выходя из дома", purchasesPage.getEmptyScreenSubtitleText());
    }

    @Test
    public void secondTest() {
        FilmMenuPage filmMenuPagePage = new MainPage(driver)
                .openTVOnlineThroughMoreMenu()
                .openFilmPageFromLeftMenu();
        String url = filmMenuPagePage.getFilmUrl(1,2);
        String response = Helpers.getResponseFromURL(url, "GET");
        String year = Helpers.getReleaseYearFromSting(response);
        String age = Helpers.getRestrictionAgeFromSting(response);
        FilmPage filmPage = filmMenuPagePage.clickOnAFilm(1,2);
        String pageYear = filmPage.getFilmYear();
        String pageRestrictionAge = filmPage.getRestrictionAge();
        Assert.assertEquals(year, pageYear);
        Assert.assertEquals(age, pageRestrictionAge);
        filmPage.fullScreen();
        Assert.assertTrue(filmPage.verifyFilmStart());
    }

    @Test
    public void thirdTest() {
        TVOnlinePage tvOnlinePage = new MainPage(driver)
                .openTVOnlineThroughMoreMenu()
                .openFilmPageFromLeftMenu()
                .searchFor("Апгрейд")
                .waitMetadataToLoadbyRating();
        Assert.assertTrue(tvOnlinePage.checkVideoPresented("Апгрейд"));
        Assert.assertEquals("2018 • фантастика, боевик, триллер, детектив, криминал", tvOnlinePage.getVidoDiscriptionByTitle("Апгрейд"));
    }

}
