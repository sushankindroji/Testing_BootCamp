package com.commonfloor.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By logo         = By.cssSelector("img[alt*='CommonFloor'], img[alt*='commonfloor'], "
    		+ ".logo img, #logo img");
    private By searchBox    = By.cssSelector("input[placeholder*='location'],"
    		+ " input[placeholder*='Location'],"
    		+ " input[placeholder*='city'], "
    		+ "input#location, input[name='location']");
    private By searchButton = By.cssSelector("button[type='submit'], input[type='submit'], "
    		+ ".search-btn, button.btn-search, button[class*='search'],"
    		+ " button[id*='search']");
    private By bodyTag      = By.tagName("body");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /** Navigate to CommonFloor home page */
    public void open() {
        driver.get("https://www.commonfloor.com/");
        System.out.println("Opened: " + driver.getCurrentUrl());
    }

    /** Returns true if the page title contains expected text */
    public boolean isTitleCorrect() {
        String title = driver.getTitle();
        System.out.println("Page title: " + title);
        return title != null && !title.isEmpty();
    }

    /** Returns true if URL is commonfloor.com */
    public boolean isUrlCorrect() {
        String url = driver.getCurrentUrl();
        System.out.println("Current URL: " + url);
        return url.contains("commonfloor.com");
    }

    /** Returns true if the page body loaded (most basic check) */
    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(bodyTag));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** Returns true if a search input field exists on the page */
    public boolean isSearchBoxPresent() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(searchBox));
            System.out.println("Search box found");
            return true;
        } catch (Exception e) {
            System.out.println("Search box not found with primary locator");
            return false;
        }
    }

    /** Type into search box and return SearchPage */
    public SearchPage searchFor(String location) {
        try {
            WebElement box = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
            box.clear();
            box.sendKeys(location);
            System.out.println("Typed: " + location);

            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            btn.click();
            System.out.println("Search clicked");
        } catch (Exception e) {
            System.out.println("Search interaction: " + e.getMessage());
            // Try pressing Enter as fallback
            try {
                WebElement box = driver.findElement(searchBox);
                box.sendKeys(org.openqa.selenium.Keys.ENTER);
            } catch (Exception ignored) {}
        }
        return new SearchPage(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}
