package Tests;

import Helpers.Helpers;
import org.openqa.selenium.edge.EdgeDriver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class FilePrintTest extends AbstractTest implements BrowserTestInterface {
    public FilePrintTest(EdgeDriver driver, Helpers helpers) {
        super(driver, helpers);
    }

    @Override
    public void runTest() {
        String filePath = getTestFilePath();
        System.out.println("Beginning file printing test.");
        try {
            File file = new File(filePath);
            if (isDesktopSupported()) {
                Desktop desktop = getDesktopInstance();
                if (isPrintingSupported(desktop)) {
                    openFileForPrinting(desktop, file);
                    this.helpers.sleep(Helpers.SHORT_SLEEP_DURATION);
                    System.out.println("Printing file: " + filePath);
                } else {
                    System.out.println("Printing is not supported on this platform.");
                }
            } else {
                System.out.println("Desktop is not supported on this platform.");
            }
        } catch (IOException | AWTException e) {
            System.out.println("An error occurred while trying to print the file:" + e.getMessage());
        }
    }

    private String getTestFilePath() {
        return System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "testfile.pdf";
    }

    private boolean isDesktopSupported() {
        return Desktop.isDesktopSupported();
    }

    private Desktop getDesktopInstance() {
        return Desktop.getDesktop();
    }

    private boolean isPrintingSupported(Desktop desktop) {
        return desktop.isSupported(Desktop.Action.PRINT);
    }

    private void openFileForPrinting(Desktop desktop, File file) throws IOException, AWTException {
        desktop.print(file);
        Robot robot = new Robot();
        robot.mousePress(MouseEvent.MOUSE_CLICKED);
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_META);
    }
}