package tests;

import io.qameta.allure.*;
import models.CheckoutData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withStandardUser;
import static enums.TitleNaming.CHECKOUT;
import static enums.TitleNaming.CHECKOUT_OVERVIEW;

@Epic("Интернет-магазин")
@Feature("Оформление заказа")
@Owner("Vladimirov Maksim Vladimirovich | TG: @not_found_404_404 | EMAIL: maksim25082002x5@gmail.com")
@Severity(SeverityLevel.CRITICAL)
public class CheckoutTest extends BaseTest {
    private final List<String> goodsList =
            List.of("Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Backpack",
                    "Sauce Labs Fleece Jacket");

    private void navigateCheckout() {
        loginPage.open();
        loginPage.login(withStandardUser());
        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }
        productsPage.counterCartClick();
        checkoutPage.clickCheckoutButton();
    }

    @Test
    @Story("Просмотр страницы оформления заказа")
    @Description("Проверка наличия отображения страницы")
    public void pageCheckoutLoaded() {
        navigateCheckout();
        assertTrue(checkoutPage.pageIsOpen());
        assertEquals(checkoutPage.getNamePage(), CHECKOUT.getDisplayName());
    }

    @Test
    @Story("Успешное заполнение формы оформления заказа")
    @Description("Проверка перехода на страницу обзора (Overview) после успешного заполнения формы")
    public void fillSuccessCheckoutInfo() {
        navigateCheckout();
        checkoutPage.fillCheckoutForm("Maksim", "V.", "247184");
        assertEquals(checkoutPage.getNamePage(), CHECKOUT_OVERVIEW.getDisplayName());
    }

    @DataProvider
    public Object[][] invalidCheckoutData() {
        return new Object[][]{
                {new CheckoutData("", "LastName", "247184", "Error: First Name is required")},
                {new CheckoutData("FirstName", "", "247184", "Error: Last Name is required")},
                {new CheckoutData("FirstName", "LastName", "", "Error: Postal Code is required")},
        };
    }

    @Test(dataProvider = "invalidCheckoutData")
    @Story("Негативные сценарии заполнения формы оформления заказа")
    @Description("Проверка отображения ошибок при пропуске обязательных полей")
    @Severity(SeverityLevel.NORMAL)
    public void checkoutWithMissingFields(CheckoutData data) {
        navigateCheckout();
        checkoutPage.fillCheckoutForm(data.getFirstName(), data.getLastName(), data.getPostalCode());
        assertTrue(checkoutPage.isErrorDisplayed(), "Ошибка не отобразилась при данных:"+ data.getFirstName()+" / "+data.getLastName()+" / "+data.getPostalCode());
        assertEquals(checkoutPage.getErrorText(), data.getExpectedErrMsg(), "Текст не совпадает с ожидаемым текстом ошибки");
    }
}
