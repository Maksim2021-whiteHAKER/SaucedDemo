package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage  {
    private final By pageName = By.xpath("//span[@data-test='title']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean pageIsOpen() {
        return driver.findElement(pageName).isDisplayed();
    }

    public String getNamePage() {
        return driver.findElement(pageName).getText();
    }
}
