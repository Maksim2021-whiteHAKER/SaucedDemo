package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasePage.*;
import utils.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.UserFactory.*;
import static enums.TitleNaming.*;

public class LoginTest extends BaseTest {

    @Test public void pageLoginLoaded() {
        loginPage.open();
        assertEquals(loginPage.getNameElementAcceptsNamesOnPage(), "Accepted usernames are:", "Элемент не найден 'Accepted usernames are:'");
    }

    @Test
    public void testLogin() {
        loginPage.open();
        loginPage.login(withStandardUser());

        assertTrue(loginPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName(),
                "Название страницы не соответствует ожидаемому результату");
    }

    @DataProvider
    public Object[][] incorrectData() {
        return new Object[][]{
                {withWrongLoginUser(), "Epic sadface: Username and password do not match any user in this service"},
                {withWrongPasswordUser(), "Epic sadface: Username and password do not match any user in this service"},
                {withLockedUser(), "Epic sadface: Sorry, this user has been locked out."},
                {new User("", "secret_sauce"), "Epic sadface: Username is required"},
                {new User("standard_user", ""), "Epic sadface: Password is required"}
        };

    }

    @Test(dataProvider = "incorrectData")
    public void incorrectLogin(User user, String errorMsg) {
        loginPage.open();
        loginPage.login(user);
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), errorMsg);
    }
}
