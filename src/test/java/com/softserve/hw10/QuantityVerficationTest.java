package com.softserve.hw10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class QuantityVerficationTest {

    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static final Long ONE_SECOND_DELAY = 1000L;
    private final String baseURL = "https://test.pkwteile.de/";
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
        WebElement cookieFooter = driver.findElement(By.xpath("//div[@class=\"popup-choose-cookies__first-block\"]"));

        if (cookieFooter != null) {
            cookieFooter.findElement(By.xpath("//span[@class=\"js-button_choose popup-choose-cookies__btn popup-choose-cookies__btn--full\"]")).click();
        }
        delaySec();
    }

    @Test
    public void verifyQuant () {

        driver.findElement(By.xpath("//form[@class=\"header__search-form\"]")).click();
        driver.findElement(By.xpath("//input[@type=\"text\"]")).sendKeys("Fuchsschwanz", Keys.ENTER);
        delaySec();
        String total = driver.findElement(By.xpath("//span[@class=\"js-products-count\"]")).getText();
        int totalInt = Integer.valueOf(total);
        //int elements = driver.findElements(By.xpath("//div[@class=\"brand-products\"]")).size();


        if (totalInt > 0) {
            int totalQ = 0;
            boolean hasLastPageBtn = false;
            //WebElement nextPage=driver.findElement(By.xpath("//span[@class=\"next\"]"));
            do {
                WebElement lastPageBtn = driver.findElement(By.xpath("//span[@class=\"next\"]"));
                hasLastPageBtn = lastPageBtn.isDisplayed();

                int elements = driver.findElements(By.xpath("//div[@class=\"brand-products\"]")).size();

                if (elements != 20) {
                    System.out.println("Test failed count less than 20");
                }

                totalQ = totalQ + elements;
                String link = driver.findElement(By.xpath("//span[@class=\"next\"]//a")).getAttribute("href");
                driver.get(link);
                delaySec();
            } while (hasLastPageBtn);

            /*int elementsOnLastPage = driver.findElements(By.xpath("//div[@class=\"brand-products\"]")).size();
            totalQ = totalQ + elementsOnLastPage;*/
            System.out.println(totalQ);
        }
    }
}

