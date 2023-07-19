package Tests;

import Helpers.Helpers;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.edge.EdgeDriver;

public class KeyboardNavigationTest extends AbstractTest implements BrowserTestInterface {
    public KeyboardNavigationTest(EdgeDriver driver, Helpers helpers) {
        super(driver, helpers);
    }

    @Override
    public void runTest() {
        switchToNewTab();
        navigateToWikipedia();
        testPerformKeyboardNavigation();
    }

    private void switchToNewTab() {
        ((JavascriptExecutor) this.driver).executeScript("window.open()");
        String newTabHandle = this.helpers.getNewTabHandle(this.driver);
        this.driver.switchTo().window(newTabHandle);
    }

    private void navigateToWikipedia() {
        System.out.println("Beginning keyboard navigation test.");
        this.driver.get("https://en.wikipedia.org/wiki/Main_Page");
    }

    private void testPerformKeyboardNavigation() {
        boolean success;

        // Scroll down
        this.helpers.scrollMultipleTimes(this.driver, Keys.ARROW_DOWN, 5);
        success = this.helpers.isPageScrolledDown(this.driver);
        System.out.println("Scroll down: " + (success ? "Success" : "Failed"));

        // Scroll up
        this.helpers.scrollMultipleTimes(this.driver, Keys.ARROW_UP, 5);
        success = this.helpers.isPageScrolledUp(this.driver);
        System.out.println("Scroll up: " + (success ? "Success" : "Failed"));

        // Add more navigation actions as needed
    }
}