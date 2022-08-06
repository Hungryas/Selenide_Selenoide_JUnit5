package tests.api;

import api.UsersApi;
import api.models.BadRequestResponse;
import api.models.LoginUserResponse;
import api.models.ServiceUserRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

@Epic("Проверка авторизации пользователя.")
public class LoginUserTest {
    private final String USER_EMAIL = "eve.holt@reqres.in";

    @Test
    @DisplayName("Проверка успешной авторизации пользователя.")
    public void checkLoginUserSuccess() {
        String USER_PASS = "i*gjsH26$b";
        String USER_TOKEN = "QpwL5tke4Pnpja7X4";


        ServiceUserRequest serviceUserRequest = ServiceUserRequest.builder()
                .email(USER_EMAIL)
                .password(USER_PASS).build();
        LoginUserResponse loginUserResponse = UsersApi.loginUser(serviceUserRequest);

        Assert.assertEquals(USER_TOKEN, loginUserResponse.getToken());
    }

    @Test
    @DisplayName("Проверка авторизации пользователя без указания пароля.")
    public void checkLoginUserWithoutPass() {
        String RESPONSE_ERROR = "Missing password";

        ServiceUserRequest serviceUserRequest = ServiceUserRequest.builder().email(USER_EMAIL).build();
        BadRequestResponse badRequestResponse = UsersApi.loginUserWithoutData(serviceUserRequest);
        Assert.assertEquals(RESPONSE_ERROR, badRequestResponse.getError());
    }

    @Test
    @DisplayName("Проверка авторизации пользователя без данных.")
    public void checkRegisterUserWithoutData() {
        String RESPONSE_ERROR = "Missing email or username";

        ServiceUserRequest serviceUserRequest = ServiceUserRequest.builder().build();
        BadRequestResponse badRequestResponse = UsersApi.loginUserWithoutData(serviceUserRequest);
        Assert.assertEquals(RESPONSE_ERROR, badRequestResponse.getError());
    }
}
