package ru.astondevs.lab17;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static org.hamcrest.Matchers.*;

/**
 * @author Tatiana Futarnaya
 */
class PatchTests {
    private static final String REQUEST_BODY = "This is expected to be sent back as part of response body.";
    private static final String EXPECTED_HOST = "postman-echo.com";
    private static final String EXPECTED_CONTENT_LENGTH = "58";
    private static final String EXPECTED_USER_AGENT = "PostmanRuntime/7.43.0";
    private static final String EXPECTED_ACCEPT = "*/*";
    private static final String EXPECTED_URL = "https://postman-echo.com/patch";

    private Response response;

    @BeforeEach
    void setUp() {
        response = sendPatchRequest(REQUEST_BODY);
    }

    private Response sendPatchRequest(String requestBody) {
        return Specifications.requestSpec()
                .body(requestBody)
                .when()
                .patch("/patch") // Replace with your API's endpoint
                .then()
                .spec(Specifications.responseSpec(200)) // Check for status 200
                .extract()
                .response();
    }

    @Test
    @DisplayName("Проверка PATCH запроса: статус 200")
    void shouldReturnStatus200() {
        response.then().statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Проверка поля args в ответе на PATCH запрос")
    void shouldReturnEmptyArgs() {
        validateResponseField("args", Collections.emptyMap());
    }

    @Test
    @DisplayName("Проверка поля data в ответе на PATCH запрос")
    void shouldReturnCorrectData() {
        validateResponseField("data", REQUEST_BODY);
    }

    @Test
    @DisplayName("Проверка поля files в ответе на PATCH запрос")
    void shouldReturnEmptyFiles() {
        validateResponseField("files", Collections.emptyMap());
    }

    @Test
    @DisplayName("Проверка поля form в ответе на PATCH запрос")
    void shouldReturnEmptyForm() {
        validateResponseField("form", Collections.emptyMap());
    }

    @Test
    @DisplayName("Проверка заголовка host в ответе на PATCH запрос")
    void shouldReturnCorrectHostHeader() {
        validateResponseField("headers.host", EXPECTED_HOST);
    }

    @Test
    @DisplayName("Проверка заголовка content-length в ответе на PATCH запрос")
    void shouldReturnCorrectContentLengthHeader() {
        validateResponseField("headers.content-length", EXPECTED_CONTENT_LENGTH);
    }

    @Test
    @DisplayName("Проверка заголовка content-type в ответе на PATCH запрос")
    void shouldReturnCorrectContentTypeHeader() {
        response.then()
                .body("headers.content-type", containsString("text/plain"));
    }

    @Test
    @DisplayName("Проверка заголовка user-agent в ответе на PATCH запрос")
    void shouldReturnCorrectUserAgentHeader() {
        validateResponseField("headers.user-agent", EXPECTED_USER_AGENT);
    }

    @Test
    @DisplayName("Проверка заголовка accept в ответе на PATCH запрос")
    void shouldReturnCorrectAcceptHeader() {
        validateResponseField("headers.accept", EXPECTED_ACCEPT);
    }

    @Test
    @DisplayName("Проверка URL в ответе на PATCH запрос")
    void shouldReturnCorrectUrl() {
        validateResponseField("url", EXPECTED_URL);
    }

    private void validateResponseField(String field, Object expectedValue) {
        response.then()
                .body(field, equalTo(expectedValue));
    }
}