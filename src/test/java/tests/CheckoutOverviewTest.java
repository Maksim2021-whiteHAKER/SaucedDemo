package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.List;

import static enums.TitleNaming.CHECKOUT_COMPLETE;
import static enums.TitleNaming.CHECKOUT_OVERVIEW;
import static org.testng.Assert.*;
import static user.UserFactory.withStandardUser;

@Epic("Интернет-магазин")
@Feature("Оформление заказа (обзор)")
@Owner("Vladimirov Maksim Vladimirovich | TG: @not_found_404_404 | EMAIL: maksim25082002x5@gmail.com")
@Severity(SeverityLevel.CRITICAL)
public class CheckoutOverviewTest extends BaseTest {
    private final List<String> goodsList =
            List.of("Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Backpack",
                    "Sauce Labs Fleece Jacket");

    private void navigateToCheckOverview() {
        loginPage
                .open()
                .login(withStandardUser());
        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }
        productsPage.counterCartClick();
        checkoutPage
                .clickCheckoutButton()
                .fillCheckoutForm("Maksim", "V.", "247184");
    }

    @Test
    @Story("Просмотр страницы обзора (Overview) оформления заказа")
    @Description("Проверка отображения страницы")
    public void pageCheckoutOverviewLoaded() {
        navigateToCheckOverview();
        assertEquals(checkoutOverviewPage.getNamePage(), CHECKOUT_OVERVIEW.getDisplayName());
    }

    @Test
    @Story("Математическая корректность итоговой суммы")
    @Description("Проверка того, что (Сумма цен товаров + Налог) строго равна отображаемой Итоговой сумме (Total)")
    public void verifyTotalPrice() {
        navigateToCheckOverview();
        List<String> prices = checkoutOverviewPage.getItemPrices();
        assertFalse(prices.isEmpty(), "Список цен пуст");
        double calculatedTotal = checkoutOverviewPage.calculationTotalPrice();
        assertEquals(checkoutOverviewPage.getTotalPrice(), "Total: $" + calculatedTotal, "Цена не совпадает с ожидаемой ценой");
    }

    @Test
    @Story("Кнопка 'Finish' отображается и кликабельна")
    @Description("Проверка что кнопка работает и осуществляет переход 'Checkout: Complete!'")
    public void checkFinishButton() {
        navigateToCheckOverview();
        assertTrue(checkoutOverviewPage.finishButtonDisplayed());
        checkoutOverviewPage.clickFinishButton();
        assertEquals(checkoutOverviewPage.getNamePage(), CHECKOUT_COMPLETE.getDisplayName(), "Ожидаемое название страницы не совпадает с актуальным");
    }
}
