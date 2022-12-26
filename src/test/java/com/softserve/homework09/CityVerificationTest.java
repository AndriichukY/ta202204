package com.softserve.homework09;

import com.softserve.homework07.ShoppingCartFlow;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class CityVerificationTest {

    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static final Long ONE_SECOND_DELAY = 1000L;
    private final String baseURL = "https://devexpress.github.io/devextreme-reactive/react/grid/docs/guides/filtering";
    private WebDriver driver;

    private static void delaySec() {
        delaySec(1);
    }

    private static void delaySec(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.navigate().to(baseURL);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseURL);
        acceptCookies();
    }

    public void acceptCookies(){
        WebElement cookieFooter = driver.findElement(By.xpath("//footer[@class = 'cookie-module--cookie--pJvQ8']"));

        if (cookieFooter != null) {
            cookieFooter.findElement(By.xpath(".//button")).click();
        }
        delaySec();
    }

    @Test
    public void checkShoppingCartFlow() {
        WebElement position = driver.findElement(By.cssSelector("div.embedded-demo"));
        Actions action = new Actions(driver);
        action.moveToElement(position).perform();

        driver.switchTo().frame(driver.findElement(By.cssSelector("div.tab-content iframe")));

        WebElement filterField = driver.findElement(By.xpath("//thead[@class='MuiTableHead-root  css-d59a7u'][1]//tr[2]//th[last()-1]//input"));
                 filterField.click();
                 filterField.clear();
                 filterField.sendKeys("L", Keys.ENTER);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
