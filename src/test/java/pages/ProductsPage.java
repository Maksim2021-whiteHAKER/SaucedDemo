package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Selectors;

public class ProductsPage extends BasePage {
    private static final String ADD_TO_CART = "//*[text()='%s']//ancestor::div//*[text()='Add to cart']";
    private final By pageName = Selectors.dataTest("title");
    private final By counter = Selectors.css(".shopping_cart_badge");
    private final By addToCartBtn = Selectors.text("Add to cart");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean pageIsOpen() {
        return driver.findElement(pageName).isDisplayed();
    }

    public String getNamePage() {
        return driver.findElement(pageName).getText();
    }

    public void addToCart(String buyElem) {
        By goods = By.xpath(ADD_TO_CART.formatted(buyElem));
        driver.findElement(goods).click();
    }

    public void addToCart(int id) {
        driver.findElements(addToCartBtn).get(id).click();
    }

    public int checkCounterValue() {
        return Integer.parseInt(driver.findElement(counter).getText());
    }

    public String checkCounterColor() {
        return driver.findElement(counter).getCssValue("background-color");
    }

    public void switchToCart() {
        driver.findElement(counter).click();
    }
}
