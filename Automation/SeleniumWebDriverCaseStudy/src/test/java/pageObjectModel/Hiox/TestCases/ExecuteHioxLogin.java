package pageObjectModel.Hiox.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pageObjectModel.Hiox.Pages.LoginHiox;
import pageObjectModel.helper.BrowserFactory;

public class ExecuteHioxLogin {

    @Test
    public void CheckValidUser() {

        WebDriver driver = BrowserFactory.BrowserOptions(
                "chrome",
                "https://www.login.hiox.com/login?referrer=easycalculation.com"
        );

        LoginHiox loginPage =
                PageFactory.initElements(driver, LoginHiox.class);

        loginPage.login_Hiox("your_username", "your_password");
    }
}
