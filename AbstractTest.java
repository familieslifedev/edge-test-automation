package Tests;

import Helpers.Helpers;
import org.openqa.selenium.edge.EdgeDriver;

abstract public class AbstractTest implements BrowserTestInterface {

    protected EdgeDriver driver;

    protected Helpers helpers;
    public AbstractTest(EdgeDriver driver, Helpers helpers) {
        this.driver = driver;
        this.helpers = helpers;
    }
}
