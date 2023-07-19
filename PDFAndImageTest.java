package Tests;

import Helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class PDFAndImageTest extends AbstractTest implements BrowserTestInterface {
    private final String pdfFilePath;
    private final String imageFilePath;

    public PDFAndImageTest(EdgeDriver driver, Helpers helpers) {
        super(driver, helpers);
        pdfFilePath = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "testfile.pdf";
        imageFilePath = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "kitchen.jpg";
    }

    @Override
    public void runTest() {
        System.out.println("Beginning PDF and image test.");

        navigateToPDFFile();
        verifyPDFDisplayed();
        downloadPDFFile();

        navigateToImageFile();
        verifyImageDisplayed();
        downloadImageFile();
    }

    private void navigateToPDFFile() {
        System.out.println("Navigating to PDF file: " + pdfFilePath);
        String absolutePath = "file://" + pdfFilePath;
        this.driver.navigate().to(absolutePath);
    }

    private void verifyPDFDisplayed() {
        WebElement pdfElement = this.driver.findElement(By.tagName("embed"));
        if (pdfElement.isDisplayed()) {
            System.out.println("PDF is displayed.");
        } else {
            System.out.println("PDF is not displayed.");
        }
    }

    private void downloadPDFFile() {
        System.out.println("Downloading PDF file.");
        String pdfUrl = this.driver.getCurrentUrl();
        this.driver.navigate().to(pdfUrl);
        helpers.sleep(Helpers.SHORT_SLEEP_DURATION);

        try {
            URL url = new URL(pdfUrl);
            InputStream inputStream = url.openStream();
            Files.copy(inputStream, Paths.get(pdfFilePath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("PDF file downloaded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while downloading the PDF file: " + e.getMessage());
        }
    }

    private void navigateToImageFile() {
        System.out.println("Navigating to image file: " + imageFilePath);
        String absolutePath = "file://" + imageFilePath;
        this.driver.navigate().to(absolutePath);
    }

    private void verifyImageDisplayed() {
        WebElement imageElement = this.driver.findElement(By.tagName("img"));
        if (imageElement.isDisplayed()) {
            System.out.println("Image is displayed.");
        } else {
            System.out.println("Image is not displayed.");
        }
    }

    private void downloadImageFile() {
        System.out.println("Downloading image file.");
        String imageUrl = this.driver.getCurrentUrl();
        this.driver.navigate().to(imageUrl);
        helpers.sleep(Helpers.SHORT_SLEEP_DURATION);

        try {
            URL url = new URL(imageUrl);
            InputStream inputStream = url.openStream();
            Files.copy(inputStream, Paths.get(imageFilePath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image file downloaded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while downloading the image file: " + e.getMessage());
        }
    }
}
