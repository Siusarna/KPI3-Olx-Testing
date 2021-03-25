package pages;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class RegisterPage {
    private WebDriver driver;

    @FindBy(id = "userEmailPhoneRegister")
    private WebElement email;

    @FindBy(id = "userPassRegister")
    private WebElement password;

    @FindBy(id = "button_register")
    private WebElement submit;

    @FindBy(xpath = "//*[@id=\"registerForm\"]/div[3]/div/div/label")
    private WebElement checker;

    public RegisterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void registerUser(String username, String passwd) {
        email.sendKeys(username);
        password.sendKeys(passwd);
        checker.click();
        submit.submit();
    }

}

