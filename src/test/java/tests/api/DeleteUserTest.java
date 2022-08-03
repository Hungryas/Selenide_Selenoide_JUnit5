package tests.api;

import api.UsersApi;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

@Epic("Проверка удаления записей пользователей.")
public class DeleteUserTest {
    private final int USER_ID = 2;

    @Test
    @DisplayName("Проверка успешного удаления данных пользователя.")
    public void checkDeleteUser() {
        UsersApi.deleteUser(USER_ID);
    }
}
