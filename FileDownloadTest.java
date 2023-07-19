package Tests;

import Helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;

public class FileDownloadTest extends AbstractTest implements BrowserTestInterface {

    public FileDownloadTest(EdgeDriver driver, Helpers helpers) {
        super(driver, helpers);
    }

    @Override
    public void runTest() {
        System.out.println("Beginning download test.");
        navigateToWebpage();
        clickDownloadButton();
        waitForFileDownload();
        assertFileDownloaded();
    }

    private void navigateToWebpage() {
        String webpageUrl = "https://www.google.co.uk/chrome/";
        this.driver.get(webpageUrl);
    }

    private void clickDownloadButton() {
        String downloadButtonXPath = "/html/body/div[3]/section[1]/div/div[4]/div/div[1]/div[2]/button";
        WebElement downloadButton = this.driver.findElement(By.xpath(downloadButtonXPath));
        downloadButton.click();
    }

    private void waitForFileDownload() {
        String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";
        String expectedFileName = "googlechrome.dmg";
        this.helpers.waitForFileDownload(downloadPath, expectedFileName);
    }

    private void assertFileDownloaded() {
        String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";
        String expectedFileName = "googlechrome.dmg";
        boolean isFileDownloaded = this.helpers.isFileDownloaded(downloadPath, expectedFileName);

        if (isFileDownloaded) {
            System.out.println("File is downloaded successfully!");
        } else {
            System.out.println("File download failed!");
        }
    }
}
