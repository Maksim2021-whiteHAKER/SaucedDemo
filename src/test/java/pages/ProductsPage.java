package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Selectors;

public class ProductsPage extends BasePage {
    private static final String ADD_TO_CART = "//*[text()='%s']//ancestor::div//*[text()='Add to cart']";
    private final By counter = Selectors.css(".shopping_cart_badge");
    private final By cart = Selectors.css(".shopping_cart_link");
    private final By addToCartBtn = Selectors.text("Add to cart");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Добавляем товар в корзину")
    public void addToCart(String buyElem) {
        By goods = By.xpath(ADD_TO_CART.formatted(buyElem));
        driver.findElement(goods).click();
    }

    @Step("Добавляем товар в корзину")
    public void addToCart(int id) {
        driver.findElements(addToCartBtn).get(id).click();
    }

    @Step("Проверяем счётчик корзины")
    public int checkCounterValue() {
        return Integer.parseInt(driver.findElement(counter).getText());
    }

    @Step("Проверяем цвет счётчика")
    public String checkCounterColor() {
        return driver.findElement(counter).getCssValue("background-color");
    }

    @Step("Проверяем переход при клике на счётчик")
    public void counterCartClick() {
        driver.findElement(counter).click();
    }

    @Step("Проверяем переход при клике на корзину")
    public void enterToCart() {
        driver.findElement(cart).click();
    }
}
