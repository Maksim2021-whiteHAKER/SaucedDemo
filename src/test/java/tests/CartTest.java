package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.List;

import static user.UserFactory.*;

import static org.testng.Assert.*;

import static enums.TitleNaming.*;

@Epic("Интернет-магазин")
@Feature("Корзина")
@Severity(SeverityLevel.BLOCKER)
public class CartTest extends BaseTest {
    private final List<String> goodsList =
            List.of("Sauce Labs Backpack",
                    "Sauce Labs Bike Light",
                    "Sauce Labs Bolt T-Shirt");

    private void navigateCart() {
        loginPage
                .open()
                .login(withStandardUser());
    }

    @Test
    @Story("Просмотр корзины")
    @Description("Проверка успешного открытия страницы корзины товаров после выбора товаров")
    public void pageCartLoaded() {
        navigateCart();
        productsPage.enterToCart();
        assertTrue(cartPage.pageIsOpen());
        assertEquals(cartPage.getNamePage(), CART.getDisplayName());
    }

    @Test
    @Story("Заполненность корзины")
    @Description("Проверка того, что товары добавленные в каталоге, корректно перенеслись в корзину")
    public void checkGoodsAdded() {
        navigateCart();
        for (String goodTitle : goodsList) {
            productsPage.addToCart(goodTitle);
        }
        productsPage.counterCartClick();
        assertFalse(cartPage.getProductsTitle().isEmpty());
        assertEquals(cartPage.getProductsTitle().size(), 3);
        assertTrue(cartPage.getProductsTitle().contains("Sauce Labs Backpack"));
        assertEquals(cartPage.getProductsTitle(), goodsList);
    }
}
