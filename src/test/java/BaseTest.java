import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import model.util.BurgerClient;
import model.util.LocalStorageService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    static WebDriver driver;
    public static String baseUrl = "https://stellarburgers.nomoreparties.site/";
    public static BurgerClient apiClient;
    public static LocalStorageService localStorage;

    @BeforeClass
    public static void init() {
        apiClient = new BurgerClient();

        // driver setup for chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        /*
        // driver setup for yandex browser
        System.setProperty("webdriver.chrome.driver","src/main/resources/yandexdriver.exe");
        driver = new ChromeDriver();
         */
        localStorage = new LocalStorageService(driver);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Step("{0}")
    public void step(String message){
    }
}
