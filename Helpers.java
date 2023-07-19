package Helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Helpers {
    private final WebDriver driver;

    public static final int QUICK_SLEEP_DURATION = 500;

    public static final int SHORT_SLEEP_DURATION = 2000;

    public static final int LONG_SLEEP_DURATION = 5000;

    public Helpers(WebDriver driver) {
        this.driver = driver;
    }

    public void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printDivider() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }
    public void maximizeWindow() {
        this.driver.manage().window().maximize();
    }

    public void waitForFileDownload(String downloadPath, String expectedFileName) {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(120)); // Set an appropriate timeout value
        File downloadedFile = new File(downloadPath + File.separator + expectedFileName);

        wait.until(driver -> downloadedFile.exists());
    }

    public boolean isFileDownloaded(String downloadPath, String expectedFileName) {
        File downloadedFile = new File(downloadPath + File.separator + expectedFileName);
        return downloadedFile.exists();
    }

    public boolean isPageScrolledDown(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Long currentPosition = (Long) jsExecutor.executeScript("return window.pageYOffset;");
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Long newPosition = (Long) jsExecutor.executeScript("return window.pageYOffset;");
        return newPosition > currentPosition;
    }

    public boolean isPageScrolledUp(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Long currentPosition = (Long) jsExecutor.executeScript("return window.pageYOffset;");
        jsExecutor.executeScript("window.scrollTo(0, 0);");
        Long newPosition = (Long) jsExecutor.executeScript("return window.pageYOffset;");
        return newPosition < currentPosition;
    }

    public void zoomIn(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.body.style.zoom='125%'");
    }

    public void zoomOut(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.body.style.zoom='75%'");
    }

    public void resetZoom(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.body.style.zoom='100%'");
    }

    public String getZoomValue(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.body.style.zoom");
    }

    public String getNewTabHandle(WebDriver driver) {
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                return handle;
            }
        }
        return null;
    }

    public void scrollMultipleTimes(WebDriver driver, Keys key, int count) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < count; i++) {
            actions.sendKeys(key).perform();
            sleep(QUICK_SLEEP_DURATION); // Wait between each key press for better visibility
        }
    }
}
