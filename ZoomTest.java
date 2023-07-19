package Tests;

import Helpers.Helpers;
import org.openqa.selenium.edge.EdgeDriver;

public class ZoomTest extends AbstractTest implements BrowserTestInterface {
    public ZoomTest(EdgeDriver driver, Helpers helpers) {
        super(driver, helpers);
    }

    @Override
    public void runTest() {
        System.out.println("Beginning zoom test.");
        navigateToWebpage();
        performZoomIn();
        performZoomOut();
        performResetZoom();
    }

    private void navigateToWebpage() {
        System.out.println("Navigating to the webpage: " + "https://www.mozilla.org/en-GB/");
        driver.get("https://www.mozilla.org/en-GB/");
        helpers.sleep(Helpers.SHORT_SLEEP_DURATION);
    }

    private void performZoomIn() {
        System.out.println("Performing zoom in.");
        helpers.zoomIn(driver);
        verifyZoom("Zoom In Value");
    }

    private void performZoomOut() {
        System.out.println("Performing zoom out.");
        helpers.zoomOut(driver);
        verifyZoom("Zoom Out Value");
    }

    private void performResetZoom() {
        System.out.println("Performing reset zoom.");
        helpers.resetZoom(driver);
        verifyZoom("Reset Zoom Value");
    }

    private void verifyZoom(String zoomType) {
        String zoomValue = helpers.getZoomValue(driver);
        System.out.println(zoomType + ": " + zoomValue);
        helpers.sleep(Helpers.SHORT_SLEEP_DURATION);
    }
}