package pageObjectModel.NopCommerce.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pageObjectModel.NopCommerce.Pages.LoginNopCommerce;
import pageObjectModel.helper.BrowserFactory;

public class ExecuteNopCommerce {

    @Test
    public void CheckValidUser() {

        WebDriver driver = BrowserFactory.BrowserOptions(
                "chrome",
                "https://www.nopcommerce.com/en/login"
        );

        LoginNopCommerce loginPage =
                PageFactory.initElements(driver, LoginNopCommerce.class);

        loginPage.login_NopCommerce(
                "your_email@example.com",
                "your_password"
        );
    }
}
