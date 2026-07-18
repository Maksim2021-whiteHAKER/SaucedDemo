import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

// 1. открыть нужный браузер (Который есть)
// 2. Зайти на сайт saucedemo.com

public class LoginTest {

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

    @Test
    public void testLogin(){
        WebDriver browser_driver = new EdgeDriver();
        browser_driver.get("https://saucedemo.com/");
        browser_driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        browser_driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        browser_driver.findElement(By.cssSelector("#login-button")).click();
        var title = browser_driver.findElement(By.xpath("//span[@data-test='title']"));
        assertTrue(title.isDisplayed());
        assertEquals(title.getText(), "Products");
        browser_driver.quit();
    }

    @Test
    public void incorrectLogin(){
        WebDriver browser_driver = new EdgeDriver();
        browser_driver.get("https://saucedemo.com/");
        browser_driver.findElement(By.cssSelector("#user-name")).sendKeys("Standard_user");
        browser_driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        browser_driver.findElement(By.cssSelector("#login-button")).click();
        var titleError = browser_driver.findElement(By.xpath("//h3"));
        assertTrue(titleError.isDisplayed());
        assertEquals(titleError.getText(), "Epic sadface: Username and password do not match any user in this service");
        browser_driver.quit();
    }

    @Test
    public void incorrectPassword(){
        WebDriver browser_driver = new EdgeDriver();
        browser_driver.get("https://saucedemo.com/");
        browser_driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        browser_driver.findElement(By.cssSelector("#password")).sendKeys("Secret_sauce");
        browser_driver.findElement(By.cssSelector("#login-button")).click();
        var titleError = browser_driver.findElement(By.xpath("//h3"));
        assertTrue(titleError.isDisplayed());
        assertEquals(titleError.getText(), "Epic sadface: Username and password do not match any user in this service");
        browser_driver.quit();
    }

}
