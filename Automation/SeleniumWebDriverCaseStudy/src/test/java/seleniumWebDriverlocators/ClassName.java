package seleniumWebDriverlocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ClassName {

    @Test
    public void TestDemo() throws Exception {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get(
                "https://www.hollandandbarrett.com/shop/vitamins-supplements/vitamins/");

        Thread.sleep(5000);

        try {
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        } catch (Exception e) {
            System.out.println("Cookie popup not displayed.");
        }

        /*
         * Locate product image using ClassName.
         * Class names on modern websites can change,
         * so this locator may need updating if the website changes.
         */

        try {

            driver.findElement(
                    By.className("ProductCardImage-module_innerImage_pnkUg"))
                    .click();

            Thread.sleep(3000);

            System.out.println("Current URL: " + driver.getCurrentUrl());

        } catch (Exception e) {

            System.out.println(
                    "ClassName locator was not found. Website class may have changed.");
        }

        driver.quit();
    }
}