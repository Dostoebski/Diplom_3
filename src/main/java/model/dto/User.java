package model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {

    private String email;
    private String password;
    private String name;

    public static User getDefaultUser() {
        return new User(
                "data-test-ui@example.com",
                "praktikum",
                "Josh"
        );
    }
}
