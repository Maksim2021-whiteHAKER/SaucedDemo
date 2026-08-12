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
import static user.UserFactory.*;

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
        String browserName = System.getProperty("browser", browser != null ? browser : "edge").toLowerCase();

        boolean isCiEnviroment = System.getenv("CI") != null || "true".equalsIgnoreCase(System.getProperty("CI"));

        String headlessProperty = System.getProperty("headless");
        boolean isHeadless = headlessProperty != null ? Boolean.parseBoolean(headlessProperty) : isCiEnviroment;
        if (browserName.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--inprivate");
            if (!isHeadless) options.addArguments("--start-maximized");
            if (isHeadless) options.addArguments("--headless=new");
            driver = new EdgeDriver(options);
        } else if (browserName.equals("yandex")) {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
            options.addArguments("--incognito");
            if (!isHeadless) options.addArguments("--start-maximized");
            if (isHeadless) options.addArguments("--headless=new");
            driver = new ChromeDriver(options);
        } else {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            if (isHeadless) options.addArguments("--headless=new");
            driver = new ChromeDriver(options);
        }

        context.setAttribute("driver", driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        clearCartIfNotEmpty();
    }

    @Step("Очистка корзины перед тестом")
    private void clearCartIfNotEmpty() {
        try {
            loginPage
                    .open()
                    .login(withStandardUser());
            productsPage.enterToCart();
            cartPage.clearCart();
        } catch (Exception e) {
            System.out.println("ошибка: " + e);
        }
    }

    @Step("Закрытие браузера")
    @AfterMethod(alwaysRun = true)
    public void exit() {
        if (driver != null){
            driver.quit();
        }
    }
}
