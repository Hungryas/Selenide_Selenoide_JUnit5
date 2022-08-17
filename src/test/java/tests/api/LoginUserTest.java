package tests.api;

import api.UsersApi;
import api.models.BadRequestResponse;
import api.models.LoginUserResponse;
import api.models.ServiceUserRequest;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Проверка авторизации пользователя.")
class LoginUserTest {
    private final static String USER_EMAIL = "eve.holt@reqres.in";

    @Test
    @DisplayName("Проверка успешной авторизации пользователя.")
    void checkLoginUserSuccess() {
        String USER_PASS = "i*gjsH26$b";
        String USER_TOKEN = "QpwL5tke4Pnpja7X4";


        ServiceUserRequest serviceUserRequest = ServiceUserRequest.builder()
                .email(USER_EMAIL)
                .password(USER_PASS).build();
        LoginUserResponse loginUserResponse = UsersApi.loginUser(serviceUserRequest);

        assertEquals(USER_TOKEN, loginUserResponse.getToken());
    }

    @Test
    @DisplayName("Проверка авторизации пользователя без указания пароля.")
    void checkLoginUserWithoutPass() {
        String RESPONSE_ERROR = "Missing password";

        ServiceUserRequest serviceUserRequest = ServiceUserRequest.builder().email(USER_EMAIL).build();
        BadRequestResponse badRequestResponse = UsersApi.loginUserWithoutData(serviceUserRequest);
        assertEquals(RESPONSE_ERROR, badRequestResponse.getError());
    }

    @Test
    @DisplayName("Проверка авторизации пользователя без данных.")
    void checkRegisterUserWithoutData() {
        String RESPONSE_ERROR = "Missing email or username";

        ServiceUserRequest serviceUserRequest = ServiceUserRequest.builder().build();
        BadRequestResponse badRequestResponse = UsersApi.loginUserWithoutData(serviceUserRequest);
        assertEquals(RESPONSE_ERROR, badRequestResponse.getError());
    }
}
