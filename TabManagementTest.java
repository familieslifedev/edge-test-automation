package Tests;

import Helpers.Helpers;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.edge.EdgeDriver;

public class TabManagementTest extends AbstractTest implements BrowserTestInterface {
    public TabManagementTest(EdgeDriver driver, Helpers helpers) {
        super(driver, helpers);
    }

    @Override
    public void runTest() {
        System.out.println("Beginning tab management test.");

        testOpenFirstTab();
        testOpenNewTab();
        testSwitchToNewTab();
        testOpenDifferentWebsite();
        testSwitchBackToFirstTab();
        testCloseNewTab();
        testSwitchBackToFirstTab();

        System.out.println("Tab management test complete.");
    }

    private void testOpenFirstTab() {
        this.driver.get("https://www.microsoft.com/en-gb/");
        System.out.println("First tab title: " + this.driver.getTitle());
    }

    private void testOpenNewTab() {
        ((JavascriptExecutor) this.driver).executeScript("window.open()");
        System.out.println("Number of tabs open: " + this.driver.getWindowHandles().size());
    }

    private void testSwitchToNewTab() {
        String firstTabHandle = this.driver.getWindowHandle();
        for (String handle : this.driver.getWindowHandles()) {
            if (!handle.equals(firstTabHandle)) {
                this.driver.switchTo().window(handle);
                break;
            }
        }
        System.out.println("Switched to the new tab. Current tab title: " + this.driver.getTitle());
    }

    private void testOpenDifferentWebsite() {
        this.driver.get("https://www.twitter.com");
        System.out.println("New tab title: " + this.driver.getTitle());
    }

    private void testSwitchBackToFirstTab() {
        String firstTabHandle = this.driver.getWindowHandles().iterator().next();
        this.driver.switchTo().window(firstTabHandle);
        System.out.println("Switched back to the first tab. Current tab title: " + this.driver.getTitle());
    }

    private void testCloseNewTab() {
        String secondTabHandle = this.driver.getWindowHandles().iterator().next();
        this.helpers.sleep(Helpers.SHORT_SLEEP_DURATION);
        this.driver.switchTo().window(secondTabHandle).close();
        System.out.println("Number of tabs open after closing the new tab: " + this.driver.getWindowHandles().size());
    }
}