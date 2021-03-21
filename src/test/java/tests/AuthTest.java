package tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.out.println("start");
        driver = new ChromeDriver();
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
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser("ggxmtpvgnqckmxfefb@niwghx.com", "String1@");
        Assert.assertEquals("Не вдалося підтвердити користувача.", loginPage.textOfError());
    }

    @Test
    public void testRegisterNewUser() {
        driver.get("https://www.olx.ua/uk/");
        homePage = new HomePage(driver);
        homePage.goToRegistration();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToRegisterPage();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerUser("ggxmtpvgnqckmxfefb"+ ThreadLocalRandom.current().nextInt(1000, 9999 + 1) + "@gmail.com", "String1@");
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("login-box-confirm__link")));
        Assert.assertEquals("Підтвердження", driver.getTitle());
    }

    @After
    public void teardown() {
        driver.quit();
    }


}
