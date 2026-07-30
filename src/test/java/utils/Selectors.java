package utils;

import org.openqa.selenium.By;

public class Selectors {

    public static By dataTest(String value) {
         return By.cssSelector("[data-test='%s'], [data-testid='%s']".formatted(value, value));
    }

    public static By id(String value) {
        return By.id(value);
    }

    public static By css(String value) {
        return By.cssSelector(value);
    }

    public static By xpath(String value) {
        return By.xpath(value);
    }

    public static By text(String value) {
        return By.xpath("//*[text()='%s']".formatted(value));
    }
}
