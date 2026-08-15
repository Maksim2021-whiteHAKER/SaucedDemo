package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import utils.PropertyReader;
import utils.Selectors;

public class BasePage {
    public static final String BASE_URL = PropertyReader.getProperty("saucedemo.url");
    private final By pageName = Selectors.dataTest("title");

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Проверяем открылась ли страница")
    public boolean pageIsOpen() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageName)).isDisplayed();
    }

    @Step("Получаем название страницы")
    public String getNamePage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageName)).getText();
    }
}
