import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class AllMusicParameterizedTest {
    private WebDriver driver;
    private String baseUrl;

    public AllMusicParameterizedTest(WebDriver driver) {
        this.driver = driver;
    }

    @Parameterized.Parameters(name = "Drivers {index}: {0}")
    public static Collection movieTitles() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Dropbox\\selenium\\chromedriver_win32\\chromedriver.exe");
//        System.setProperty("webdriver.ie.driver", "C:\\Dropbox\\selenium\\IEDriverServer_Win32_2.53.0\\IEDriverServer.exe");

        ArrayList<WebDriver> res = new ArrayList();
//        res.add(new InternetExplorerDriver());
        res.add(new HtmlUnitDriver());
//        res.add(new FirefoxDriver());
//        res.add(new ChromeDriver());

        return res;
    }

    @Before
    public void setUp() throws Exception {
        Logger.getLogger("").setLevel(Level.SEVERE);
        baseUrl = "http://www.allmusic.com";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testAllMusic() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.name("term")).click();
        driver.findElement(By.name("term")).clear();
        driver.findElement(By.name("term")).sendKeys("achtung baby");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("Achtung Baby")).click();
        assertTrue(driver.getTitle().matches(".*Achtung Baby.*"));
    }

//    @Before
//    public void setUp() throws Exception {
//        baseUrl = "http://www.imdb.com/";
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//    }
//
//    @Test
//    public void testImdbSearch() throws Exception {
//        driver.get(baseUrl);
//        String searchTerm = "Blade Runner";
//        driver.findElement(By.id("navbar-query")).click();
//        driver.findElement(By.id("navbar-query")).clear();
//        driver.findElement(By.id("navbar-query")).sendKeys(searchTerm);
//        driver.findElement(By.id("navbar-submit-button")).click();
//        driver.findElement(By.xpath("//td[2]/a")).click();
//        assertTrue(driver.getTitle().matches(".*" + searchTerm + ".*"));
//    }
//
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
