package tests.api;

import api.UsersApi;
import api.models.CreateUserRequest;
import api.models.UpdateUserResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import org.junit.Test;

import java.time.LocalDateTime;

import static java.time.Clock.systemUTC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Epic("Проверка обновления записей пользователей.")
public class UpdateUserTest {
    private final int USER_ID = 2;
    private final String USER_NAME = "Scotty";
    private final String USER_JOB = "Artisan";

    @SneakyThrows
    @Test
    @DisplayName("Проверка успешного обновления пользователя через PUT запрос.")
    public void checkPutUpdateUser() {
        LocalDateTime requestTime = LocalDateTime.now(systemUTC());
        CreateUserRequest createUserRequest = new CreateUserRequest(USER_NAME, USER_JOB);
        UpdateUserResponse updateUserResponse = UsersApi.putUpdateUserResponse(createUserRequest, USER_ID);

        assertEquals(USER_NAME, updateUserResponse.getName());
        assertEquals(USER_JOB, updateUserResponse.getJob());

        LocalDateTime actualTime = updateUserResponse.getUpdatedAt();
        assertTrue(String.format("Update time error! start: %s, finish: %s.", requestTime, actualTime),
                actualTime.isAfter(requestTime));
    }

    @Test
    @DisplayName("Проверка успешного обновления пользователя через PATCH запрос.")
    public void checkPatchUpdateUser() {
        LocalDateTime requestTime = LocalDateTime.now(systemUTC());
        CreateUserRequest createUserRequest = new CreateUserRequest(USER_NAME, USER_JOB);
        UpdateUserResponse updateUserResponse = UsersApi.patchUpdateUserResponse(createUserRequest, USER_ID);

        assertEquals(USER_NAME, updateUserResponse.getName());
        assertEquals(USER_JOB, updateUserResponse.getJob());

        LocalDateTime actualTime = updateUserResponse.getUpdatedAt();
        assertTrue(String.format("Update time error! start: %s, finish: %s.", requestTime, actualTime),
                actualTime.isAfter(requestTime));
    }
}
