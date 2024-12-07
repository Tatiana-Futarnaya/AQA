package ru.astondevs.lab17;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * @author Tatiana Futarnaya
 */
class GetTests {
    private static final String FOO1_VALUE = "bar1";
    private static final String FOO2_VALUE = "bar2";
    private static final String EXPECTED_URL = "https://postman-echo.com/get?foo1=bar1&foo2=bar2";
    private static final String HOST_HEADER_VALUE = "postman-echo.com";
    private static final String CONNECTION_HEADER_VALUE = "close";
    private static final String USER_AGENT_HEADER_VALUE = "PostmanRuntime/7.43.0";
    private static final String ACCEPT_HEADER_VALUE = "*/*";

    private Response response;

    @BeforeEach
    void setUp() {
        response = sendGetRequest();
    }

    private Response sendGetRequest() {
        return given()
                .spec(Specifications.requestSpec())
                .queryParam("foo1", FOO1_VALUE)
                .queryParam("foo2", FOO2_VALUE)
                .log().all() // Логирование запроса
                .when()
                .get("/get")
                .then()
                .log().ifError()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response();
    }

    @Test
    @DisplayName("Проверка GET запроса: статус 200")
    void shouldReturn200() {
        response.then().statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Проверка значения args.foo1 в ответе на GET запрос")
    void shouldReturnCorrectFoo1() {
        response.then()
                .body("args.foo1", equalTo(FOO1_VALUE));
    }

    @Test
    @DisplayName("Проверка значения args.foo2 в ответе на GET запрос")
    void shouldReturnCorrectFoo2() {
        response.then()
                .body("args.foo2", equalTo(FOO2_VALUE));
    }

    @Test
    @DisplayName("Проверка URL в ответе на GET запрос")
    void shouldReturnCorrectUrl() {
        response.then()
                .body("url", equalTo(EXPECTED_URL));
    }

    @Test
    @DisplayName("Проверка заголовка host в ответе на GET запрос")
    void shouldReturnCorrectHostHeader() {
        response.then()
                .body("headers.host", equalTo(HOST_HEADER_VALUE));
    }

    @Test
    @DisplayName("Проверка заголовка connection в ответе на GET запрос")
    void shouldReturnCorrectConnectionHeader() {
        response.then()
                .body("headers.connection", equalTo(CONNECTION_HEADER_VALUE));
    }

    @Test
    @DisplayName("Проверка заголовка user-agent в ответе на GET запрос")
    void shouldReturnCorrectUserAgentHeader() {
        response.then()
                .body("headers.user-agent", equalTo(USER_AGENT_HEADER_VALUE));
    }

    @Test
    @DisplayName("Проверка заголовка accept в ответе на GET запрос")
    void shouldReturnCorrectAcceptHeader() {
        response.then()
                .body("headers.accept", equalTo(ACCEPT_HEADER_VALUE));
    }
}