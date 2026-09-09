package seleniumWebDriverlocators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LinkText {

    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.navigate().to("https://www.hollandandbarrett.com/");

        Thread.sleep(5000);

        try {
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        } catch (Exception e) {
            System.out.println("Cookie popup not displayed.");
        }
    }

    @Test
    public void findElementByLinkText() throws Exception {

        // Locate link using complete visible link text
        driver.findElement(By.linkText("Vitamins & Supplements")).click();

        Thread.sleep(3000);

        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Find all links using TagName
        List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println("Number of links: " + links.size());

        for (WebElement link : links) {

            String text = link.getText();

            if (!text.isEmpty()) {
                System.out.println(text);
            }
        }
    }

    @AfterTest
    public void tearDown() {

        driver.quit();
    }
}
