package tests;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Backpack",
                    "Sauce Labs Fleece Jacket");

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.pageIsOpen();
        productsPage.addToCart(4);
        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }
        assertEquals(productsPage.checkCounterValue(), 4, "Неверный ожидаемый индекс - не совпадает с актуальным");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)", "Цвет счётчика не верный");
    }
}
