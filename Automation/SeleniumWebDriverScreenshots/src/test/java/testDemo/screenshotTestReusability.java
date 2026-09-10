package testDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import library.Reusability;

public class screenshotTestReusability{

    @Test
    public void Browser() throws Exception {

        ChromeDriver driver = new ChromeDriver();

        driver.get("https://auth.hollandandbarrett.com/u/login");
        driver.manage().window().maximize();

        Reusability.capturedScreenshot(driver, "A. Launch Application - TakeScreenShot");

        driver.findElement(By.id("username"))
              .sendKeys("raghu.astepahead@gmail.com");

        driver.findElement(By.name("password"))
              .sendKeys("raghuBN@123");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//button[@name='action']"))
              .click();

        Reusability.capturedScreenshot(driver, "B. Click Sign In Button - TakeScreenShot");

        driver.quit();
    }
}
