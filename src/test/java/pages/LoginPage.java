package pages;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "userEmail")
    private WebElement email;

    @FindBy(id = "userPass")
    private WebElement password;

    @FindBy(id = "se_userLogin")
    private WebElement submit;

    @FindBy(id = "recaptcha-anchor")
    private WebElement checker;

    @FindBy(xpath = "//*[@id=\"loginForm\"]/div/p")
    private WebElement error;

    @FindBy(id = "register_tab")
    private WebElement registerTab;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void loginUser(String username, String passwd) {
        email.sendKeys(username);
        password.sendKeys(passwd);
        submit.click();
    }

    public void goToRegisterPage() {
        registerTab.click();
    }

    public String textOfError() {
        return error.getText();
    }


}
