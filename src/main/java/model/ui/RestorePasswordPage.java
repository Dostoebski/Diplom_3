package model.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePasswordPage {

    private final WebDriver driver;

    private final By loginLink = By.linkText("Войти");

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage clickLoginLink() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }
}
