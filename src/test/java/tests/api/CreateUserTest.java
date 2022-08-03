package tests.api;

import api.UsersApi;
import api.models.CreateUserRequest;
import api.models.CreateUserResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Epic("Проверка создания записей пользователей.")
public class CreateUserTest {

    @Test
    @DisplayName("Проверка успешного создания пользователя.")
    public void checkCreateUser() {
        String name = "Name";
        String job = "Job";
        LocalDateTime responseTime = LocalDateTime.now();

        CreateUserRequest createUserRequest = new CreateUserRequest(name, job);
        CreateUserResponse createUserResponse = UsersApi.createUserSuccess(createUserRequest);

        assertEquals(name, createUserResponse.getName());
        assertEquals(job, createUserResponse.getJob());

        LocalDateTime actualTime = createUserResponse.getCreatedAt();
        assertTrue(actualTime.isBefore(responseTime));
    }

    @Test
    @DisplayName("Проверка создания пользователя с пустым запросом.")
    public void checkCreateUserWithoutBody() {
        UsersApi.createUserWithWrongData();
    }

    @Test
    @DisplayName("Проверка создания пользователя с некорректным запросом.")
    // TODO Add parametrized data
    public void checkCreateUserWithWrongData() {
        int name = 10;
        int job = 10;
        UsersApi.createUserWithWrongData(name, job);
    }
}
