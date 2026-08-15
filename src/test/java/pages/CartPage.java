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

    public CartPage(WebDriver driver) {
        super(driver);
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
