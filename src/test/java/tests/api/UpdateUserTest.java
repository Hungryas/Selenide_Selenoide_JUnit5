package tests.api;

import api.UsersApi;
import api.models.CreateUserRequest;
import api.models.UpdateUserResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Epic("Проверка обновления записей пользователей.")
public class UpdateUserTest {
    private final int USER_ID = 2;
    private final String USER_NAME = "Scotty";
    private final String USER_JOB = "Artisan";

    @Test
    @DisplayName("Проверка успешного обновления пользователя через PUT запрос.")
    public void checkPutUpdateUser() {
        LocalDateTime responseTime = LocalDateTime.now();
        CreateUserRequest createUserRequest = new CreateUserRequest(USER_NAME, USER_JOB);
        UpdateUserResponse updateUserResponse = UsersApi.putUpdateUserResponse(createUserRequest, USER_ID);

        assertEquals(USER_NAME, updateUserResponse.getName());
        assertEquals(USER_JOB, updateUserResponse.getJob());

        LocalDateTime actualTime = updateUserResponse.getUpdatedAt();
        assertTrue(actualTime.isBefore(responseTime));
    }

    @Test
    @DisplayName("Проверка успешного обновления пользователя через PATCH запрос.")
    public void checkPatchUpdateUser() {
        LocalDateTime responseTime = LocalDateTime.now();
        CreateUserRequest createUserRequest = new CreateUserRequest(USER_NAME, USER_JOB);
        UpdateUserResponse updateUserResponse = UsersApi.putUpdateUserResponse(createUserRequest, USER_ID);

        assertEquals(USER_NAME, updateUserResponse.getName());
        assertEquals(USER_JOB, updateUserResponse.getJob());

        LocalDateTime actualTime = updateUserResponse.getUpdatedAt();
        assertTrue(actualTime.isBefore(responseTime));
    }
}
