package Tests;

import Helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class HistorySearchTest extends AbstractTest implements BrowserTestInterface {
    public HistorySearchTest(EdgeDriver driver, Helpers helpers) {
        super(driver, helpers);
    }

    @Override
    public void runTest() {
        System.out.println("Beginning history search test.");
        navigateToHistoryPage();
        testPerformSearch();
        verifySearchResultsContain();
    }

    private void navigateToHistoryPage() {
        this.driver.get("edge://history/");
    }

    private void testPerformSearch() {
        WebElement searchBox = this.driver.findElement(By.tagName("input"));
        searchBox.sendKeys("bing.com");
    }

    private void verifySearchResultsContain() {
        WebElement searchResults = this.driver.findElement(By.tagName("body"));
        String searchResultsText = searchResults.getText();

        if (searchResultsText.contains("Bing")) {
            System.out.println("History search test passed!");
        } else {
            System.out.println("History search test failed!");
        }
    }
}
