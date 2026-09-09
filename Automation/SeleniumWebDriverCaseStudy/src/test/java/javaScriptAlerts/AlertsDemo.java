package javaScriptAlerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import utility.Helper;

public class AlertsDemo {

	@Test
	public void KSRTCLogin() throws Exception {

		WebDriver driver = Helper.startBrowser("Chrome");

		driver.get("https://www.ksrtc.in/login");
		Thread.sleep(5000);

		// Find username field
		WebElement username = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div[1]/input"));
		Assert.assertTrue(username.isDisplayed());
		Assert.assertTrue(username.isEnabled());
		username.sendKeys("your_username");

		// Find password field
		WebElement password = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div[4]/input"));
		Assert.assertTrue(password.isDisplayed());
		Assert.assertTrue(password.isEnabled());
		password.sendKeys("your_password");

		// Verify password is entered
		String passwordType = password.getAttribute("type");
		System.out.println("Password field type: " + passwordType);
		Assert.assertEquals(passwordType, "password");

		// Click Login
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[4]")).click();
		Thread.sleep(5000);

		// Go back to login page
		driver.navigate().back();
		Thread.sleep(3000);

		// Refresh page
		driver.navigate().refresh();
		Thread.sleep(3000);
		System.out.println("KSRTC Login test completed");

		driver.quit();
	}


	@Test
	public void RediffLogin() throws Exception {

		WebDriver driver = Helper.startBrowser("Chrome");

		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		Thread.sleep(3000);

		// Verify username field
		WebElement username = driver.findElement(By.xpath("//*[@id=\"login1\"]"));
		Assert.assertTrue(username.isDisplayed());
		Assert.assertTrue(username.isEnabled());

		// Verify password field
		WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		Assert.assertTrue(password.isDisplayed());
		Assert.assertTrue(password.isEnabled());

		// Enter invalid username
		username.sendKeys("testuser123");

		// Enter invalid password
		password.sendKeys("testpassword");

		// Click Sign In
		driver.findElement(By.name("proceed")).click();
		Thread.sleep(5000);

		// Go back
		driver.navigate().back();
		Thread.sleep(3000);

		// Refresh page
		driver.navigate().refresh();
		Thread.sleep(3000);
		System.out.println("Rediff Login test completed");

		driver.quit();
	}
}