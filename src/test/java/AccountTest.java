import io.restassured.response.Response;
import model.dto.User;
import model.ui.AccountPage;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountTest extends BaseTest {

    private static AccountPage accountPage;
    private static String accessToken;
    private static String refreshToken;

    @BeforeClass
    public static void setUp() {
        accountPage = new AccountPage(driver);
        // создать пользователя и получить токены
        User user = User.getDefaultUser();
        Response response = apiClient.createUser(user);
        accessToken = response.then().extract().path("accessToken");
        refreshToken = response.then().extract().path("refreshToken");
        // открыть легковесную страницу для работы с local storage
        driver.get(baseUrl+"favicon.ico");
    }

    @Before
    public void openAccountPage() {
        // записать токены в local storage
        localStorage
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken);
        // открыть страницу, как авторизованный пользователь
        driver.navigate().to(baseUrl+"account");
    }

    @Test
    public void redirectToAccountPageByAccountLinkWorked() {
        step("Перейти в конструктор по ссылке Конструктор");
        boolean isDisplayed = accountPage
                .clickConstructorLink()
                .orderButtonIsDisplayed();

        step("Проверить, что произошел переход");
        assertTrue(isDisplayed);
    }

    @Test
    public void redirectToMainPageByConstructorLinkWorked() {
        step("Перейти в конструктор по ссылке Конструктор");
        boolean isDisplayed = accountPage
                .clickConstructorLink()
                .orderButtonIsDisplayed();

        step("Проверить, что произошел переход");
        assertTrue(isDisplayed);
    }

    @Test
    public void redirectToMainPageByLogoWorked() {
        step("Нажать на логотип");
        boolean isDisplayed = accountPage
                .clickLogo()
                .orderButtonIsDisplayed();

        step("Проверить, что произошел переход в конструктор");
        assertTrue(isDisplayed);
    }

    @Test
    public void exitFromAccountExited() {
        step("Нажать на кнопку выйти");
        boolean isDisplayed = accountPage
                .clickExitButton()
                .titleIsDisplayed();

        step("Проверить, что произошел переход на страницу логина");
        assertTrue(isDisplayed);

        step("Проверить, что пользователь не авторизован");
        assertEquals(0, localStorage.size());
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
