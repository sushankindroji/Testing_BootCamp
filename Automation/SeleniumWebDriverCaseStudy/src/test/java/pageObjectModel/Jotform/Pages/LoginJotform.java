package pageObjectModel.Jotform.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginJotform {

    WebDriver driver;

    public LoginJotform(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//a[contains(text(),'Log In')]")
    WebElement loginLink;

    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(xpath = "//button[contains(text(),'Log In')]")
    WebElement loginButton;

    public void login_Jotform(String uname, String pass) {

        loginLink.click();

        username.sendKeys(uname);
        password.sendKeys(pass);

        loginButton.click();
    }
}
