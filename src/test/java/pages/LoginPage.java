package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Selectors;
import user.User;

public class LoginPage extends BasePage{
    private final By loginInput = Selectors.id("user-name");
    private final By passwordInput = Selectors.id("password");
    private final By loginButton = Selectors.id("login-button");
    private final By error = Selectors.xpath("//h3");
    private final By acceptedUserNames = Selectors.xpath("//*[@id='login_credentials']/h4");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие браузер")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Авторизация под пользователем: {user.login} с паролем: *** ")
    public void login(User user) {
        driver.findElement(loginInput).sendKeys(user.getLogin());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        driver.findElement(loginButton).click();
    }

    @Step("Проверяем отображается ли сообщение об ошибке")
    public boolean isErrorDisplayed() {
        return driver.findElement(error).isDisplayed();
    }

    @Step("Получение текста ошибки")
    public String getErrorText() {
        return driver.findElement(error).getText();
    }

    @Step("Получение текста заголовка со списком разрешенных пользователей")
    public String getNameElementAcceptsNamesOnPage() {
        return driver.findElement(acceptedUserNames).getText();
    }
}
