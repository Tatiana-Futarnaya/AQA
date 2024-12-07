package ru.astondevs.lab17;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

/**
 * @author Tatiana Futarnaya
 */public class Specifications {
    private static final String BASE_URI = "https://postman-echo.com";
    private static final String CONTENT_TYPE_HEADER = "text/plain; charset=ISO-8859-1";
    private static final String USER_AGENT_HEADER = "PostmanRuntime/7.43.0";
    public static RequestSpecification requestSpec() {
        return given()
                .baseUri(BASE_URI)
                .header("Content-Type", CONTENT_TYPE_HEADER)
                .header("User-Agent", USER_AGENT_HEADER)
                .log().all(); // Логирование запроса
    }

    public static ResponseSpecification responseSpec(int expectedStatusCode) {
        return expect()
                .statusCode(expectedStatusCode)
                .log().all(); // Логирование ответа
    }
}
