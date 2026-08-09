package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Selectors;

public class CheckoutCompletePage extends BasePage {
    public final By backHomeButton = Selectors.id("back-to-products");
    public final By finalText = Selectors.xpath("//h2[text()='Thank you for your order!']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean backHomeButtonDisplayed() {
        return driver.findElement(backHomeButton).isDisplayed();
    }

    public String getFinalText() {
        return driver.findElement(finalText).getText();
    }
}
