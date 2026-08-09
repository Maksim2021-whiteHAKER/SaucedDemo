package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;
import static user.UserFactory.withStandardUser;

import static enums.TitleNaming.PRODUCTS;

@Epic("Интернет-магазин")
@Feature("Каталог товаров")
@Severity(SeverityLevel.NORMAL)
public class ProductsTest extends BaseTest {
    private final List<String> goodsList =
            List.of("Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Backpack",
                    "Sauce Labs Fleece Jacket");

    private void navigateToProducts() {
        loginPage
                .open()
                .login(withStandardUser());
        assertTrue(productsPage.pageIsOpen());
    }

    @Test
    @Story("Просмотр каталога товаров")
    @Description("Проверка успешного открытия страницы каталога товаров после авторизации")
    public void pageProductsLoaded() {
        navigateToProducts();
        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName());
    }

    @Test
    @Story("Добавление товаров в корзину")
    @Description("Проверка корректного обновления счетчика корзины и его цвета при добавлении нескольких товаров")
    public void checkGoodsAdded() {
        navigateToProducts();
        productsPage.addToCart(4);
        for (String goodName : goodsList) {
            productsPage.addToCart(goodName);
        }
        assertEquals(productsPage.checkCounterValue(), 4, "Ожидаемое количество товаров не совпадает с актуальным");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)", "Цвет счётчика не совпадает с актуальным");
    }
}
