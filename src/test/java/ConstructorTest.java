import model.ui.MainPage;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {

    private static MainPage mainPage;

    @BeforeClass
    public static void setUp() {
        mainPage = new MainPage(driver);

        driver.get(baseUrl);
    }

    @Test
    public void selectingTabsWorks() {
        String expectedClassName = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

        step("Перейти на вкладку Соусы");
        String className = mainPage
                .clickSaucesButton()
                .getSaucesButtonClassName();

        step("Проверить, что вкладка выбрана");
        assertEquals(expectedClassName, className);

        step("Перейти на вкладку Начинки");
        className = mainPage
                .clickFillingsButton()
                .getFillingsButtonClassName();

        step("Проверить, что вкладка выбрана");
        assertEquals(expectedClassName, className);

        step("Перейти на вкладку Булки");
        className = mainPage
                .clickBunsButton()
                .getBunsButtonClassName();

        step("Проверить, что вкладка выбрана");
        assertEquals(expectedClassName, className);
    }
}
