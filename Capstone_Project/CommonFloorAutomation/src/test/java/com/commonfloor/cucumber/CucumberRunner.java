package com.commonfloor.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/CommonFloor.feature",
    glue     = "com.commonfloor.cucumber",
    plugin   = { "pretty", "html:target/cucumber-report.html" }
)
public class CucumberRunner {
    // This class intentionally left empty.
    // JUnit + Cucumber annotations do all the work.
}
