package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void zipCode4Digits() {
        loginPage.openShareLine();
        driver.findElement(By.xpath("//*[@name='zip_code']")).sendKeys("9698");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        var errorMessage = driver.findElement(By.cssSelector(".error_message"));
        assertTrue(errorMessage.isDisplayed());
        assertEquals(errorMessage.getText(), "Oops, error on page. ZIP code should have 5 digits");
        driver.quit();
    }

    @Test
    public void zipCode5Digits() {
        loginPage.openShareLine();
        driver.findElement(By.xpath("//*[@name='zip_code']")).sendKeys("96981");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        driver.findElement(By.cssSelector("[value='Register']")).isDisplayed();
        driver.quit();
    }

    @Test
    public void testLogin() {
        loginPage.openSaucedDemo();
        loginPage.login("standard_user", "secret_sauce");
        var title = driver.findElement(By.xpath("//span[@data-test='title']"));
        assertTrue(title.isDisplayed());
        assertEquals(title.getText(), "Products");
        driver.quit();
    }

    @Test
    public void incorrectLogin() {
        loginPage.openSaucedDemo();
        loginPage.login("Standart_user", "secret_sauce");
        var titleError = driver.findElement(By.xpath("//h3"));
        assertTrue(titleError.isDisplayed());
        assertEquals(titleError.getText(), "Epic sadface: Username and password do not match any user in this service");
        driver.quit();
    }

    @Test
    public void incorrectPassword() {
        loginPage.openSaucedDemo();
        loginPage.login("standard_user", "Secret_sauce");
        var titleError = driver.findElement(By.xpath("//h3"));
        assertTrue(titleError.isDisplayed());
        assertEquals(titleError.getText(), "Epic sadface: Username and password do not match any user in this service");
        driver.quit();
    }

    @Test
    public void lockedUserLogin() {
        loginPage.openSaucedDemo();
        loginPage.login("locked_out_user", "secret_sauce");
        var titleError = driver.findElement(By.xpath("//h3"));
        assertTrue(titleError.isDisplayed());
        assertEquals(titleError.getText(), "Epic sadface: Sorry, this user has been locked out.");
        driver.quit();
    }

    @Test
    public void emptyUserLogin() {
        loginPage.openSaucedDemo();
        loginPage.login("", "secret_sauce");
        var titleError = driver.findElement(By.xpath("//h3"));
        assertTrue(titleError.isDisplayed());
        assertEquals(titleError.getText(), "Epic sadface: Username is required");
        driver.quit();
    }

    @Test
    public void emptyUserPassword() {
        loginPage.openSaucedDemo();
        loginPage.login("standard_user", "");
        var titleError = driver.findElement(By.xpath("//h3"));
        assertTrue(titleError.isDisplayed());
        assertEquals(titleError.getText(), "Epic sadface: Password is required");
        driver.quit();
    }
}
