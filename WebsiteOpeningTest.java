package Tests;

import Helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebsiteOpeningTest extends AbstractTest implements BrowserTestInterface {
    public WebsiteOpeningTest(EdgeDriver driver, Helpers helpers) {
        super(driver, helpers);
    }

    @Override
    public void runTest() {
        String[] websites = {
                "https://www.google.com",
                "https://www.bing.com",
                "https://www.facebook.com",
                "https://www.reddit.com",
                "https://www.youtube.com"
        };

        System.out.println("Beginning website opening test.");
        for (String website : websites) {
            navigateToWebsite(website);
            testPageTitle();
            testLogoDisplay();
            testPageLoadTime();
            System.out.println("--------------------------------------");
            waitForNextWebsite();
        }
    }

    private void navigateToWebsite(String website) {
        this.driver.get(website);
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    private void testPageTitle() {
        String pageTitle = this.driver.getTitle();
        System.out.println("Page Title: " + pageTitle);
    }

    private void testLogoDisplay() {
        WebElement logoElement = this.driver.findElement(By.tagName("img"));
        boolean isLogoDisplayed = logoElement.isDisplayed();
        if (!isLogoDisplayed) {
            logoElement = this.driver.findElement(By.tagName("a"));
            isLogoDisplayed = logoElement.isDisplayed();
        }
        System.out.println("Is Logo Displayed: " + isLogoDisplayed);
    }

    private void testPageLoadTime() {
        long startTime = System.currentTimeMillis();
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        wait.until(this::isPageLoaded);
        long pageLoadTime = (System.currentTimeMillis() - startTime);
        System.out.println("Page Load Time: " + pageLoadTime + " milliseconds");
    }

    private boolean isPageLoaded(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript("return document.readyState").equals("complete");
    }



    private void waitForNextWebsite() {
        this.helpers.sleep(Helpers.SHORT_SLEEP_DURATION);
    }

}
