package seleniumWebDriverlocators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TagName {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.hollandandbarrett.com/");
        driver.manage().window().maximize();

        Thread.sleep(5000);

        try {
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        } catch (Exception e) {
            System.out.println("Cookie popup not displayed.");
        }

        // Locate all hyperlinks using TagName
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));

        System.out.println("Number of links: " + allLinks.size());

        for (WebElement ele : allLinks) {

            System.out.println("Displayed: " + ele.isDisplayed());
            System.out.println("Enabled: " + ele.isEnabled());
            System.out.println("URL: " + ele.getAttribute("href"));
            System.out.println("--------------------------------");
        }

        driver.quit();
    }
}
