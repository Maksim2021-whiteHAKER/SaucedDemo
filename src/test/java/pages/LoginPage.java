package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Selectors;
import utils.User;

public class LoginPage extends BasePage{
    private final By loginInput = Selectors.id("user-name");
    private final By passwordInput = Selectors.id("password");
    private final By loginButton = Selectors.id("login-button");
    private final By error = Selectors.xpath("//h3");
    private final By acceptedUserNames = Selectors.xpath("//*[@id='login_credentials']/h4");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(User user) {
        driver.findElement(loginInput).sendKeys(user.getLogin());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        driver.findElement(loginButton).click();
    }

    public boolean isErrorDisplayed() {
        return driver.findElement(error).isDisplayed();
    }

    public String getNameElementAcceptsNamesOnPage() {
        return driver.findElement(acceptedUserNames).getText();
    }

    public String getErrorText() {
        return driver.findElement(error).getText();
    }
}
