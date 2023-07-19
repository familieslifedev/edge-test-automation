package Tests;

import Helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.awt.*;
import java.awt.event.InputEvent;

public class BookmarkTest extends AbstractTest implements BrowserTestInterface {
    public BookmarkTest(EdgeDriver driver, Helpers helpers) {
        super(driver, helpers);
    }

    @Override
    public void runTest() {
        System.out.println("Beginning bookmarking test.");
        openBing();
        testBookmarkPage();
        testOpenBookmarksPage();
        testBookmarkSaved();
    }

    private void openBing() {
        System.out.println("Opening Bing...");
        this.driver.get("https://www.bing.com");
        this.helpers.sleep(Helpers.SHORT_SLEEP_DURATION);
    }

    private void testBookmarkPage() {
        System.out.println("Bookmarking the page...");
        Robot robot;
        try {
            robot = new Robot();

            int x = 1189;
            int y = 93;
            moveAndClickMouse(robot, x, y);

            x = 1159;
            y = 251;
            moveAndClickMouse(robot, x, y);

            this.helpers.sleep(Helpers.SHORT_SLEEP_DURATION);
        } catch (AWTException e) {
            e.getMessage();
        }
    }

    private void moveAndClickMouse(Robot robot, int x, int y) {
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        this.helpers.sleep(Helpers.SHORT_SLEEP_DURATION);
    }

    private void testOpenBookmarksPage() {
        System.out.println("Opening bookmarks page...");
        ((JavascriptExecutor) this.driver).executeScript("window.open()");
        String favouritesHandle = this.helpers.getNewTabHandle(this.driver);
        this.driver.switchTo().window(favouritesHandle);
        this.driver.get("edge://favorites");
    }

    private void testBookmarkSaved() {
        System.out.println("Checking if the bookmark is saved and accessible...");
        boolean isBookmarkSaved = false;
        try {
            WebElement bookmarkLink = this.driver.findElement(By.tagName("body"));
            String result = bookmarkLink.getText();
            isBookmarkSaved = result.contains("bing");
        } catch (NoSuchElementException e) {
            handleBookmarkNotFoundException(e);
        }

        if (isBookmarkSaved) {
            System.out.println("Bookmark is saved and accessible.");
        } else {
            System.out.println("Bookmark is not saved and/or accessible.");
        }
    }

    private void handleBookmarkNotFoundException(NoSuchElementException e) {
        String error = e.getMessage();
        if (error != null) {
            String firstLine = error.split(System.lineSeparator())[0];
            System.out.println("Bookmark element not found. Bookmark might not be saved or accessible.");
            System.out.println("More information: " + firstLine);
        }
    }
}
