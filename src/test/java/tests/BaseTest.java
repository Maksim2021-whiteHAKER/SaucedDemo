package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import utils.TestListener;

import java.time.Duration;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    public WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    CheckoutOverviewPage checkoutOverviewPage;
    CheckoutCompletePage checkoutCompletePage;

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("edge") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--inprivate");
            options.addArguments("--start-maximized");
            options.addArguments("--headless");
            driver = new EdgeDriver(options);
        } else if (browser.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }

        context.setAttribute("driver", driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
    }

    @Step("Закрытие браузера")
    @AfterMethod(alwaysRun = true)
    public void exit() {
        if (driver != null){
            driver.quit();
        }
    }
}