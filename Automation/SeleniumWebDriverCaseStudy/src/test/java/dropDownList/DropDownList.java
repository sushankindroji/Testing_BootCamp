package dropDownList;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utility.Helper;

public class DropDownList{

    WebDriver driver = Helper.startBrowser("Chrome");

    @BeforeTest
    public void Setup() {
        driver.manage().window().maximize();
    }

    @Test
    public void DropDownLists() throws Exception {

        // 1. Elfsight Employee Registration Form
        driver.navigate().to("https://elfsight.com/online-form-builder/templates/html-employee-registration-form/");
        Thread.sleep(5000);

        List<WebElement> elfsightDropdowns =
                driver.findElements(By.tagName("select"));

        System.out.println("===== ELFSIGHT =====");
        System.out.println("Number of Drop Downs: " + elfsightDropdowns.size());

        for (WebElement dropdown : elfsightDropdowns) {

            Select list = new Select(dropdown);

            List<WebElement> options = list.getOptions();

            System.out.println("Items Count: " + options.size());

            for (WebElement ele : options) {
                System.out.println("Item: " + ele.getText());
            }
        }


        // 2. QA Practice Form
        driver.navigate().to("https://www.qa-practice.com/forms/practice-form");
        Thread.sleep(5000);

        List<WebElement> qaDropdowns =
                driver.findElements(By.tagName("select"));

        System.out.println("\n===== QA PRACTICE =====");
        System.out.println("Number of Drop Downs: " + qaDropdowns.size());

        for (WebElement dropdown : qaDropdowns) {

            Select list = new Select(dropdown);

            List<WebElement> options = list.getOptions();

            System.out.println("Items Count: " + options.size());

            for (WebElement ele : options) {
                System.out.println("Item: " + ele.getText());
            }
        }


        // 3. Facebook Registration
        driver.navigate().to("https://www.facebook.com/reg/?entry_point=login");
        Thread.sleep(5000);

        List<WebElement> facebookDropdowns =
                driver.findElements(By.tagName("select"));

        System.out.println("\n===== FACEBOOK =====");
        System.out.println("Number of Drop Downs: " + facebookDropdowns.size());

        for (WebElement dropdown : facebookDropdowns) {

            Select list = new Select(dropdown);

            List<WebElement> options = list.getOptions();

            System.out.println("Items Count: " + options.size());

            for (WebElement ele : options) {
                System.out.println("Item: " + ele.getText());
            }
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
