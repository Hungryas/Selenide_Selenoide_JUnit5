package tests.api;

import api.UsersApi;
import api.models.CreateUserRequest;
import api.models.CreateUserResponse;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static java.time.Clock.systemUTC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Проверка создания записей пользователей.")
class CreateUserTest {

    @Test
    @DisplayName("Проверка успешного создания пользователя.")
    void checkCreateUser() {
        String name = "Name";
        String job = "Job";
        LocalDateTime responseTime = LocalDateTime.now(systemUTC());

        CreateUserRequest createUserRequest = new CreateUserRequest(name, job);
        CreateUserResponse createUserResponse = UsersApi.createUserSuccess(createUserRequest);

        assertEquals(name, createUserResponse.getName());
        assertEquals(job, createUserResponse.getJob());

        LocalDateTime actualTime = createUserResponse.getCreatedAt();
        assertTrue(actualTime.isAfter(responseTime));
    }

    @Test
    @DisplayName("Проверка создания пользователя с пустым запросом.")
    void checkCreateUserWithoutBody() {
        UsersApi.createUserWithWrongData();
    }

    @Test
    @DisplayName("Проверка создания пользователя с некорректным запросом.")
    void checkCreateUserWithWrongData() {
        int name = 10;
        int job = 10;
        UsersApi.createUserWithWrongData(name, job);
    }
}
