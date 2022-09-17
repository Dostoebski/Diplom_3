package model.ui;

import model.dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;

    private final By title = By.xpath("//h2");
    private final By emailInput = By.xpath("//fieldset[1]//input");
    private final By passwordInput = By.xpath("//fieldset[2]//input");
    private final By submitButton = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilTitleDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(title));
    }

    public boolean titleIsDisplayed() {
        waitUntilTitleDisplayed();
        return driver.findElement(title).isDisplayed();
    }

    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setPassword(String name) {
        driver.findElement(passwordInput).sendKeys(name);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    public MainPage login(User user) {
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickSubmitButton();
        return new MainPage(driver);
    }
}
