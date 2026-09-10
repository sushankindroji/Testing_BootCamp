package radioButtons;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RadioButtons {

    @Test
    public void TestRadioButtons() throws Exception {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.spicejet.com/");

        Thread.sleep(5000);

        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio']"));

        System.out.println("Number of Radio Buttons: " + radioButtons.size());

        for (int i = 0; i < radioButtons.size(); i++) {

            WebElement radio = radioButtons.get(i);

            System.out.println("--------------------------------");
            System.out.println("Radio Button " + (i + 1));

            System.out.println("Displayed: " + radio.isDisplayed());
            System.out.println("Enabled: " + radio.isEnabled());
            System.out.println("Selected Before Click: " + radio.isSelected());

            radio.click();

            System.out.println("Selected After Click: " + radio.isSelected());
        }

        driver.quit();
    }
}