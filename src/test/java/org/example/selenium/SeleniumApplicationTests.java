package org.example.selenium;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources",
		glue = {"stepDefinitions"},
		plugin = {"json:target/cucumber.json"}
)
public class SeleniumApplicationTests {
}

