package com.EasyCalculation.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.EasyCalculation.Pages.LoginEasyCal;
import Helper.BrowserFactory;

public class ExecuteLoginPage {
	@Test
	public void CheckValidUser() {
		WebDriver driver = BrowserFactory.BrowserOptions("chrome","https://www.login.hiox.com/login?referrer=easycalculation.com");
		
		LoginEasyCal loginPageEasyCal = PageFactory.initElements(driver, LoginEasyCal.class);
		loginPageEasyCal.login_EasyCal("9123123123","ajax@123");
		driver.quit();
	}
}
