package model.ui;

import model.dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private final WebDriver driver;
    private final By nameInput = By.xpath("//fieldset[1]//input");
    private final By emailInput = By.xpath("//fieldset[2]//input");
    private final By passwordInput = By.xpath("//fieldset[3]//input");
    private final By submitButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By inputError = By.cssSelector(".input__error");
    private final By loginLink = By.linkText("Войти");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    public LoginPage registerUser(User user) {
        setName(user.getName());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickSubmitButton();
        return new LoginPage(driver);
    }

    public void waitUntilErrorDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputError));
    }

    public String inputErrorText() {
        waitUntilErrorDisplayed();
        return driver.findElement(inputError).getText();
    }

    public LoginPage clickLoginLink() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }
}
