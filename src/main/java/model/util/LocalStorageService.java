package model.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;

/*
 * Методы для работы с local storage
 */

public class LocalStorageService {

    private final LocalStorage localStorage;

    public LocalStorageService(WebDriver driver) {
        this.localStorage = ((WebStorage) driver).getLocalStorage();
    }

    public LocalStorageService setAccessToken(String accessToken) {
        localStorage.setItem("accessToken", accessToken);
        return this;
    }

    public LocalStorageService setRefreshToken(String refreshToken) {
        localStorage.setItem("refreshToken", refreshToken);
        return this;
    }

    public void clear() {
        localStorage.clear();
    }

    public int size() {
        return localStorage.size();
    }

}
