package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.UserFactory.*;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        loginPage.open();
        loginPage.login(withStandardUser());

        assertTrue(productsPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), "Products",
                "Название страницы не соответствует ожидаемому результату");
    }

    @DataProvider
    public Object[][] incorrectData() {
        return new Object[][]{
                {withWrongLoginUser(), "Epic sadface: Username and password do not match any user in this service"},
                {withWrongPasswordUser(), "Epic sadface: Username and password do not match any user in this service"},
                {withVoidLoginUser(), "Epic sadface: Username is required"},
                {withLockedUser(), "Epic sadface: Sorry, this user has been locked out."},
                {withVoidPasswordUser(), "Epic sadface: Password is required"}
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
