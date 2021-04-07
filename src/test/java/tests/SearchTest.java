package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

public class SearchTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

    @Before
    public void setup() {
        String user_dir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", user_dir+"/src/main/resources/chromedriver.exe");
        System.out.println("start");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        // full size window
        driver.manage().window().maximize();
    }

    @Test
    public void testSearchBar() {
        driver.get("https://www.olx.ua/uk/");
        homePage = new HomePage(driver);
        homePage.search("Компютер");
        wait = new WebDriverWait(driver, 15);
        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        Assert.assertEquals("Компютер - OLX.ua", driver.getTitle());
    }

    //    test css
    @Test
    public void testChangeLang() throws InterruptedException {
        driver.get("https://www.olx.ua/uk/");
        homePage = new HomePage(driver);
        homePage.changeLang();
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.titleIs("Сервис объявлений OLX: сайт объявлений в Украине - новые и бу товары на OLX.ua"));
        Assert.assertEquals("Сервис объявлений OLX: сайт объявлений в Украине - новые и бу товары на OLX.ua", driver.getTitle());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
