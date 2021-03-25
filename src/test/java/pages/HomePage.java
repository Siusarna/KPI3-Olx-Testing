package pages;

import jdk.jfr.DataAmount;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


@Data
public class HomePage {
    private WebDriver driver;

    @FindBy(css = "span.link")
    private WebElement login;

    @FindBy(name = "q")
    private WebElement searcher;

    @FindBy(linkText = "язык")
    private WebElement changeLang;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void search(String input) {
        searcher.sendKeys(input);
        searcher.submit();
    }

    public void changeLang() {
        changeLang.click();
    }

    public void goToRegistration() {
        login.click();
    }
}
