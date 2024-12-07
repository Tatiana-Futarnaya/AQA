package ru.astondevs.lab17;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * @author Tatiana Futarnaya
 */
class PutTests {
    private static final String REQUEST_BODY = "This is expected to be sent back as part of response body.";
    private static final String URL = "https://postman-echo.com/put";
    private Response response;

    @BeforeEach
    void setUp() {
        response = sendPutRequest();
    }

    private Response sendPutRequest() {
        return given()
                .spec(Specifications.requestSpec())
                .body(REQUEST_BODY)
                .log().all()
                .when()
                .put(URL)
                .then()
                .log().ifError()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response();
    }

    @Test
    @DisplayName("Проверка PUT запроса: статус 200")
    void shouldReturnStatus200() {
        response.then().statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Проверка поля data в ответе на PUT запрос")
    void shouldReturnCorrectResponseBodyData() {
        response.then().body("data", equalTo(REQUEST_BODY));
    }

    @Test
    @DisplayName("Проверка пустого объекта args в ответе на PUT запрос")
    void shouldReturnEmptyArgs() {
        response.then().body("args", equalTo(Collections.emptyMap()));
    }

    @Test
    @DisplayName("Проверка пустого объекта files в ответе на PUT запрос")
    void shouldReturnEmptyFiles() {
        response.then().body("files", equalTo(Collections.emptyMap()));
    }

    @Test
    @DisplayName("Проверка пустого объекта form в ответе на PUT запрос")
    void shouldReturnEmptyForm() {
        response.then().body("form", equalTo(Collections.emptyMap()));
    }

    @Test
    @DisplayName("Проверка значения json в ответе на PUT запрос")
    void shouldReturnNullJson() {
        response.then().body("json", is(emptyOrNullString()));
    }

    @Test
    @DisplayName("Проверка URL в ответе на PUT запрос")
    void shouldReturnCorrectUrl() {
        response.then().body("url", equalTo(URL));
    }

    @Test
    @DisplayName("Проверка заголовка content-type в ответе на PUT запрос")
    void shouldReturnCorrectContentTypeHeader() {
        response.then().body("headers.content-type", containsString("text/plain"));
    }

    @Test
    @DisplayName("Проверка заголовка connection в ответе на PUT запрос")
    void shouldReturnCorrectConnectionHeader() {
        response.then().body("headers.connection", equalTo("close"));
    }

    @Test
    @DisplayName("Проверка заголовка host в ответе на PUT запрос")
    void shouldReturnCorrectHostHeader() {
        response.then().body("headers.host", equalTo("postman-echo.com"));
    }

    @Test
    @DisplayName("Проверка заголовка accept в ответе на PUT запрос")
    void shouldReturnCorrectAcceptHeader() {
        response.then().body("headers.accept", equalTo("*/*"));
    }

    @Test
    @DisplayName("Проверка заголовка user-agent в ответе на PUT запрос")
    void shouldReturnCorrectUserAgentHeader() {
        response.then().body("headers.user-agent", equalTo("PostmanRuntime/7.43.0"));
    }
}