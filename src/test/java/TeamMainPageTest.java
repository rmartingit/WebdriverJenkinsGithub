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

public class TeamMainPageTest {
    private static final String LINKTEXT_RM = "shortbio";

    //PAGES
    //private static final String URL_MAINPAGE = "http://localhost:8080/tests/index.html";

    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        Logger.getLogger("").setLevel(Level.OFF);
        driver = new HtmlUnitDriver();
        if((baseUrl = String.valueOf(System.getProperty("baseURL")))==null);
            baseUrl = "http://localhost:8080/tests";
    }

    @Test
    public void testRMTeamMemberLink() throws Exception {
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
