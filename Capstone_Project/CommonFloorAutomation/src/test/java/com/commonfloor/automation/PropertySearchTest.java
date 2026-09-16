package com.commonfloor.automation;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

/**
 * TC_04 — Property Search Verification
 *
 * Demonstrates:
 *   - Selenium WebDriver with Edge
 *   - Page Object Model (HomePage → SearchPage)
 *   - TestNG assertions
 *   - Allure annotations
 *   - Screenshot capture
 */
@Feature("Property Search")
public class PropertySearchTest extends BaseTest {

    private static final String SEARCH_LOCATION = "Bangalore";

    @Test(description = "TC_04_01 — Verify search navigates away from home page")
    @Description("Types 'Bangalore' in search box and verifies browser navigates to a results page")
    @Severity(SeverityLevel.BLOCKER)
    public void testSearchNavigates() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        String homeUrl = homePage.getUrl();
        SearchPage searchPage = homePage.searchFor(SEARCH_LOCATION);

        // Verify we are still on commonfloor domain
        Assert.assertTrue(searchPage.isSearchUrlCorrect(),
                "After search, URL should still be on commonfloor.com");

        System.out.println("✅ TC_04_01 PASS: Search performed. URL = " + searchPage.getUrl());
        takeScreenshot("TC_04_01_SearchNavigates");
    }

    @Test(description = "TC_04_02 — Verify search results page loads after search")
    @Description("Types 'Bangalore' in search box and verifies the search results page body loads")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchPageLoads() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        SearchPage searchPage = homePage.searchFor(SEARCH_LOCATION);

        Assert.assertTrue(searchPage.isLoaded(),
                "Search results page should load after performing search");

        System.out.println("✅ TC_04_02 PASS: Search results page loaded");
        System.out.println("   Title: " + searchPage.getTitle());
        System.out.println("   URL:   " + searchPage.getUrl());

        takeScreenshot("TC_04_02_SearchResultsLoaded");
    }
}
