package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GoogleSearchSteps {
    public WebDriver driver;
    public FirefoxBinary firefoxBinary;

    @Before
    public void createWebDriver() {
        // Initialize FirefoxBinary
        firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        firefoxBinary.addCommandLineOptions("--no-sandbox");

        // Set GeckoDriver path (adjust to your system)
        System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");

        // Configure FirefoxOptions
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);

        // Initialize WebDriver
        driver = new FirefoxDriver(firefoxOptions);
    }

    @After
    public void quitWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I open google search page")
    public void i_open_google_search_page() {
        driver.get("https://www.google.com");
    }

    @When("I lookup the word {string}")
    public void i_lookup_the_word(String string) {
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys(string);
        driver.findElement(By.name("btnK")).click();
    }

    @Then("Search results display the word {string}")
    public void search_results_display_the_word(String string) {
        boolean isWordPresent = driver.getTitle().contains(string);
        System.out.println("Search result contains the word: " + isWordPresent);
        assert isWordPresent : "The word is not present in the search results";
    }
}
