package tests.api;

import api.UsersApi;
import api.models.GetUserRequest;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Проверка получения записей пользователей.")
class GetUserRequestTest {
    private final static int USER_ID = 2;
    private final static String EMAIL = "janet.weaver@reqres.in";
    private final static String FIRST_NAME = "Janet";
    private final static String LAST_NAME = "Weaver";
    private final static GetUserRequest TEST_USER = new GetUserRequest(USER_ID, EMAIL, FIRST_NAME, LAST_NAME);

    @Test
    @DisplayName("Успешное получение данных пользователя.")
    void checkGetUser() {
        GetUserRequest getUserRequest = UsersApi.getUserSuccess(TEST_USER.getId());
        assertEquals(getUserRequest.getId(), TEST_USER.getId());
        assertEquals(getUserRequest.getEmail(), TEST_USER.getEmail());
        assertEquals(getUserRequest.getFirstName(), TEST_USER.getFirstName());
        assertEquals(getUserRequest.getLastName(), TEST_USER.getLastName());
    }

    @Test
    @DisplayName("Получение данных пользователя с несуществующим ID.")
    void checkGetUserWithWrongId() {
        int userId = 23;
        UsersApi.getUserFail(userId);
    }

    @Test
    @DisplayName("Успешное получение данных всех пользователей.")
    void checkGetUsers() {
        List<GetUserRequest> userList = UsersApi.getUsersSuccess();
        assertTrue(userList.contains(TEST_USER));
    }

    @Test
    @DisplayName("Успешное получение данных всех пользователей с параметрами page и per_page.")
    void checkGetUsersWithPerPage() {
        int page = 1;
        int per_page = 12;
        Map<String, Integer> paramsMap = Map.of("page", page, "per_page", per_page);
        List<GetUserRequest> userList = UsersApi.getUsersSuccessWithParam(paramsMap);

        assertTrue(userList.contains(TEST_USER));
        assertEquals(userList.size(), per_page);
    }

    @Test
    @DisplayName("Успешное получение данных всех пользователей с параметром delay.")
    void checkGetUsersWithDelay() {
        int delay = 4;
        LocalDateTime startRequest = LocalDateTime.now();
        Map<String, Integer> paramsMap = Map.of("delay", delay);
        List<GetUserRequest> userList = UsersApi.getUsersSuccessWithParam(paramsMap);

        LocalDateTime finishRequest = LocalDateTime.now();
        long actualDelay = SECONDS.between(startRequest, finishRequest);

        assertTrue(userList.contains(TEST_USER), "User not found!");
        assertTrue(actualDelay >= delay, String.format("Delay error! start: %s, finish: %s, delay: %s.", startRequest, finishRequest, actualDelay));
    }
}
