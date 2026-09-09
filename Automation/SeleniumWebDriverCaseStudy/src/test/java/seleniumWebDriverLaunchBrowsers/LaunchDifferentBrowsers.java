package seleniumWebDriverLaunchBrowsers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class LaunchDifferentBrowsers {
	//Marks a class or a method as part of the test.
	@Test(priority=0)
	public void ChromeBrowserTest() throws InterruptedException {
		ChromeDriver driver = new ChromeDriver(); // creating instance of a class using new keyword.
		driver.get("https://practicetestautomation.com/practice-test-login/");
		driver.manage().window().maximize(); //maximize is a method to fit the size of window.
		Thread.sleep(10000);
		driver.manage().window().minimize(); // minimize is a method
	}
	
	@Test(priority=1)
	public void FirefoxBrowserTest() throws InterruptedException {
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("https://www.selenium.dev/");
		driver.manage().window().maximize(); 
		Thread.sleep(10000);
		driver.manage().window().minimize(); 
	}
	
	@Test(priority=2)
	public void EdgeBrowserTest() throws InterruptedException {
		EdgeDriver driver = new EdgeDriver();
		driver.get("https://automationexercise.com/");
		driver.manage().window().maximize(); 
		Thread.sleep(10000);
		driver.manage().window().minimize(); 
	}

}