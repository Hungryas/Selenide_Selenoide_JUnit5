package tests.api;

import api.UsersApi;
import api.models.CreateUserRequest;
import api.models.UpdateUserResponse;
import io.qameta.allure.Epic;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static java.time.Clock.systemUTC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Проверка обновления записей пользователей.")
class UpdateUserTest {
    private final static int USER_ID = 2;
    private final static String USER_NAME = "Scotty";
    private final static String USER_JOB = "Artisan";

    @SneakyThrows
    @Test
    @DisplayName("Проверка успешного обновления пользователя через PUT запрос.")
    void checkPutUpdateUser() {
        LocalDateTime requestTime = LocalDateTime.now(systemUTC());
        CreateUserRequest createUserRequest = new CreateUserRequest(USER_NAME, USER_JOB);
        UpdateUserResponse updateUserResponse = UsersApi.putUpdateUserResponse(createUserRequest, USER_ID);

        assertEquals(USER_NAME, updateUserResponse.getName());
        assertEquals(USER_JOB, updateUserResponse.getJob());

        LocalDateTime actualTime = updateUserResponse.getUpdatedAt();
        assertTrue(actualTime.isAfter(requestTime),
                String.format("Update time error! start: %s, finish: %s.", requestTime, actualTime));
    }

    @Test
    @DisplayName("Проверка успешного обновления пользователя через PATCH запрос.")
    void checkPatchUpdateUser() {
        LocalDateTime requestTime = LocalDateTime.now(systemUTC());
        CreateUserRequest createUserRequest = new CreateUserRequest(USER_NAME, USER_JOB);
        UpdateUserResponse updateUserResponse = UsersApi.patchUpdateUserResponse(createUserRequest, USER_ID);

        assertEquals(USER_NAME, updateUserResponse.getName());
        assertEquals(USER_JOB, updateUserResponse.getJob());

        LocalDateTime actualTime = updateUserResponse.getUpdatedAt();
        assertTrue(actualTime.isAfter(requestTime),
                String.format("Update time error! start: %s, finish: %s.", requestTime, actualTime));
    }
}
