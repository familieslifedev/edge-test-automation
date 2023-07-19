import Helpers.Helpers;
import Tests.*;
import org.openqa.selenium.edge.EdgeDriver;
import java.io.File;

public class Main {

    private static final String EDGE_DRIVER_PATH = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "msedgedriver";
    private final Helpers helpers;
    private final BrowserTestInterface[] tests;
    public Main(Helpers helpers, BrowserTestInterface[] tests) {
        this.helpers = helpers;
        this.tests = tests;
        // Set the Microsoft Edge WebDriver path
        System.setProperty("webdriver.edge.driver", EDGE_DRIVER_PATH);

    }

    public void runTests() {
        this.helpers.maximizeWindow();
        for (BrowserTestInterface test: this.tests) {
            test.runTest();
            this.helpers.printDivider();
            this.helpers.sleep(Helpers.LONG_SLEEP_DURATION);
        }    
    }

    public static void main(String[] args) {
        EdgeDriver edgeDriver = new EdgeDriver();
        Helpers helpers = new Helpers(edgeDriver);
        BrowserTestInterface[] tests = {
                new WebsiteOpeningTest(edgeDriver, helpers),
                new TabManagementTest(edgeDriver, helpers),
                new BookmarkTest(edgeDriver, helpers),
                new KeyboardNavigationTest(edgeDriver, helpers),
                new ZoomTest(edgeDriver, helpers),
                new TabResponsivenessTest(edgeDriver, helpers),
                new HistorySearchTest(edgeDriver, helpers),
                new FileDownloadTest(edgeDriver, helpers),
                new FilePrintTest(edgeDriver, helpers),
                new PDFAndImageTest(edgeDriver, helpers),
                new BrowserCrashAndRecoveryTest(edgeDriver, helpers)

        };

        Main testAutomation = new Main(helpers, tests);
        testAutomation.runTests();
    }
}


