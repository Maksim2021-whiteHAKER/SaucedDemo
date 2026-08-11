package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Selectors;

public class CheckoutCompletePage extends BasePage {
    public final By backHomeButton = Selectors.id("back-to-products");
    public final By finalText = Selectors.xpath("//h2[text()='Thank you for your order!']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean backHomeButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(backHomeButton)).isDisplayed();
    }

    public String getFinalText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(finalText)).getText();
    }
}
