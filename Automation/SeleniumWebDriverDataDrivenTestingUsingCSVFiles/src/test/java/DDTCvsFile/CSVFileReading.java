package DDTCvsFile;


import com.opencsv.CSVReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.FileReader;

public class CSVFileReading {
    WebDriver driver;
    String CsvPath = "/Users/sushank2/Documents/Testing/data.csv";

    @BeforeTest
    public void setup() throws Exception {
        driver = new ChromeDriver();
        driver.get("http://only-testing-blog.blogspot.com/2014/05/form.html");
        driver.manage().window().maximize();
    }

    @Test
    public void DDTTestingBlog() throws Exception {
        CSVReader reader = new CSVReader(new FileReader(CsvPath));
        String[] csvCell;
        while ((csvCell = reader.readNext()) != null) {
            String FName = csvCell[0];
            String LName = csvCell[1];
            String Email = csvCell[2];
            String MNumb = csvCell[3];
            String CName = csvCell[4];

            driver.findElement(By.name("FirstName")).sendKeys(FName);
            driver.findElement(By.name("LastName")).sendKeys(LName);
            driver.findElement(By.name("EmailID")).sendKeys(Email);
            driver.findElement(By.name("MobNo")).sendKeys(MNumb);
            driver.findElement(By.name("Company")).sendKeys(CName);
            Thread.sleep(2000);
            
            driver.findElement(By.xpath("//input[contains(@type,'submit')]")).click();
            Thread.sleep(2000);
            driver.switchTo().alert().accept();
        }
        reader.close();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}