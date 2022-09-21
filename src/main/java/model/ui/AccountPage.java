package model.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {

    private final WebDriver driver;

    private final By constructorLink = By.xpath("//p[text()='Конструктор']/parent::a");
    private final By logo = By.cssSelector(".AppHeader_header__logo__2D0X2");
    private final By exitButton = By.xpath("//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage clickConstructorLink() {
        driver.findElement(constructorLink).click();
        return new MainPage(driver);
    }

    public MainPage clickLogo() {
        driver.findElement(logo).click();
        return new MainPage(driver);
    }

    public LoginPage clickExitButton() {
        driver.findElement(exitButton).click();
        return new LoginPage(driver);
    }
}
