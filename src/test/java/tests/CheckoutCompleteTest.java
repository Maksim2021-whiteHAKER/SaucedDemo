package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.List;

import static enums.TitleNaming.CHECKOUT_COMPLETE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withStandardUser;

@Epic("Интернет-магазин")
@Feature("Оформление заказа (завершено)")
@Owner("Vladimirov Maksim Vladimirovich | TG: @not_found_404_404 | EMAIL: maksim25082002x5@gmail.com")
@Severity(SeverityLevel.CRITICAL)
public class CheckoutCompleteTest extends BaseTest {
    private final List<String> goodsList =
            List.of("Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Backpack",
                    "Sauce Labs Fleece Jacket");

    private void navigateToCheckComplete() {
        loginPage
                .open()
                .login(withStandardUser());
        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }
        productsPage.counterCartClick();
        checkoutPage.clickCheckoutButton();
        checkoutPage.fillCheckoutForm("Maksim", "V.", "247184");
    }

    @Test
    @Story("Просмотр страницы завершения оформления заказа")
    @Description("Проверка отображения страницы")
    public void pageCheckoutCompleteLoaded() {
        navigateToCheckComplete();
        checkoutOverviewPage.clickFinishButton();
        assertEquals(checkoutCompletePage.getNamePage(), CHECKOUT_COMPLETE.getDisplayName(), "Ожидаемое название страницы не совпадает с актуальным");
        assertTrue(checkoutCompletePage.backHomeButtonDisplayed());
        assertEquals(checkoutCompletePage.getFinalText(), "Thank you for your order!", "Ожидаемый текст не совпадает с актуальным");
    }
}
