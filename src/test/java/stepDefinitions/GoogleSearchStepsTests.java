package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GoogleSearchStepsTests {
    public WebDriver driver;

    @Before
    public void createWebDriver() {
        // Configure Edge options
        EdgeOptions edgeOptions = new EdgeOptions();
        // Remove --headless if you want to debug interactively
        edgeOptions.addArguments("--headless");
        driver = new EdgeDriver(edgeOptions);
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the search box to be visible and interactable
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(string);

        // Wait for the search button to be clickable
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnK")));
        searchButton.click();
    }

    @Then("Search results display the word {string}")
    public void search_results_display_the_word(String string) {
        // Wait for the page title to contain the search word
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains(string));

        // Check if the search word is in the page title
        boolean isWordPresent = driver.getTitle().contains(string);
        System.out.println("Search result contains the word: " + isWordPresent);
        assert isWordPresent : "The word is not present in the search results";
    }
}
