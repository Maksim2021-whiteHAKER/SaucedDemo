package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;
import static user.UserFactory.withStandardUser;

import static org.testng.Assert.assertEquals;
import static enums.TitleNaming.*;

@Epic("Интернет-магазин")
@Feature("Каталог товаров")
public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Backpack",
                    "Sauce Labs Fleece Jacket");

    @Story("Проверяем загружена ли страница каталога товаров")
    @Description("Проверка успешного открытия страницы каталога товаров после авторизации")
    @Test
    public void pageProductsLoaded() {
        loginPage.open();
        loginPage.login(withStandardUser());
        assertTrue(productsPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName());
    }

    @Story("Добавление товаров в корзину")
    @Description("Проверка корректного обновления счетчика корзины и его цвета при добавлении нескольких товаров")
    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(withStandardUser());
        productsPage.pageIsOpen();
        productsPage.addToCart(4);
        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }
        assertEquals(productsPage.checkCounterValue(), 4, "Неверный ожидаемый индекс - не совпадает с актуальным");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)", "Цвет счётчика не верный");
    }
}
