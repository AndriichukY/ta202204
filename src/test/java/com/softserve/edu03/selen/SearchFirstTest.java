package com.softserve.edu03.selen;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchFirstTest {
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static final Long ONE_SECOND_DELAY = 1000L;

    private static void presentationSleep() {
        presentationSleep(1);
    }

    private static void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void checkMac() {
//        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        //
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        driver.manage().window().maximize();
        presentationSleep(); // For Presentation ONLY
        //driver.get(BASE_URL);
        driver.navigate().to(BASE_URL);
        presentationSleep(); // For Presentation ONLY
        //
        List<WebElement> elements = driver.findElements(By.className("form-control"));
        System.out.println("***elements.size() = " + elements.size());
        //
        List<WebElement> elements2 = driver.findElements(By.className("form-Acontrol"));
        System.out.println("***elements2.size() = " + elements2.size());
        if (elements2.size() == 0) {
            // Develop Custom Exception
            //throw new RuntimeException("Element(s) not found");
            System.out.println("\tElement(s) not found");
        }
        //
        // Choose Curency
        driver.findElement(By.cssSelector("button.btn.btn-link.dropdown-toggle")).click();
        presentationSleep(); // For Presentation ONLY
        //driver.findElement(By.cssSelector("button[name='USD']")).click();
        driver.findElement(By.name("USD")).click();
        presentationSleep(); // For Presentation ONLY
        //
        // Steps
        //driver.findElement(By.cssSelector("#search > input")).click();
        driver.findElement(By.name("search")).click();
        //driver.findElement(By.cssSelector("#search > input")).clear();
        driver.findElement(By.name("search")).clear();
        //driver.findElement(By.cssSelector("#search > input")).sendKeys("mac");
        driver.findElement(By.name("search")).sendKeys("mac");
        presentationSleep(); // For Presentation ONLY
        //
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
        presentationSleep(); // For Presentation ONLY
        //
        // Check
        WebElement price = driver.findElement(By
                .xpath("//a[text()='MacBook']/../following-sibling::p[@class='price']"));
        Assert.assertTrue(price.getText().contains("$602.00"));
        System.out.println("***contains: " + price.getText());
        presentationSleep(); // For Presentation ONLY
        //
        driver.quit(); // close browser, stop server
    }
}