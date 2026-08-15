package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Selectors;

public class CheckoutPage extends BasePage {
    private static final By checkoutBtn = Selectors.xpath("//button[text()='Checkout']");
    private final By firstName = Selectors.id("first-name");
    private final By lastName = Selectors.id("last-name");
    private final By postalCode = Selectors.id("postal-code");
    private final By continueBtn = Selectors.id("continue");
    private final By errorMsgOnCheckoutPage = Selectors.css("[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажимаем кнопку 'Checkout' для перехода к оформлению")
    public CheckoutPage clickCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return this;
    }

    @Step("Заполняем форму оформления заказа: Имя '{fName}', Фамилия '{lName}', Индекс '{pCode}'")
    public CheckoutPage fillCheckoutForm(String fName, String lName, String pCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode)).sendKeys(pCode);
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
        return this;
    }

    @Step("Проверяем отображается ли сообщение об ошибке")
    public boolean isErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsgOnCheckoutPage)).isDisplayed();
    }

    @Step("Получение текста ошибки")
    public String getErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsgOnCheckoutPage)).getText();
    }
}
