import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;

public class PlaylistTest {

    public WebDriver driver=null;

    @BeforeEach
    public void Setup(){
        //le kell tölteni a ChromeDrivert is a saját chrome-hoz
        //érdemes mindig betenni a chrome drivert a projectbe
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");

        //WebDriverManager.chromedriver().setup();
        // configure options parameter to Chrome driver
        ChromeOptions o= new ChromeOptions();
        // add Incognito parameter
        o.addArguments("--incognito");
        //o.addArguments("--window-size=1920,1080");
        o.addArguments("--start-maximized");
        // add no UI parameter
        //o.addArguments("--headless");
        //window size
        //o.addArguments("--window-size=1920,1080");
        // para javasciptek letiltása
        o.addArguments("--no-sandbox");
        // DesiredCapabilities object
        DesiredCapabilities c = new DesiredCapabilities();
        //set capability to browser
        c.setCapability(ChromeOptions.CAPABILITY, o);
        driver = new ChromeDriver(o);

        driver.get("https://www.youtube.com/");

        //várakoztatás
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement agree= driver.findElement(By.xpath("//*[@aria-label='Reject the use of cookies and other data for the purposes described']"));
        agree.click();
    }

    @Test
    public void SearchBoxTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement searchBox = driver.findElement(By.xpath("//input[@id=\"search\"]"));

        Actions action = new Actions(driver);
        action.moveToElement(searchBox).perform();

        searchBox.click();
        searchBox.sendKeys("mmm mmm");
    }

    @AfterEach
    public void Dispose(){
        //csak az aktuális windowt zárja be
        //driver.close();
        //bezár mindent ami kapcsolódik a seleniumhoz
        driver.quit();
    }
}
