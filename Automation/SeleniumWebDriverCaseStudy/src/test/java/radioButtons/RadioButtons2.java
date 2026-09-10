package radioButtons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RadioButtons2 {

	WebDriver driver;

	@BeforeTest
	public void Setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void TestEmployeeRegistration() throws Exception {

		driver.get("https://testautomationpractice.blogspot.com/");
		Thread.sleep(3000);

		// Enter employee name
		WebElement name = driver.findElement(By.id("name"));
		name.sendKeys("Indroji");

		// Select Female
		WebElement female = driver.findElement(By.id("female"));
		female.click();

		System.out.println("Female selected: " + female.isSelected());

		// Verify Male is not selected
		WebElement male = driver.findElement(By.id("male"));
		System.out.println("Male selected: " + male.isSelected());

		// Submit registration
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

		Thread.sleep(3000);
	}

	@Test
	public void TestJotform() throws Exception {

		driver.get("https://www.jotform.com/build/262511769568469?s=templates");
		Thread.sleep(5000);

		// Count radio buttons
		int radioButtons = driver.findElements(By.xpath("//input[@type='radio']")).size();

		System.out.println("Number of Radio Buttons in Jotform: " + radioButtons);

		// Display radio button information
		for (int i = 0; i < radioButtons; i++) {

			WebElement radio = driver.findElements(By.xpath("//input[@type='radio']")).get(i);

			System.out.println("--------------------------------");
			System.out.println("Radio Button " + (i + 1));
			System.out.println("Displayed: " + radio.isDisplayed());
			System.out.println("Enabled: " + radio.isEnabled());
			System.out.println("Selected: " + radio.isSelected());

			radio.click();

			System.out.println("Selected After Click: " + radio.isSelected());
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
