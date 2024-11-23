package org.example.selenium;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources",  // Path to the feature files
		glue = "stepDefinitions",        // Package where step definitions are located
		plugin = {"json:target/cucumber.json", "pretty"}  // Plugins for reporting
)
public class SeleniumApplicationTests {
	// This class is used to run the tests, and it's empty because the annotations handle the configuration.
}

