package tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

import java.util.concurrent.ThreadLocalRandom;

public class AuthTest {
    private static WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;


    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/home/circleci/circleci-demo-java-spring/src/main/resources/chromedriver.exe");
        System.out.println("start");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        // full size window
        driver.manage().window().maximize();
    }

    //    xpath example
    @Test
    public void testRedirectToRegisterPage() {
        driver.get("https://www.olx.ua/uk/");
        homePage = new HomePage(driver);
        homePage.getLogin().click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.olx.ua/uk/account/?ref%5B0%5D%5Baction%5D=myaccount&ref%5B0%5D%5Bmethod%5D=index");
    }

//    id example
    @Test
    public void testIsRobot() {
        driver.get("https://www.olx.ua/uk/");
        homePage = new HomePage(driver);
        homePage.goToRegistration();
        wait = new WebDriverWait(driver, 15);
        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("ggxmtpvgnqckmxfefb@niwghx.com", "String1@");
        Assert.assertEquals("Не вдалося підтвердити користувача.", loginPage.textOfError());
    }

    @Test
    public void testRegisterNewUser() {
        driver.get("https://www.olx.ua/uk/");
        homePage = new HomePage(driver);
        homePage.goToRegistration();
        wait = new WebDriverWait(driver, 15);
        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToRegisterPage();
        WebDriverWait wait1 = new WebDriverWait(driver, 15);
        wait1.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerUser("asdalkjlkasd"+ ThreadLocalRandom.current().nextInt(10000, 99999 + 1) + "@gmail.com", "String1@");
        WebDriverWait wait2 = new WebDriverWait(driver, 15);
        wait2.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        Assert.assertEquals("Підтвердження", driver.getTitle());
    }

    @After
    public void teardown() {
        driver.quit();
    }


}
