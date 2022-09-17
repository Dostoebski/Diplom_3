import model.dto.User;
import model.dto.UserFactory;
import model.ui.MainPage;
import model.ui.RestorePasswordPage;
import model.ui.RegisterPage;
import org.junit.*;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private static MainPage mainPage;
    private static RegisterPage registerPage;
    private static RestorePasswordPage restoringPage;
    private static User user;
    private static String accessToken;

    @BeforeClass
    public static void setUp() {
        registerPage = new RegisterPage(driver);
        mainPage = new MainPage(driver);
        restoringPage = new RestorePasswordPage(driver);

        user = UserFactory.getDefaultUser();
        accessToken = apiClient.createUser(user).then().extract().path("accessToken");
    }

    @Test
    public void loginFromMainPageByLoginButtonUserAuthorized() {
        step("Перейти на главную страницу");
        driver.get(baseUrl);

        step("Войти через кнопку Войти в аккаунт");
        boolean isDisplayed = mainPage
                .clickLoginButton()
                .login(user)
                .orderButtonIsDisplayed();

        step("Проверить, что произошел вход");
        assertTrue(isDisplayed);
    }

    @Test
    public void loginFromMainPageAccountLinkUserAuthorized() {
        step("Перейти на главную страницу");
        driver.get(baseUrl);

        step("Войти через кнопку Личный кабинет");
        boolean isDisplayed = mainPage
                .clickAccountLink()
                .login(user)
                .orderButtonIsDisplayed();

        step("Проверить, что произошел вход");
        assertTrue(isDisplayed);
    }

    @Test
    public void loginFromRestoringPageUserAuthorized() {
        step("Войти на страницу восстановления пароля");
        driver.get(baseUrl+"forgot-password");

        step("Войти по ссылке на странице восстановления пароля");
        boolean isDisplayed = restoringPage
                .clickLoginLink()
                .login(user)
                .orderButtonIsDisplayed();

        step("Проверить, что произошел вход");
        assertTrue(isDisplayed);
    }

    @Test
    public void loginFromRegisterPageUserAuthorized() {
        step("Войти на страницу регистрации");
        driver.get(baseUrl+"register");

        step("Войти по ссылке на странице регистрации");
        boolean isDisplayed = registerPage
                .clickLoginLink()
                .login(user)
                .orderButtonIsDisplayed();

        step("Проверить, что произошел вход");
        assertTrue(isDisplayed);
    }

    @After
    public void clearLocalStorage() {
        localStorage.clear();
    }

    @AfterClass
    public static void deleteUser() {
        if (accessToken != null) {
            apiClient.deleteUser(accessToken);
        }
    }
}
