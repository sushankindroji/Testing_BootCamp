package multipleFrames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import utility.Helper;

public class MultipleFrames {

    @Test
    public void Frames() throws Exception {

        WebDriver driver = Helper.startBrowser("GC");

        // 1. Navigate to Naukri
        driver.get("https://www.naukri.com/");

        Thread.sleep(5000);

        // 2. Identify available frames
        int totalNoOfFrames = driver.findElements(By.tagName("iframe")).size();

        System.out.println("Number of Frames in Naukri: " + totalNoOfFrames);

        // 3. Display details of available frames
        for (int i = 0; i < totalNoOfFrames; i++) {

            WebElement frame = driver.findElements(By.tagName("iframe")).get(i);

            System.out.println("Frame " + i);
            System.out.println("ID: " + frame.getAttribute("id"));
            System.out.println("Name: " + frame.getAttribute("name"));
        }

        // ---------------------------------------------------------
        // Frame switching using WebElement
        // ---------------------------------------------------------

        if (totalNoOfFrames > 0) {

            // 4. Identify first iframe using WebElement
            WebElement firstFrame =
                    driver.findElements(By.tagName("iframe")).get(0);

            // 5. Switch to first frame using WebElement
            driver.switchTo().frame(firstFrame);

            System.out.println("Switched to first frame using WebElement");

            // 6. Return to main page
            driver.switchTo().defaultContent();

            System.out.println("Returned to main page using defaultContent()");
        }

        // ---------------------------------------------------------
        // Frame switching using index
        // ---------------------------------------------------------

        totalNoOfFrames = driver.findElements(By.tagName("iframe")).size();

        if (totalNoOfFrames > 1) {

            // 7. Switch to another frame using index
            driver.switchTo().frame(1);

            System.out.println("Switched to second frame using index");

            // 8. Return to main page
            driver.switchTo().defaultContent();

            System.out.println("Returned to main page");
        }

        // ---------------------------------------------------------
        // DemoQA Nested Frames
        // ---------------------------------------------------------

        // 9. Navigate to DemoQA Nested Frames page
        driver.navigate().to(
                "https://demoqa.com/nestedframes"
        );

        Thread.sleep(3000);

        // 10. Identify the Parent Frame
        WebElement parentFrame =
                driver.findElement(By.id("frame1"));

        // 11. Switch to Parent Frame
        driver.switchTo().frame(parentFrame);

        System.out.println("Switched to Parent Frame");

        // Validate Parent Frame text
        String parentText =
                driver.findElement(By.tagName("body")).getText();

        System.out.println("Parent Frame Text:");
        System.out.println(parentText);

        // 12. Identify and switch to Child Frame
        WebElement childFrame =
                driver.findElement(By.tagName("iframe"));

        driver.switchTo().frame(childFrame);

        System.out.println("Switched to Child Frame");

        // 13. Validate Child Frame text
        String childText =
                driver.findElement(By.tagName("body")).getText();

        System.out.println("Child Frame Text:");
        System.out.println(childText);

        // 14. Validate expected Child Frame text
        if (childText.contains("Child Iframe")) {
            System.out.println("Child Frame text validation PASSED");
        } else {
            System.out.println("Child Frame text validation FAILED");
        }

        // 15. Return from Child Frame to Parent Frame
        driver.switchTo().parentFrame();

        System.out.println("Returned to Parent Frame using parentFrame()");

        // 16. Validate Parent Frame text
        parentText =
                driver.findElement(By.tagName("body")).getText();

        if (parentText.contains("Parent frame")) {
            System.out.println("Parent Frame text validation PASSED");
        } else {
            System.out.println("Parent Frame text validation FAILED");
        }

        // 17. Return to main page
        driver.switchTo().defaultContent();

        System.out.println("Returned to main page using defaultContent()");

        // Verify main page
        String mainPageTitle = driver.getTitle();

        System.out.println("Main Page Title: " + mainPageTitle);

        driver.quit();
    }
}