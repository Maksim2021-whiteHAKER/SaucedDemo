package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final By loginInput = By.cssSelector("#user-name");
    private final By passwordInput = By.cssSelector("#password");
    private final By loginButton = By.cssSelector("#login-button");
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openSaucedDemo() {
        driver.get("https://saucedemo.com/");
    }

    // будет не тут но пока тут
    public void openShareLine() {
        driver.get("https://sharelane.com/cgi-bin/register.py");
    }

    public void login(final String userName, final String password) {
        driver.findElement(loginInput).sendKeys(userName);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
