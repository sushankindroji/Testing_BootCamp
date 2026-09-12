package com.EasyCal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver driver;

	// create Driver

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "log_email")
	WebElement email;

	@FindBy(name = "log_password")
	WebElement password;

	@FindBy(xpath = "//input[@name='log_submit']")
	WebElement loginbtn;

	public void login_EasyCal(String uname, String pass) {
		email.sendKeys(uname);
		password.sendKeys(pass);
		loginbtn.click();
	}

	public void tearDown() {
		driver.quit();
	}
}