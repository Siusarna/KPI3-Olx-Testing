package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LaunchWebPlayerPage;
import pages.LoginPage;

public class SearchTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private LaunchWebPlayerPage page;
    private HomePage homePage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.out.println("start");
        driver = new ChromeDriver();
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
    public void testChangeLang() {
        driver.get("https://www.olx.ua/uk/");
        homePage = new HomePage(driver);
        homePage.changeLang();
        wait = new WebDriverWait(driver, 15);
        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        Assert.assertEquals("Сервис объявлений OLX: сайт объявлений в Украине - новые и бу товары на OLX.ua", driver.getTitle());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
