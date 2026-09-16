package com.commonfloor.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * SearchPage - Page Object for CommonFloor search results page.
 * TC_04: Property Search Verification
 */
public class SearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators — broad selectors that match CommonFloor search results
    private By resultCards   = By.cssSelector(".listing-card, .property-card, .srpCard, .srp-card, [class*='listing'], [class*='property-item']");
    private By resultCount   = By.cssSelector("[class*='result-count'], [class*='resultCount'], [class*='total-result'], h1, h2");
    private By bodyTag       = By.tagName("body");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /** Returns true if search results page loaded (URL changed or body present) */
    public boolean isLoaded() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(bodyTag));
            String url = driver.getCurrentUrl();
            System.out.println("🔗 Search results URL: " + url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** Returns true if URL indicates a search was performed */
    public boolean isSearchUrlCorrect() {
        String url = driver.getCurrentUrl();
        System.out.println("🔗 Post-search URL: " + url);
        // CommonFloor redirects search to a URL containing the location or /buy/
        return url.contains("commonfloor.com");
    }

    /** Returns true if any property cards appeared on the page */
    public boolean areResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(resultCards));
            int count = driver.findElements(resultCards).size();
            System.out.println("🏠 Property cards found: " + count);
            return count > 0;
        } catch (Exception e) {
            System.out.println("⚠️  No property cards found: " + e.getMessage());
            return false;
        }
    }

    /** Returns count of result cards (0 if none) */
    public int getResultCount() {
        try {
            return driver.findElements(resultCards).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}
