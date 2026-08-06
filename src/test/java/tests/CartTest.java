package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import java.util.List;

import static user.UserFactory.*;

import static org.testng.Assert.*;

import static enums.TitleNaming.*;

@Epic("Интернет-магазин")
@Feature("Корзина")
public class CartTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Backpack",
                    "Sauce Labs Bike Light",
                    "Sauce Labs Bolt T-Shirt");

    @Test
    public void pageCartLoaded() {
        loginPage.open();
        loginPage.login(withStandardUser());
        productsPage.enterToCart();
        assertTrue(cartPage.pageIsOpen());
        assertEquals(cartPage.getNamePage(), CART.getDisplayName());
    }

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(withStandardUser());
        for (String goodTittle : goodsList) {
            productsPage.addToCart(goodTittle);
        }
        productsPage.counterCartClick();
        assertFalse(cartPage.getProductsTitle().isEmpty());
        assertEquals(cartPage.getProductsTitle().size(), 3);
        assertTrue(cartPage.getProductsTitle().contains("Sauce Labs Backpack"));
        assertEquals(cartPage.getProductsTitle(), goodsList);
    }
}
