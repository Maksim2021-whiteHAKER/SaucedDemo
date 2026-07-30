package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Selectors;

public class LoginPage extends BasePage{
    private final By loginInput = Selectors.id("user-name");
    private final By passwordInput = Selectors.id("password");
    private final By loginButton = Selectors.id("login-button");
    private final By error = Selectors.xpath("//h3");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void loginCorrect() {
        login("standard_user", "secret_sauce");
    }

    public void login(final String userName, final String password) {
        driver.findElement(loginInput).sendKeys(userName);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isErrorDisplayed() {
        return driver.findElement(error).isDisplayed();
    }

    public String getErrorText() {
        return driver.findElement(error).getText();
    }
}
