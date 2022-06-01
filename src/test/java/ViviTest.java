import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


import java.time.Duration;

public class ViviTest {
    static WebDriver driver;
    Actions action;

    @BeforeAll
    public static void Setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @BeforeEach
    public void Navigate() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "incognito");
        driver = new ChromeDriver(options);
        action = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://www.youtube.com");
    }

    @Test
    @Disabled
    public void AcceptDeclineCookies() {
        WebElement acceptButton = driver.findElement(By.xpath("//*[@aria-label=\"Reject the use of cookies and other data for the purposes described\"]"));
        acceptButton .click();

        //WebElement agree= driver.findElement(By.xpath("//*[@aria-label=\"Hozzájárulok a cookie-k és egyéb adatok itt ismertetett célokból történő használatához\"]"));
        //agree.click();
    }

    //@RepeatedTest()
    @Test
    public void NavigateToVideo() {
        AcceptDeclineCookies();
        WebElement searchBox = driver.findElement(By.xpath("//input[@id=\"search\"]"));
        action.moveToElement(searchBox).perform();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        searchBox.click();
        searchBox.sendKeys("cica");
    }


    @AfterEach
    public void Discard() {
        //driver.quit();
    }
}
