package com.EasyCal.TestCses;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.EasyCal.pages.BaseClass;
import com.EasyCal.pages.LoginPage;

import utility.ExcelDataReader;

public class LoginEasyCalculation extends BaseClass {

	@Test
	public void loginApp() throws Exception {
		ExcelDataReader excel = new ExcelDataReader();
		excel.getStringData("LoginHRM", 0, 0);
		
		LoginPage loginHRM = PageFactory.initElements(driver, LoginPage.class);
		loginHRM.login_EasyCal(excel.getStringData("LoginHRM", 0, 0), excel.getStringData("LoginHRM", 0, 1));
		Thread.sleep(5000);
	}
}