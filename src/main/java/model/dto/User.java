package model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@AllArgsConstructor
@Getter
@Setter
public class User {

    private String email;
    private String password;
    private String name;

    public static User getDefaultUser() {
        Random random = new Random();
        String randomEmail = random.nextInt(10000) + "data-test@example.com";
        return new User(
                randomEmail,
                "praktikum",
                "Josh"
        );
    }
}
