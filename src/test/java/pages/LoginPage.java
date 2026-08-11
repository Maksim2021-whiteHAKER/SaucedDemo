package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Selectors;
import user.User;

public class LoginPage extends BasePage{
    private final By loginInput = Selectors.id("user-name");
    private final By passwordInput = Selectors.id("password");
    private final By loginButton = Selectors.id("login-button");
    private final By error = Selectors.xpath("//h3[@data-test='error']");
    private final By acceptedUserNames = Selectors.xpath("//*[@id='login_credentials']/h4");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие браузера")
    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Авторизация под пользователем: {user.login} с паролем: *** ")
    public LoginPage login(User user) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginInput)).sendKeys(user.getLogin());
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(user.getPassword());
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).click();
        return this;
    }

    @Step("Проверяем отображается ли сообщение об ошибке")
    public boolean isErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(error)).isDisplayed();
    }

    @Step("Получение текста ошибки")
    public String getErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(error)).getText();
    }

    @Step("Получение текста заголовка со списком разрешенных пользователей")
    public String getNameElementAcceptsNamesOnPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(acceptedUserNames)).getText();
    }
}
