package seleniumWebDriver.Browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class LaunchDifferentBrowsers {

    @Test
    public void ChromeBrowser() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/downloads/");
        driver.manage().window().maximize();
        driver.manage().window().minimize();

    }
    
    @Test
    public void FirefoxBrowser() {

        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.selenium.dev/downloads/");
        driver.manage().window().maximize();
        driver.manage().window().maximize();

    }
    
    @Test
    public void EdgeBrowser() {

        WebDriver driver = new EdgeDriver();
        driver.get("https://www.selenium.dev/downloads/");
        driver.manage().window().maximize();
        driver.manage().window().maximize();

    }
}