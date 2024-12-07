package ru.astondevs.lab17;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Collections;

/**
 * @author Tatiana Futarnaya
 */
 class POSTFormDataTest {
    private static final String URL = "https://postman-echo.com/post";
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
    private static final String DEFAULT_REQUEST_BODY = "foo1=bar1&foo2=bar2";

    private Response response;

    @BeforeEach
    void setUp() {
        response = sendPostRequest(DEFAULT_REQUEST_BODY);
    }

    private Response sendPostRequest(String requestBody) {
        return given()
                .spec(Specifications.requestSpec())
                .contentType(CONTENT_TYPE)
                .body(requestBody)
                .log().all()
                .when()
                .post(URL)
                .then()
                .log().ifError()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response();
    }

    private void assertResponse(String jsonPath, Object expectedValue) {
        response.then().body(jsonPath, equalTo(expectedValue));
    }

    @Test
    @DisplayName("Проверка POST запроса: статус 200")
     void shouldReturnStatus200() {
        response.then().statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Проверка пустого объекта args в ответе на POST запрос")
     void shouldReturnEmptyArgs() {
        assertResponse("args", Collections.emptyMap());
    }

    @Test
    @DisplayName("Проверка пустого поля data в ответе на POST запрос")
     void shouldReturnEmptyData() {
        assertResponse("data", "");
    }

    @Test
    @DisplayName("Проверка пустого объекта files в ответе на POST запрос")
     void shouldReturnEmptyFiles() {
        assertResponse("files", Collections.emptyMap());
    }

    @Test
    @DisplayName("Проверка значения form.foo1 в ответе на POST запрос")
     void shouldReturnCorrectFormFoo1() {
        assertResponse("form.foo1", "bar1");
    }

    @Test
    @DisplayName("Проверка значения form.foo2 в ответе на POST запрос")
     void shouldReturnCorrectFormFoo2() {
        assertResponse("form.foo2", "bar2");
    }

    @Test
    @DisplayName("Проверка заголовка host в ответе на POST запрос")
     void shouldReturnCorrectHostHeader() {
        assertResponse("headers.host", "postman-echo.com");
    }

    @Test
    @DisplayName("Проверка заголовка connection в ответе на POST запрос")
     void shouldReturnCorrectConnectionHeader() {
        assertResponse("headers.connection", "close");
    }

    @Test
    @DisplayName("Проверка заголовка content-length в ответе на POST запрос")
     void shouldReturnCorrectContentLengthHeader() {
        assertResponse("headers.content-length", "19");
    }

    @Test
    @DisplayName("Проверка заголовка user-agent в ответе на POST запрос")
     void shouldReturnCorrectUserAgentHeader() {
        assertResponse("headers.user-agent", "PostmanRuntime/7.43.0");
    }

    @Test
    @DisplayName("Проверка заголовка accept в ответе на POST запрос")
     void shouldReturnCorrectAcceptHeader() {
        assertResponse("headers.accept", "*/*");
    }

    @Test
    @DisplayName("Проверка заголовка content-type в ответе на POST запрос")
     void shouldReturnCorrectContentTypeHeader() {
        response.then().body("headers.content-type", containsString("application/x-www-form-urlencoded"));
    }

    @Test
    @DisplayName("Проверка значения json.foo1 в ответе на POST запрос")
     void shouldReturnCorrectJsonFoo1() {
        assertResponse("json.foo1", "bar1");
    }

    @Test
    @DisplayName("Проверка значения json.foo2 в ответе на POST запрос")
     void shouldReturnCorrectJsonFoo2() {
        assertResponse("json.foo2", "bar2");
    }

    @Test
    @DisplayName("Проверка URL в ответе на POST запрос")
     void shouldReturnCorrectUrl() {
        assertResponse("url", URL);
    }
}