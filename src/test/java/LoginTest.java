import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {
    // 1. открыть нужный браузер (Который есть)
    // 2. Зайти на сайт saucedemo.com

    @Test
    public void zipCode4Digits(){
        WebDriver browser_driver = new EdgeDriver();
        browser_driver.get("https://sharelane.com/cgi-bin/register.py");
        browser_driver.findElement(By.xpath("//*[@name='zip_code']")).sendKeys("9698");
        browser_driver.findElement(By.xpath("//input[@value='Continue']")).click();
        boolean isErrorsDisplayed = browser_driver.findElement(By.cssSelector(".error_message")).isDisplayed();
        String errorMessage = browser_driver.findElement(By.cssSelector(".error_message")).getText();
        assertTrue(isErrorsDisplayed);
        assertEquals(errorMessage, "Oops, error on page. ZIP code should have 5 digits");
        //browser_driver.get("https://saucedemo.com/"); // открытие, нужно закрытие
        browser_driver.quit(); // выход (закрытие браузера)
    }

    @Test
    public void zipCode5Digits(){
        WebDriver browser_driver = new EdgeDriver();
        browser_driver.get("https://sharelane.com/cgi-bin/register.py");
        browser_driver.findElement(By.xpath("//*[@name='zip_code']")).sendKeys("96981");
        browser_driver.findElement(By.xpath("//input[@value='Continue']")).click();
        browser_driver.findElement(By.cssSelector("[value='Register']")).isDisplayed();

        browser_driver.quit(); // выход (закрытие браузера)
    }
}
