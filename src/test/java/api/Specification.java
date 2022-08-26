package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.http.ContentType.JSON;

public class Specification {

    private static final String BASE_URI = "https://reqres.in/api/";

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setBaseUri(BASE_URI)
                .setContentType(JSON)
                .log(BODY)
                .build();
    }

    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(BODY)
                .build();
    }
}
