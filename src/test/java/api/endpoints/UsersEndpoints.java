package api.endpoints;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UsersEndpoints {
    USER("/users/%s"),
    USERS("/users"),
    REGISTER("/register"),
    LOGIN("/login");

    @Getter
    private final String url;
}
