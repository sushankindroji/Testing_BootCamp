package com.commonfloor.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.time.Duration;

public class StepDefinitions {
    private WebDriver driver;

    @Before
    public void setUp() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Browser started");
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
    @Given("I open the CommonFloor website")
    public void iOpenTheCommonFloorWebsite() {
        driver.get("https://www.commonfloor.com/");
        System.out.println("Opened: " + driver.getCurrentUrl());
    }
    @Then("the page title should not be empty")
    public void thePageTitleShouldNotBeEmpty() {
        String title = driver.getTitle();
        System.out.println("Page title: " + title);
        Assert.assertNotNull("Page title should not be null", title);
        Assert.assertFalse("Page title should not be empty", title.isEmpty());
    }
    @And("the URL should contain {string}")
    public void theUrlShouldContain(String expected) {
        String url = driver.getCurrentUrl();
        System.out.println("Current URL: " + url);
        Assert.assertTrue("URL should contain '" + expected + "' but was: " + url,
                url.contains(expected));
    }
}
