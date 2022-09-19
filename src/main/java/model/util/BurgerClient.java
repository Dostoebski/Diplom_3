package model.util;

import io.restassured.response.Response;
import model.dto.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BurgerClient extends RestClient {

        private final String REGISTER_PATH = "/api/auth/register";
        private final String USER_PATH = "/api/auth/user";
        private final String LOGIN_PATH = "/api/auth/login";

        public Response createUser(User user) {
            return given()
                    .spec(getBaseSpec())
                    .body(user)
                    .post(REGISTER_PATH);
        }

        public void deleteUser(String accessToken) {
            given()
                    .spec(getBaseSpec(accessToken))
                    .delete(USER_PATH);
        }

        public Response login(User user) {
            Map<String, String> credentials = new HashMap<>();
            credentials.put("email", user.getEmail());
            credentials.put("password", user.getPassword());

            return given()
                    .spec(getBaseSpec())
                    .body(credentials)
                    .post(LOGIN_PATH);
        }
}
