package Tests;

import Helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserCrashAndRecoveryTest extends AbstractTest implements BrowserTestInterface {
    public BrowserCrashAndRecoveryTest(EdgeDriver driver, Helpers helpers) {
        super(driver, helpers);
    }

    @Override
    public void runTest() {
        EdgeOptions options = createEdgeOptions();
        WebDriver driver = launchEdgeBrowser(options);

        System.out.println("Beginning browser crash and recovery test.");
        try {
            navigateAndPerformActions(driver);
            try {
                simulateBrowserCrash();
            } catch (WebDriverException e) {
                handleCrashRecovery(driver);
                verifySessionRecovery(driver);
            }

        } catch (Exception e) {
            handleTestException(e);
        }
    }

    private EdgeOptions createEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-features=Crashpad");
        return options;
    }

    private WebDriver launchEdgeBrowser(EdgeOptions options) {
        return new EdgeDriver(options);
    }

    private void navigateAndPerformActions(WebDriver driver) {
        driver.get("https://www.bing.com");
        WebElement searchInput = driver.findElement(By.name("q"));
        String searchQuery = "selenium";
        searchInput.sendKeys(searchQuery);
        searchInput.submit();
    }

    private void simulateBrowserCrash() {
        this.helpers.sleep(Helpers.SHORT_SLEEP_DURATION);
        throw new WebDriverException("Simulated browser crash");
    }

    private void handleCrashRecovery(WebDriver driver) {
        System.out.println("Browser crashed and recovered.");
        // Perform any additional error handling or recovery steps
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("bing.com"));
        System.out.println("Browser recovery complete.");
        System.out.println("--------------------------------------");
    }

    private void verifySessionRecovery(WebDriver driver) {
        String recoveredURL = driver.getCurrentUrl();
        System.out.println("Recovered URL: " + recoveredURL);

        if (recoveredURL.contains("bing.com")) {
            System.out.println("Session is recovered on Bing's search page.");
        } else {
            System.out.println("Session is not recovered on Bing's search page.");
        }

        WebElement searchInput2 = driver.findElement(By.name("q"));
        String recoveredQuery = searchInput2.getAttribute("value");
        System.out.println("Recovered Query: " + recoveredQuery);

        String expectedQuery = "selenium";
        if (expectedQuery.equals(recoveredQuery)) {
            System.out.println("Recovered query matches the previous query.");
        } else {
            System.out.println("Recovered query does not match the previous query.");
        }
    }

    private void handleTestException(Exception e) {
            String errorMessage = e.getMessage();
            if (errorMessage != null) {
                String firstLine = errorMessage.split(System.lineSeparator())[0];
                System.out.println("An error occurred during the crash test: " + firstLine);
            } else {
                System.out.println("An unknown error occurred during the crash test.");
            }
        }
    }