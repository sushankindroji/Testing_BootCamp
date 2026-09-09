package seleniumWebDriverlocators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TagNameCaseStudy {

    @Test
    public void countLinks() throws Exception {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        // EasyCalculation
        driver.get("https://www.easycalculation.com/");
        Thread.sleep(5000);

        List<WebElement> easyLinks = driver.findElements(By.tagName("a"));

        System.out.println("EasyCalculation Total Links: " + easyLinks.size());

        for (WebElement ele : easyLinks) {
            System.out.println(ele.isDisplayed());
            System.out.println(ele.isEnabled());
            System.out.println(ele.getAttribute("href"));
        }


        // Swag Labs
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(5000);

        List<WebElement> swagLinks = driver.findElements(By.tagName("a"));

        System.out.println("Swag Labs Total Links: " + swagLinks.size());

        for (WebElement ele : swagLinks) {
            System.out.println(ele.isDisplayed());
            System.out.println(ele.isEnabled());
            System.out.println(ele.getAttribute("href"));
        }


        // Naukri.com
        driver.get("https://www.naukri.com/");
        Thread.sleep(7000);

        List<WebElement> naukriLinks = driver.findElements(By.tagName("a"));

        System.out.println("Naukri.com Total Links: " + naukriLinks.size());

        for (WebElement ele : naukriLinks) {
            System.out.println(ele.isDisplayed());
            System.out.println(ele.isEnabled());
            System.out.println(ele.getAttribute("href"));
        }


        // nopCommerce
        driver.get("https://demo.nopcommerce.com/");
        Thread.sleep(5000);

        List<WebElement> nopLinks = driver.findElements(By.tagName("a"));

        System.out.println("nopCommerce Total Links: " + nopLinks.size());

        for (WebElement ele : nopLinks) {
            System.out.println(ele.isDisplayed());
            System.out.println(ele.isEnabled());
            System.out.println(ele.getAttribute("href"));
        }


        // BlazeDemo
        driver.get("https://blazedemo.com/");
        Thread.sleep(5000);

        List<WebElement> blazeLinks = driver.findElements(By.tagName("a"));

        System.out.println("BlazeDemo Total Links: " + blazeLinks.size());

        for (WebElement ele : blazeLinks) {
            System.out.println(ele.isDisplayed());
            System.out.println(ele.isEnabled());
            System.out.println(ele.getAttribute("href"));
        }


        driver.quit();
    }
}