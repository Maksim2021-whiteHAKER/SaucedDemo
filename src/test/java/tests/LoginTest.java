package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;
import static enums.TitleNaming.*;

@Epic("Интернет-магазин")
@Feature("Авторизация")
@Owner("Vladimirov Maksim Vladimirovich | TG: @not_found_404_404 | EMAIL: maksim25082002x5@gmail.com")
@Severity(SeverityLevel.BLOCKER)
@TmsLink("SaucedDemo")
public class LoginTest extends BaseTest {
    @Story("Просмотр страницы авторизации")
    @Description("Проверка наличия отображения всех элементов страницы")
    @Test
    public void pageLoginLoaded() {
        loginPage.open();
        assertEquals(loginPage.getNameElementAcceptsNamesOnPage(), "Accepted usernames are:", "Элемент не найден 'Accepted usernames are:'");
    }

    @Story("Успешная авторизация")
    @Description("Проверка успешного входа в систему с валидными учетными данными стандартного пользователя и перехода на страницу товаров")
    @Test
    public void testLogin() {
        loginPage
                .open()
                .login(withStandardUser());

        assertTrue(productsPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName(), "Название страницы не соответствует ожидаемому результату");
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

    @Story("Негативные сценарии авторизации")
    @Description("Проверка отображения корректных сообщений об ошибках при вводе невалидных данных (неверные логин/пароль, заблокированный пользователь, пустые)")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "incorrectData")
    public void incorrectLogin(User user, String expectedErrMsg) {
        loginPage
                .open()
                .login(user);
        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), expectedErrMsg,  "Текст не совпадает с ожидаемым текстом ошибки");
    }
}
