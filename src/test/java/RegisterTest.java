import io.restassured.response.Response;
import model.dto.User;
import model.ui.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    private static RegisterPage registerPage;
    private static User user;
    private static String accessToken;

    @BeforeClass
    public static void setUp() {
        registerPage = new RegisterPage(driver);
    }

    @Before
    public void openPage() {
        user = User.getDefaultUser();

        step("Перейти на страницу регистрации");
        driver.get(baseUrl + "register");
    }

    @Test
    public void registerValidCredentialsUserCreated() {
        step("Зарегистрировать пользователя");
        boolean isDisplayed = registerPage
                .registerUser(user)
                .titleIsDisplayed();

        step("Проверить, что произошел редирект на страницу логина");
        assertTrue(isDisplayed);

        step("Проверить, что пользователь существует");
        Response response = apiClient.login(user);
        int statusCode = response.then().extract().statusCode();
        assertEquals("login must be successful", SC_OK, statusCode);
        accessToken = response.then().extract().path("accessToken");
    }

    @Test
    public void registerInvalidPasswordErrorDisplayed() {
        user.setPassword("123");

        step("Зарегистрировать пользователя");
        registerPage.registerUser(user);

        step("Проверить, что отобразилась ошибка ввода пароля");
        String error = registerPage.inputErrorText();
        assertEquals("Error message doesn't match", "Некорректный пароль", error);

        step("Проверить, что пользователя не существует");
        Response response = apiClient.login(user);
        int statusCode = response.then().extract().statusCode();
        assertEquals("login must fail", SC_UNAUTHORIZED, statusCode);
        accessToken = response.then().extract().path("accessToken");
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            apiClient.deleteUser(accessToken);
        }
    }

}
