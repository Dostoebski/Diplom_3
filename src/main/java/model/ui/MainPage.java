package model.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private final WebDriver driver;

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By accountLink = By.xpath("//a[@href='/account']");
    private final By bunsButton = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesButton = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsButton = By.xpath("//span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    public LoginPage clickAccountLink() {
        driver.findElement(accountLink).click();
        return new LoginPage(driver);
    }

    public MainPage clickBunsButton() {
        driver.findElement(bunsButton).click();
        return this;
    }

    public MainPage clickSaucesButton() {
        driver.findElement(saucesButton).click();
        return this;
    }

    public MainPage clickFillingsButton() {
        driver.findElement(fillingsButton).click();
        return this;
    }

    public void waitUntilOrderButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton));
    }

    public boolean orderButtonIsDisplayed() {
        waitUntilOrderButtonDisplayed();
        return driver.findElement(orderButton).isDisplayed();
    }

    public String getSaucesButtonClassName() {
        return driver.findElement(saucesButton).getAttribute("class");
    }

    public String getBunsButtonClassName() {
        return driver.findElement(bunsButton).getAttribute("class");
    }

    public String getFillingsButtonClassName() {
        return driver.findElement(fillingsButton).getAttribute("class");
    }
}
