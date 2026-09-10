package listBoxItemsDropDown;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import utility.Helper;

public class DropDownListBoxItems {
	WebDriver driver = Helper.startBrowser("Chrome");
	String url="https://testautomationpractice.blogspot.com/";
	
	@BeforeTest
	public void Setup() {
		driver.navigate().to(url);
		driver.manage().window().maximize();
	}
	
	@Test
	public void DropDownList() throws Exception {
		WebElement Country_Dropdown = driver.findElement(By.id("country"));
		Select country_list = new Select(Country_Dropdown);
		
		country_list.selectByIndex(3);
		Thread.sleep(10000);
		country_list.selectByVisibleText("India");
		Thread.sleep(10000);
		
		WebElement option = country_list.getFirstSelectedOption();
		String countryindex= option.getText();
		System.out.println(countryindex);
		
		List<WebElement> c_list = country_list.getOptions();
		int total_month = c_list.size();
		System.out.println("Total months count in List:" + total_month);
		
		for(WebElement ele : c_list) {
			String coun_name = ele.getText();
			System.out.println("Country Names in List : "+ coun_name);
		}
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}