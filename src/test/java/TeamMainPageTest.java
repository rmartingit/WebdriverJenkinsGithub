import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class TeamMainPageTest {
    private static final String LINKTEXT_RM = "Ricardo Martinho";

    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        Logger.getLogger("").setLevel(Level.OFF);
        driver = new HtmlUnitDriver();
        baseUrl = String.valueOf(System.getProperty("baseUrl"));
        if(baseUrl==null)
            baseUrl = "http://localhost:8080/tests";

        System.out.println("Base URL is: " + baseUrl);
    }

    @Test
    public void testIndexTitle() throws Exception {
        driver.get(baseUrl + "/index.html");


        assertTrue(driver.getTitle().equals("Team's main page"));

    }

    @Test
    public void testRMTeamMember() throws Exception {
        driver.get(baseUrl + "/index.html");
        WebElement linkRM =  (new WebDriverWait(driver, 10)).until(
                ExpectedConditions.presenceOfElementLocated(By.linkText(LINKTEXT_RM)));
        linkRM.click();

        assertNotNull("Image not found...", driver.findElement(By.xpath("//img[contains(@src,'/uploadsShortbio/qrcodes/qrcode-u02e74f10e0327ad868d138f2b4fdd6f0.png')]")));

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
