package WebDriverCommands;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class NavigationCommands {
	WebDriver driver;
	String baseurl = "https://practicetestautomation.com/practice-test-login/";
	String baseBackUrl = "https://www.google.com/";
	String baseNavUrl = "https://login.yahoo.com/";
	
	@Test(priority = 0) 
	public void NavigateDemo() {
		driver = new ChromeDriver();
		driver.navigate().to(baseurl);
		String Url = driver.getCurrentUrl();
		System.out.println(Url);
		driver.quit();
	}
	
	@Test(priority = 1)
	public void NavigateBackDemo() {
		driver = new ChromeDriver();
		driver.get(baseBackUrl);
		String URL = driver.getCurrentUrl();
		System.out.println(URL);
		String Title = driver.getTitle();
		System.out.println(Title);
		
		driver.navigate().to(baseNavUrl);
		URL = driver.getCurrentUrl();
		System.out.println(URL);
		
		driver.navigate().back();
		URL = driver.getCurrentUrl();
		System.out.println(URL);
	}
	
	@Test(priority=2)
	public void RefreshMethod()  {
		driver = new ChromeDriver();
		driver.get(baseBackUrl);
		String URL = driver.getCurrentUrl();
		System.out.println(URL);
		
		driver.navigate().refresh();
		String Title = driver.getTitle();
		System.out.println(Title);
		
		driver.navigate().refresh();
		URL = driver.getCurrentUrl();
		System.out.println(URL);
	}
	
	@Test(priority=3)
	public void NavigateForward() {
		driver = new ChromeDriver();
		driver.get(baseBackUrl);
		String URL = driver.getCurrentUrl();
		System.out.println(URL);
		String Title = driver.getTitle();
		System.out.println(Title);
		
		driver.navigate().to(baseNavUrl);
		URL = driver.getCurrentUrl();
		System.out.println(URL);
		
		driver.navigate().back();
		URL = driver.getCurrentUrl();
		System.out.println(URL);
		
		driver.navigate().refresh();
		
		driver.navigate().forward();
		URL = driver.getCurrentUrl();
		System.out.println(URL);
		
	}
}




