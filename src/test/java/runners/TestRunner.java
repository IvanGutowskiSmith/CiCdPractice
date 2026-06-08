package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Where your Gherkin files live
        glue = "steps",                           // Where your step definition code lives
        plugin = {"pretty", "html:target/cucumber-reports.html"} // Generates a clean HTML report
)
public class TestRunner {
}