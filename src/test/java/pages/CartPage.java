package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение названий товаров")
    public ArrayList<String> getProductsTitle() {
        List<WebElement> allProductsTitles = driver.findElements(By.cssSelector(".inventory_item_name"));
        ArrayList<String> titles = new ArrayList<>();

        for (WebElement productBlock : allProductsTitles) {
            titles.add(productBlock.getText());
        }
        return titles;
    }
}
