package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Selectors;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutOverviewPage extends BasePage {
    private final By itemPrices = Selectors.css(".inventory_item_price");
    private final By finishButton = Selectors.id("finish");
    private final By totalPrice = Selectors.css("[data-test='total-label']");
    private final By taxPrice = Selectors.css("[data-test='tax-label']");


    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение цен")
    public List<String> getItemPrices() {
        wait.until(ExpectedConditions.presenceOfElementLocated(itemPrices));
        List<WebElement> pricesElements = driver.findElements(itemPrices);
        return pricesElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Step("Расчёт конечной цены")
    public double calculationTotalPrice() {
        List<String> prices = getItemPrices();
        String taxPriceText = getTaxPrice();

        double sumPrice = 0.0;
        for (String rawPrice : prices) {
            String cleaned = rawPrice.replaceFirst("^[^0-9]*", "");
            sumPrice += Double.parseDouble(cleaned);
        }

        double taxValue = Double.parseDouble(taxPriceText.replaceFirst("^[^0-9]*", ""));

        double calculatedTotal = sumPrice + taxValue;
        return (Math.round(calculatedTotal * 100.0) / 100.0);
    }

    @Step("Получение ожидаемой итоговой цены")
    public String getTotalPrice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice)).getText();
    }

    @Step("Получение налога")
    public String getTaxPrice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(taxPrice)).getText();
    }

    @Step("Клик по кнопке 'Finish'")
    public void clickFinishButton() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    @Step("Проверяем отображается ли кнопка 'Finish'")
    public boolean finishButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton)).isDisplayed();
    }
}
