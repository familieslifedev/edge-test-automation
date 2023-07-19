package Tests;

import Helpers.Helpers;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Iterator;
import java.util.Set;

public class TabResponsivenessTest extends AbstractTest implements BrowserTestInterface {

    private String firstTabHandle = this.driver.getWindowHandle();

    public TabResponsivenessTest(EdgeDriver driver, Helpers helpers) {
        super(driver, helpers);
    }

    @Override
    public void runTest() {
        int numOfTabs = 10;

        System.out.println("Beginning tab responsiveness test.");
        for (int i = 1; i <= numOfTabs; i++) {
            try {
                openNewTab();
                switchToNewTab();
                waitForTabSwitch();
                closeCurrentTab();
                switchToFirstTab();
                System.out.println("Successful tab iteration.");
            } catch (NoSuchWindowException e) {
                System.out.println("Error occurred during tab iteration: " + e.getMessage());
                // Handle the exception as needed
            }
        }
    }

    private void openNewTab() {
        try {
            ((JavascriptExecutor) this.driver).executeScript("window.open()");
        } catch (NoSuchWindowException e) {
            System.out.println("Error occurred while opening a new tab: " + e.getMessage());
            // Handle the exception as needed
        }
    }

    private void switchToNewTab() {
        try {
            String newTabHandle = this.helpers.getNewTabHandle(this.driver);
            this.driver.switchTo().window(newTabHandle);
        } catch (NoSuchWindowException e) {
            System.out.println("Error occurred while switching to a new tab: " + e.getMessage());
            // Handle the exception as needed
        }
    }

    private void waitForTabSwitch() {
        try {
            this.helpers.sleep(Helpers.QUICK_SLEEP_DURATION);
        } catch (NoSuchWindowException e) {
            System.out.println("Error occurred while waiting for tab switch: " + e.getMessage());
            // Handle the exception as needed
        }
    }

    private void closeCurrentTab() {
        try {
            this.driver.close();
        } catch (NoSuchWindowException e) {
            System.out.println("Error occurred while closing the current tab: " + e.getMessage());
            // Handle the exception as needed
        }
    }

    private void switchToFirstTab() {
        try {
            Set<String> windowHandles = this.driver.getWindowHandles();
            Iterator<String> iterator = windowHandles.iterator();
            String firstTabHandle = iterator.next();
            this.driver.switchTo().window(firstTabHandle);

            // Switch back to the first tab
            this.driver.switchTo().window(firstTabHandle);
        } catch (NoSuchWindowException e) {
            System.out.println("Error occurred while switching to the first tab: " + e.getMessage());
            // Handle the exception as needed
        }
    }

}