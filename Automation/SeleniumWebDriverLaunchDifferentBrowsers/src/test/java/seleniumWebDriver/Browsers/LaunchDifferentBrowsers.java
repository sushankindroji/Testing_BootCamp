package seleniumWebDriver.Browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import java.time.Duration;

public class LaunchDifferentBrowsers {

    @Test
    public void ChromeBrowser() {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
        driver.get("https://www.selenium.dev/downloads/");
        driver.manage().window().maximize();
        driver.manage().window().minimize();
        driver.quit();

    }
    
    @Test
    public void FirefoxBrowser() {

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
        driver.get("https://www.selenium.dev/downloads/");
        driver.manage().window().maximize();
        driver.manage().window().maximize();
        driver.quit();

    }
    
    @Test
    public void EdgeBrowser() {

        WebDriver driver = new EdgeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
        driver.get("https://www.selenium.dev/downloads/");
        driver.manage().window().maximize();
        driver.manage().window().maximize();
        driver.quit();

    }
}