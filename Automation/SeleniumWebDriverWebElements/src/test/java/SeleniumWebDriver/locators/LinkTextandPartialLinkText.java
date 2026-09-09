package SeleniumWebDriver.locators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LinkTextandPartialLinkText {

	WebDriver driver;
	
	@BeforeTest
	public void Setup() throws Exception {
		driver = new ChromeDriver();
		driver.get("https://www.hollandandbarrett.com/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
	}
	
	@Test
	public void linkTest() {
		driver.findElement(By.partialLinkText("Vitamins")).click();
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int nolinks = links.size();
		System.out.println("Number of Links in hollandandbarrett Application :" + nolinks);
		
		for(int i = 0 ; i<links.size() ; i++) {
			System.out.println(links.get(i).getText());
			
			String str = links.get(i).getText();
			String str1 = "Vitamins";
			if(str==str1) {
				driver.findElement(By.linkText("Vitamins")).click();
				driver.findElement(By.xpath("(//a[contains(@type,'button')])[4]")).click();
			}
		}
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}