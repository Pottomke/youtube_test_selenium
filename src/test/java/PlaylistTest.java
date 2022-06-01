import io.github.bonigarcia.wdm.WebDriverManager;
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
import java.util.concurrent.TimeUnit;

public class PlaylistTest {

    public WebDriver driver=null;

    @BeforeEach
    public void Setup(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--incognito");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();


        driver.get("https://www.youtube.com/");

        //várakoztatás
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement agree= driver.findElement(By.xpath("//*[@aria-label='Reject the use of cookies and other data for the purposes described']"));
        agree.click();
    }

    @Test
    public void SearchBoxTest(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
