package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(productsPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), "Products",
                "Название страницы не соответствует ожидаемому результату");
        driver.quit();
    }

    @DataProvider
    public Object[][] incorrectData() {
        return new Object[][]{
                {"Standart_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"standard_user", "Secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"standard_user", "", "Epic sadface: Password is required"}
        };

    }

    @Test(dataProvider = "incorrectData")
    public void incorrectLogin(String loginUser, String passwordUser, String errorMsg) {
        loginPage.open();
        loginPage.login(loginUser, passwordUser);
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), errorMsg);
        driver.quit();
    }
}
