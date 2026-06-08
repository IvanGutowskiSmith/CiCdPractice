package steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UiSteps {
    private WebDriver driver;
    //
    @When("the user navigates to {string}")
    public void navigateToUrl(String url) {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);

        // Configure Chrome to run without a visual screen for CI/CD pipelines
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Runs entirely in the background
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        // Selenium 4 automatically handles driver binary downloads (no chromedriver exe needed!)
        driver = new ChromeDriver();
        driver.get(url);
    }

    @Then("the page title should contain {string}")
    public void verifyTitle(String expectedTitle) {
        try {
            String actualTitle = driver.getTitle();
            Assert.assertTrue(actualTitle.contains(expectedTitle));
        } finally {
            if (driver != null) {
                driver.quit(); // Crucial: Always close the browser window
            }
        }
    }
}