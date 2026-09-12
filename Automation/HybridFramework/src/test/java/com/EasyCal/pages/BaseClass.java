package com.EasyCal.pages;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import utility.BrowserFactory;
import utility.ConfigDataProvider;
import utility.ExcelDataReader;
import utility.Helper;

public class BaseClass {

	public WebDriver driver;
	public ExcelDataReader excel;
	public ConfigDataProvider config;

	@BeforeSuite
	public void SetUp() {
		excel = new ExcelDataReader();
		config = new ConfigDataProvider();
	}

	@BeforeClass
	public void BrowserTest() {
		String browser = config.getBrowser();
		String url = config.getAppURl();

		System.out.println("Browser = " + browser);
		System.out.println("AppUrl = " + url);

		driver = BrowserFactory.BrowserOptions(driver, browser, url);
	}

	@AfterClass
	public void tearDown() {
		BrowserFactory.quitBrowser(driver);
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE && driver != null) {
			Helper.CapturedScreenShot(driver);
		}
	}
}