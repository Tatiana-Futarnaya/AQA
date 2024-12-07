package ru.astondevs.lab17;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static java.util.Collections.emptyMap;
import static org.hamcrest.Matchers.*;

/**
 * @author Tatiana Futarnaya
 */
class DeleteTests {
    private static final String REQUEST_BODY = "This is expected to be sent back as part of response body.";
    private static final String URL = "https://postman-echo.com/delete";
    private Response response;

    @BeforeEach
    void setUp() {
        response = sendDeleteRequest(REQUEST_BODY);
    }

    private Response sendDeleteRequest(String requestBody) {
        return given()
                .spec(Specifications.requestSpec())
                .body(requestBody)
                .log().all()
                .when()
                .delete(URL)
                .then()
                .log().ifError()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response();
    }


    @Test
    @DisplayName("Проверка DELETE запроса: статус 200")
    void shouldReturnStatus200() {
        response.then().statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Проверка пустого объекта args в ответе на DELETE запрос")
    void shouldReturnEmptyArgs() {
        response.then().body("args", is(emptyMap()));
    }

    @Test
    @DisplayName("Проверка поля data в ответе на DELETE запрос")
    void shouldReturnCorrectData() {
        response.then().body("data", equalTo(REQUEST_BODY));
    }

    @Test
    @DisplayName("Проверка пустого объекта files в ответе на DELETE запрос")
    void shouldReturnEmptyFiles() {
        response.then().body("files", is(emptyMap()));
    }

    @Test
    @DisplayName("Проверка пустого объекта form в ответе на DELETE запрос")
    void shouldReturnEmptyForm() {
        response.then().body("form", is(emptyMap()));
    }

    @Test
    @DisplayName("Проверка URL в ответе на DELETE запрос")
    void shouldReturnCorrectUrl() {
        response.then().body("url", equalTo(URL));
    }

    @Test
    @DisplayName("Проверка заголовка host в ответе на DELETE запрос")
    void shouldReturnCorrectHostHeader() {
        response.then().body("headers.host", equalTo("postman-echo.com"));
    }

    @Test
    @DisplayName("Проверка заголовка content-type в ответе на DELETE запрос")
    void shouldReturnCorrectContentTypeHeader() {
        response.then().body("headers.content-type", equalTo("text/plain; charset=ISO-8859-1"));
    }

    @Test
    @DisplayName("Проверка заголовка connection в ответе на DELETE запрос")
    void shouldReturnCorrectConnectionHeader() {
        response.then().body("headers.connection", equalTo("close"));
    }

    @Test
    @DisplayName("Проверка заголовка content-length в ответе на DELETE запрос")
    void shouldReturnCorrectContentLengthHeader() {
        response.then().body("headers.content-length", equalTo("58"));
    }

    @Test
    @DisplayName("Проверка заголовка user-agent в ответе на DELETE запрос")
    void shouldReturnCorrectUserAgentHeader() {
        response.then().body("headers.user-agent", equalTo("PostmanRuntime/7.43.0"));
    }

    @Test
    @DisplayName("Проверка поля json в ответе на DELETE запрос")
    void shouldReturnNullJson() {
        response.then().body("json", is(nullValue()));
    }
}
