package tests;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class CartTest extends BaseTest  {
    List<String> goodsList =
            List.of("Sauce Labs Backpack",
                    "Sauce Labs Bike Light",
                    "Sauce Labs Bolt T-Shirt");

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.loginCorrect();
        for (String goodTittle : goodsList){
            productsPage.addToCart(goodTittle);
        }
        productsPage.switchToCart();
        assertFalse(cartPage.getProductsTittle().isEmpty());
        assertEquals(cartPage.getProductsTittle().size(), 3);
        assertTrue(cartPage.getProductsTittle().contains("Sauce Labs Backpack"));
        assertEquals(cartPage.getProductsTittle(), goodsList);
    }
}