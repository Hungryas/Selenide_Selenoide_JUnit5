package tests.api;

import api.UsersApi;
import api.models.BadRequestResponse;
import api.models.RegisterUserResponse;
import api.models.ServiceUserRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

@Epic("Проверка регистрации пользователя.")
public class RegisterUserTest {
    private final String USER_EMAIL = "eve.holt@reqres.in";

    @Test
    @DisplayName("Проверка успешной регистрации пользователя.")
    public void checkRegisterUserSuccess() {
        int USER_ID = 4;
        String USER_PASS = "i*gjsH26$b";
        String USER_TOKEN = "QpwL5tke4Pnpja7X4";

        ServiceUserRequest serviceUserRequest = ServiceUserRequest.builder()
                .email(USER_EMAIL)
                .password(USER_PASS).build();
        RegisterUserResponse registerUserResponse = UsersApi.registerUser(serviceUserRequest);

        Assert.assertEquals(USER_ID, registerUserResponse.getId());
        Assert.assertEquals(USER_TOKEN, registerUserResponse.getToken());
    }

    @Test
    @DisplayName("Проверка регистрации пользователя без указания пароля.")
    public void checkRegisterUserWithoutPass() {
        String RESPONSE_ERROR = "Missing password";

        ServiceUserRequest serviceUserRequest = ServiceUserRequest.builder().email(USER_EMAIL).build();
        BadRequestResponse badRequestResponse = UsersApi.registerUserWithoutData(serviceUserRequest);
        Assert.assertEquals(RESPONSE_ERROR, badRequestResponse.getError());
    }

    @Test
    @DisplayName("Проверка регистрации пользователя без данных.")
    public void checkRegisterUserWithoutData() {
        String RESPONSE_ERROR = "Missing email or username";

        ServiceUserRequest serviceUserRequest = ServiceUserRequest.builder().build();
        BadRequestResponse badRequestResponse = UsersApi.registerUserWithoutData(serviceUserRequest);
        Assert.assertEquals(RESPONSE_ERROR, badRequestResponse.getError());
    }
}
