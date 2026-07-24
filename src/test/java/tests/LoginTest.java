package tests;

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

    @Test
    public void incorrectLogin() {
        loginPage.open();
        loginPage.login("Standart_user", "secret_sauce");
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(),
                "Epic sadface: Username and password do not match any user in this service");
        driver.quit();
    }

    @Test
    public void incorrectPassword() {
        loginPage.open();
        loginPage.login("standard_user", "Secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(),
                "Epic sadface: Username and password do not match any user in this service");
        driver.quit();
    }

    @Test
    public void lockedUserLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(),
                "Epic sadface: Sorry, this user has been locked out.");
        driver.quit();
    }

    @Test
    public void emptyUserLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(),
                "Epic sadface: Username is required");
        driver.quit();
    }

    @Test
    public void emptyUserPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(),
                "Epic sadface: Password is required");
        driver.quit();
    }
}
