package api;

import api.models.*;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Map;

import static api.Specification.requestSpec;
import static api.Specification.responseSpec;
import static api.endpoints.UsersEndpoints.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class UsersApi {
    @Step("Отправить корректный запрос на создание пользователя.")
    public static CreateUserResponse createUserSuccess(CreateUserRequest CreateUserRequest) {
        return given()
                .spec(requestSpec())
                .when()
                .body(CreateUserRequest)
                .post(USERS.getUrl())
                .then()
                .spec(responseSpec(SC_CREATED))
                .extract().as(CreateUserResponse.class);
    }

    @Step("Отправить запрос на создание пользователя с числами вместо строк.")
    public static void createUserWithWrongData(int name, int job) {
        given()
                .spec(requestSpec())
                .when()
                .body(String.format("{'name': '%s', 'job': '%s';}", name, job))
                .post(USERS.getUrl())
                .then()
                .spec(responseSpec(SC_BAD_REQUEST));
    }

    @Step("Отправить запрос без тела на создание пользователя.")
    public static void createUserWithWrongData() {
        given()
                .spec(requestSpec())
                .when()
                .post(USERS.getUrl())
                .then()
                .spec(responseSpec(SC_BAD_REQUEST));
    }

    @Step("Отправить запрос без тела на получение данных пользователя.")
    public static GetUserRequest getUserSuccess(int userId) {
        return given()
                .spec(requestSpec())
                .when()
                .get(String.format(USER.getUrl(), userId))
                .then()
                .spec(responseSpec(SC_OK))
                .extract().jsonPath().getObject("data", GetUserRequest.class);
    }

    @Step("Отправить запрос на получение данных пользователя с несуществующим ID.")
    public static void getUserFail(int userId) {
        given()
                .spec(requestSpec())
                .when()
                .get(String.format(USER.getUrl(), userId))
                .then()
                .spec(responseSpec(SC_NOT_FOUND));
    }

    @Step("Отправить запрос без параметров на получение списка всех пользователей.")
    public static List<GetUserRequest> getUsersSuccess() {
        return given()
                .spec(requestSpec())
                .when()
                .get(USERS.getUrl())
                .then()
                .spec(responseSpec(SC_OK))
                .extract().jsonPath().getList("data", GetUserRequest.class);
    }

    @Step("Отправить запрос с параметрами {parametersMap}.")
    public static List<GetUserRequest> getUsersSuccessWithParam(Map parametersMap) {
        return given()
                .spec(requestSpec())
                .when()
                .queryParams(parametersMap)
                .get(USERS.getUrl())
                .then()
                .spec(responseSpec(SC_OK))
                .extract().jsonPath().getList("data", GetUserRequest.class);
    }

    @Step("Отправить корректный запрос на обновление пользователя.")
    public static UpdateUserResponse putUpdateUserResponse(CreateUserRequest CreateUserRequest, int userId) {
        return given()
                .spec(requestSpec())
                .when()
                .body(CreateUserRequest)
                .put(String.format(USER.getUrl(), userId))
                .then()
                .spec(responseSpec(SC_OK))
                .extract().as(UpdateUserResponse.class);
    }

    @Step("Отправить корректный запрос на обновление пользователя.")
    public static UpdateUserResponse patchUpdateUserResponse(CreateUserRequest CreateUserRequest, int userId) {
        return given()
                .spec(requestSpec())
                .when()
                .body(CreateUserRequest)
                .patch(String.format(USER.getUrl(), userId))
                .then()
                .spec(responseSpec(SC_OK))
                .extract().as(UpdateUserResponse.class);
    }

    @Step("Отправить корректный запрос на удаление.")
    public static void deleteUser(int userId) {
        given()
                .spec(requestSpec())
                .when()
                .delete(String.format(USER.getUrl(), userId))
                .then()
                .spec(responseSpec(SC_NO_CONTENT));
    }

    @Step("Отправить запрос на регистрацию с корректными данными.")
    public static RegisterUserResponse registerUser(ServiceUserRequest serviceUserRequest) {
        return given()
                .spec(requestSpec())
                .when()
                .body(serviceUserRequest)
                .post(REGISTER.getUrl())
                .then()
                .spec(responseSpec(SC_OK))
                .extract().as(RegisterUserResponse.class);
    }

    @Step("Отправить запрос на регистрацию неполными данными.")
    public static BadRequestResponse registerUserWithoutData(ServiceUserRequest serviceUserRequest) {
        return given()
                .spec(requestSpec())
                .when()
                .body(serviceUserRequest)
                .post(REGISTER.getUrl())
                .then()
                .spec(responseSpec(SC_BAD_REQUEST))
                .extract().as(BadRequestResponse.class);
    }

    @Step("Отправить запрос на авторизацию с корректными данными.")
    public static LoginUserResponse loginUser(ServiceUserRequest serviceUserRequest) {
        return given()
                .spec(requestSpec())
                .when()
                .body(serviceUserRequest)
                .post(LOGIN.getUrl())
                .then()
                .spec(responseSpec(SC_OK))
                .extract().as(LoginUserResponse.class);
    }

    @Step("Отправить запрос на авторизацию с неполными данными.")
    public static BadRequestResponse loginUserWithoutData(ServiceUserRequest serviceUserRequest) {
        return given()
                .spec(requestSpec())
                .when()
                .body(serviceUserRequest)
                .post(LOGIN.getUrl())
                .then()
                .spec(responseSpec(SC_BAD_REQUEST))
                .extract().as(BadRequestResponse.class);
    }
}
