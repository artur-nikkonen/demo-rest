package com.flamexander.demo.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MathControllerTests extends BaseTests {

    private static Stream<Arguments> sumOkParams() {
        return createStreamWithConvertedAB(Stream.of(
                Arguments.of("sum", -1, 1, 0),
                Arguments.of("sum", 1, 1, 2),
                Arguments.of("sum", -1, -1, -2),
                Arguments.of("sum", 0, 0, 0),
                Arguments.of("sum", 100, 50, 150),

                Arguments.of("multiply", 0, 1, 0),
                Arguments.of("multiply", 0, 0, 0),
                Arguments.of("multiply", 1, 1, 1),
                Arguments.of("multiply", -1, -1, 1),
                Arguments.of("multiply", 10, 1000, 10000),
                Arguments.of("multiply", -10, -100, 1000)
        ));
    }

    private static Stream<Arguments> sumOverflowParams() {
        return createStreamWithConvertedAB(Stream.of(
                Arguments.of("sum", Integer.MAX_VALUE, 1),
                Arguments.of("sum", Integer.MIN_VALUE, -1),
                Arguments.of("multiply", Integer.MAX_VALUE / 2 + 1, 2),
                Arguments.of("multiply", Integer.MIN_VALUE / 2 - 1, 2)
        ));
    }

    @ParameterizedTest
    @MethodSource("sumOkParams")
    public void mathCorrectTest(String operation, int a, int b, int result) {
        Integer answer = given()
                .spec(specification)
                .param("a", a)
                .param("b", b)
                .when()
                .get("/math/" + operation)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response().body().as(Integer.class);

        Assertions.assertEquals(result, answer);
    }

    @ParameterizedTest
    @MethodSource("sumOverflowParams")
    public void mathOverflowTest(String operation, int a, int b) {
        given()
                .spec(specification)
                .param("a", a)
                .param("b", b)
                .when()
                .get("/math/" + operation)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("integer overflow"));
    }
}