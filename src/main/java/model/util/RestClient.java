package model.util;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class RestClient {

    private final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    public RequestSpecification getBaseSpec() {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json");
    }

    public RequestSpecification getBaseSpec(String accessToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .header("Content-type", "application/json");
    }
}
