package tests.api;

import api.UsersApi;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Проверка удаления записей пользователей.")
class DeleteUserTest {
    private final static int USER_ID = 2;

    @Test
    @DisplayName("Проверка успешного удаления данных пользователя.")
    void checkDeleteUser() {
        UsersApi.deleteUser(USER_ID);
    }
}
