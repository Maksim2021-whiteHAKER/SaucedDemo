package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Selectors;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final By productTitle = Selectors.css(".inventory_item_name");
    private final By removeProduct = Selectors.xpath("//button[starts-with(@data-test, 'remove-')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Очищение корзины")
    public CartPage clearCart() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(productTitle));
            List<WebElement> removeButtons = driver.findElements(removeProduct);
            if (!removeButtons.isEmpty()) {
                removeButtons.get(0).click();
                wait.until(ExpectedConditions.stalenessOf(removeButtons.get(0)));
                driver.findElements(removeProduct);
            }
        } catch (Exception e) {
            System.out.println("ошибка: " + e);
        }
        return this;
    }

    @Step("Получение названий товаров")
    public ArrayList<String> getProductsTitle() {
        wait.until(ExpectedConditions.presenceOfElementLocated(productTitle));
        List<WebElement> allProductsTitles = driver.findElements(productTitle);
        ArrayList<String> titles = new ArrayList<>();

        for (WebElement productBlock : allProductsTitles) {
            titles.add(productBlock.getText());
        }
        return titles;
    }
}
