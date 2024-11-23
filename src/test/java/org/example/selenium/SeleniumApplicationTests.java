package org.example.selenium;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@SpringBootTest
@CucumberOptions(
		features = "src/test/resources",
		glue = {"stepDefinitions"},
		plugin = {"pretty","json:target/cucumber.json"}
)
public class SeleniumApplicationTests {
}

