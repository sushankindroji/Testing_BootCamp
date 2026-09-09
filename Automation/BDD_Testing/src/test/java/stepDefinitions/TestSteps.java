package stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestSteps {

	private EdgeDriver driver;
	private WebDriverWait wait;

	// ---------- Common navigation / launch steps ----------

	@Given("User should Launch Edge browser")
	public void user_should_launch_edge_browser() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	@When("User should Navigate to url {string}")
	public void user_should_navigate_to_url(String url) {
		driver.get(url);
	}

	@When("User should Verify that home page is visible successfully")
	public void user_should_verify_that_home_page_is_visible_successfully() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		assertTrue("Home page did not load", driver.getTitle() != null && !driver.getTitle().isEmpty());
	}

	@When("User should Verify that login page is visible successfully")
	public void user_should_verify_that_login_page_is_visible_successfully() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
	}

	// ---------- automationexercise.com steps ----------

	@Then("Click on {string} button")
	public void click_on_button(String label) {
		click_button(label);
	}

	@When("Click {string} button")
	public void click_button(String label) {
		WebElement element;
		switch (label) {
			case "Signup / Login":
				element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Signup / Login")));
				break;
			case "login":
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-qa='login-button']")));
				break;
			case "Signup":
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-qa='signup-button']")));
				break;
			case "Create Account":
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-qa='create-account']")));
				break;
			case "Continue":
				element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-qa='continue-button']")));
				break;
			case "Delete Account":
				element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Delete Account")));
				break;
			case "Logout":
				element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
				break;
			case "Submit":
				element = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
				break;
			default:
				element = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//*[normalize-space(text())='" + label + "']")));
		}
		element.click();
	}

	@Then("Verify {string} is visible")
	public void verify_is_visible(String text) {
		verify_that_is_visible(text);
	}

	@When("Verify that {string} is visible")
	@Then("Verify that {string} is visible")
	public void verify_that_is_visible(String text) {
		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[contains(normalize-space(text()), \"" + text + "\")]")));
		assertTrue("Expected text not visible: " + text, el.isDisplayed());
	}

	@Then("Enter correct email address and password")
	public void enter_correct_email_address_and_password() {
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("valid_email@example.com");
		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("ValidPass123");
	}

	@Then("Enter incorrect email address and password")
	public void enter_incorrect_email_address_and_password() {
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("invalid_email@example.com");
		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("WrongPass123");
	}

	@Then("Verify error message {string} is visible")
	public void verify_error_message_is_visible(String message) {
		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//p[contains(text(), \"" + message + "\")]")));
		assertTrue(el.isDisplayed());
	}

	@Then("Verify that user is navigated to login page")
	public void verify_that_user_is_navigated_to_login_page() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Login to your account')]")));
	}

	@Then("Enter name and email address")
	public void enter_name_and_email_address() {
		driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Test User");
		driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("testuser" + System.currentTimeMillis() + "@example.com");
	}

	@Then("Fill details: Title, Name, Email, Password, Date of birth")
	public void fill_account_information_details() {
		driver.findElement(By.id("id_gender1")).click(); // Title: Mr.
		driver.findElement(By.id("password")).sendKeys("ValidPass123");
		new org.openqa.selenium.support.ui.Select(driver.findElement(By.id("days"))).selectByValue("10");
		new org.openqa.selenium.support.ui.Select(driver.findElement(By.id("months"))).selectByValue("5");
		new org.openqa.selenium.support.ui.Select(driver.findElement(By.id("years"))).selectByValue("1995");
	}

	@Then("Select checkbox {string}")
	public void select_checkbox(String label) {
		if (label.contains("newsletter")) {
			driver.findElement(By.id("newsletter")).click();
		} else if (label.contains("special offers")) {
			driver.findElement(By.id("optin")).click();
		}
	}

	@Then("Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number")
	public void fill_address_details() {
		driver.findElement(By.id("first_name")).sendKeys("Test");
		driver.findElement(By.id("last_name")).sendKeys("User");
		driver.findElement(By.id("company")).sendKeys("TestCo");
		driver.findElement(By.id("address1")).sendKeys("123 Test Street");
		driver.findElement(By.id("address2")).sendKeys("Apt 4B");
		new org.openqa.selenium.support.ui.Select(driver.findElement(By.id("country"))).selectByVisibleText("United States");
		driver.findElement(By.id("state")).sendKeys("California");
		driver.findElement(By.id("city")).sendKeys("Los Angeles");
		driver.findElement(By.id("zipcode")).sendKeys("90001");
		driver.findElement(By.id("mobile_number")).sendKeys("1234567890");
	}

	// ---------- practicetestautomation.com steps ----------

	@Then("Enter username {string} in Username field")
	public void enter_username_in_username_field(String username) {
		driver.findElement(By.id("username")).sendKeys(username);
	}

	@Then("Enter password {string} in Password field")
	public void enter_password_in_password_field(String password) {
		driver.findElement(By.id("password")).sendKeys(password);
	}

	@Then("Verify that error message is displayed")
	public void verify_that_error_message_is_displayed() {
		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
		assertTrue(el.isDisplayed());
	}

	@Then("Verify that error message text is {string}")
	public void verify_that_error_message_text_is(String expected) {
		WebElement el = driver.findElement(By.id("error"));
		assertTrue("Actual: " + el.getText(), el.getText().contains(expected));
	}

	@Then("Verify new page URL contains {string}")
	public void verify_new_page_url_contains(String partialUrl) {
		wait.until(ExpectedConditions.urlContains(partialUrl));
		assertTrue(driver.getCurrentUrl().contains(partialUrl));
	}

	@Then("Verify that {string} or {string} text is visible")
	public void verify_that_either_text_is_visible(String option1, String option2) {
		List<WebElement> els = driver.findElements(By.tagName("body"));
		String pageText = els.get(0).getText();
		assertTrue("Neither '" + option1 + "' nor '" + option2 + "' found",
				pageText.contains(option1) || pageText.contains(option2));
	}

	@Then("Verify that {string} button is displayed on the new page")
	public void verify_that_button_is_displayed_on_the_new_page(String label) {
		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(label)));
		assertTrue(el.isDisplayed());
	}

	// ---------- cleanup ----------

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}