package model.dto;

public class UserFactory {
    public static User getDefaultUser() {
        return new User(
                "data-test-ui@example.com",
                "praktikum",
                "Josh"
        );
    }
}
