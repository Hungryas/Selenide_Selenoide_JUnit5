package tests.api;

import api.UsersApi;
import api.models.BadRequestResponse;
import api.models.RegisterUserResponse;
import api.models.ServiceUserRequest;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Проверка регистрации пользователя.")
class RegisterUserTest {
    private final static String USER_EMAIL = "eve.holt@reqres.in";

    @Test
    @DisplayName("Проверка успешной регистрации пользователя.")
    void checkRegisterUserSuccess() {
        int USER_ID = 4;
        String USER_PASS = "i*gjsH26$b";
        String USER_TOKEN = "QpwL5tke4Pnpja7X4";

        ServiceUserRequest serviceUserRequest = ServiceUserRequest.builder()
                .email(USER_EMAIL)
                .password(USER_PASS).build();
        RegisterUserResponse registerUserResponse = UsersApi.registerUser(serviceUserRequest);

        assertEquals(USER_ID, registerUserResponse.getId());
        assertEquals(USER_TOKEN, registerUserResponse.getToken());
    }

    @Test
    @DisplayName("Проверка регистрации пользователя без указания пароля.")
    void checkRegisterUserWithoutPass() {
        String RESPONSE_ERROR = "Missing password";

        ServiceUserRequest serviceUserRequest = ServiceUserRequest.builder().email(USER_EMAIL).build();
        BadRequestResponse badRequestResponse = UsersApi.registerUserWithoutData(serviceUserRequest);
        assertEquals(RESPONSE_ERROR, badRequestResponse.getError());
    }

    @Test
    @DisplayName("Проверка регистрации пользователя без данных.")
    void checkRegisterUserWithoutData() {
        String RESPONSE_ERROR = "Missing email or username";

        ServiceUserRequest serviceUserRequest = ServiceUserRequest.builder().build();
        BadRequestResponse badRequestResponse = UsersApi.registerUserWithoutData(serviceUserRequest);
        assertEquals(RESPONSE_ERROR, badRequestResponse.getError());
    }
}
