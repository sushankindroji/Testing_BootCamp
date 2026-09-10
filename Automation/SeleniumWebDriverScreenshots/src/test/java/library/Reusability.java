package library;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Reusability {
	public static void capturedScreenshot(WebDriver driver,String screenShotNames) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./ScreenShots/" + screenShotNames +".png"));
			System.out.println("Captured ScreenShot - By Selenium webdriver");
		} catch (Exception e) {
			System.out.println("Exception While taking the screnshot");
		}
	}
}