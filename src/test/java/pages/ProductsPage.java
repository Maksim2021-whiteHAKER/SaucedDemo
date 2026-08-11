package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        wait.until(ExpectedConditions.elementToBeClickable(goods)).click();
    }

    @Step("Добавляем товар в корзину")
    public void addToCart(int id) {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        driver.findElements(addToCartBtn).get(id).click();
    }

    @Step("Проверяем счётчик корзины")
    public int checkCounterValue() {
        return Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(counter)).getText());
    }

    @Step("Проверяем цвет счётчика")
    public String checkCounterColor() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(counter)).getCssValue("background-color");
    }

    @Step("Проверяем переход при клике на счётчик")
    public void counterCartClick() {
        wait.until(ExpectedConditions.elementToBeClickable(counter)).click();
    }

    @Step("Проверяем переход при клике на корзину")
    public void enterToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cart)).click();
    }
}
