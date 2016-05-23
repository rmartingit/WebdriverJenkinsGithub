import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class ImdbTests {
    private static final String SEARCHTERM = "Before Sunrise";

    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        Logger.getLogger("").setLevel(Level.SEVERE);
        driver = new HtmlUnitDriver();
        baseUrl = "http://www.imdb.com/";
    }

    @Test
    public void testImdbSearch() throws Exception {
        driver.get(baseUrl);

        WebElement searchbox =
                (new WebDriverWait(driver, 10)).until(
                        ExpectedConditions.presenceOfElementLocated(By.id("navbar-query")));
        searchbox.click();
        searchbox.clear();
        searchbox.sendKeys(SEARCHTERM);

        WebElement submitButton =
                (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("navbar-submit-button")));

        submitButton.click();

        WebElement topSearchResult =
                (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[2]/a")));
        topSearchResult.click();

        assertTrue("expected: " + SEARCHTERM + ", found: " + driver.getTitle(), driver.getTitle().matches(".*" + SEARCHTERM + ".*"));

//        assertTrue(driver.getTitle().matches(".*" + SEARCHTERM + ".*"));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
