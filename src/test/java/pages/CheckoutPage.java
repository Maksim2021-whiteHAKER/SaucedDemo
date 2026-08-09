package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        driver.findElement(checkoutBtn).click();
        return this;
    }

    @Step("Заполняем форму оформления заказа: Имя '{fName}', Фамилия '{lName}', Индекс '{pCode}'")
    public CheckoutPage fillCheckoutForm(String fName, String lName, String pCode) {
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(postalCode).sendKeys(pCode);
        driver.findElement(continueBtn).click();
        return this;
    }

    @Step("Проверяем отображается ли сообщение об ошибке")
    public boolean isErrorDisplayed() {
        return driver.findElement(errorMsgOnCheckoutPage).isDisplayed();
    }

    @Step("Получение текста ошибки")
    public String getErrorText() {
        return driver.findElement(errorMsgOnCheckoutPage).getText();
    }
}
