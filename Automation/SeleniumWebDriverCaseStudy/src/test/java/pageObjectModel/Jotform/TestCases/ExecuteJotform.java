package pageObjectModel.Jotform.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pageObjectModel.Jotform.Pages.LoginJotform;

import pageObjectModel.helper.BrowserFactory;

public class ExecuteJotform {

    @Test
    public void CheckValidUser() {

        WebDriver driver = BrowserFactory.BrowserOptions(
                "chrome",
                "https://www.jotform.com/build/262511769568469?s=templates"
        );

        LoginJotform loginPage =
                PageFactory.initElements(driver, LoginJotform.class);

        loginPage.login_Jotform(
                "your_username",
                "your_password"
        );
    }
}
