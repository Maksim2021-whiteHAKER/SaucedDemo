package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("edge") String browser) {
        if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--inprivate");
            options.addArguments("--start-maximized");
            options.addArguments("headless");
            driver = new EdgeDriver(options);
        } else if (browser.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void exit() {
        if (driver != null){
            driver.quit();
        }
    }
}
