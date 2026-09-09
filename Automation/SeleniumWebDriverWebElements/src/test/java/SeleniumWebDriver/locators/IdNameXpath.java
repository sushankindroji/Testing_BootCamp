package SeleniumWebDriver.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class IdNameXpath {

    WebDriver driver;

    String baseURL = "https://practicetestautomation.com/practice-test-login/";

    @Test
    public void ChromeBrowserTest1() {

        WebDriver driver = new ChromeDriver();

        driver.get(baseURL);
        driver.manage().window().maximize();

        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.name("password")).sendKeys("Password123");
        
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
    }
}