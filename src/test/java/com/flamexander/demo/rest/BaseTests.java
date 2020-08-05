package com.flamexander.demo.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class BaseTests {
    RequestSpecification specification = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8189)
            .setBasePath("/rest_service/api/v1")
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .build();

    //Добавляем в стрим примеры с переставленными "a" и "b", если они разные
    public static Stream<Arguments> createStreamWithConvertedAB(Stream<Arguments> st) {
        return st.map(a -> {
            Object[] values = a.get();

            if (values.length < 3) return Stream.of(a);

            if (values[1] == values[2]) {
                return Stream.of(a);
            } else {
                Object[] convertedValues = values.clone();
                Object tmp = convertedValues[1];
                convertedValues[1] = convertedValues[2];
                convertedValues[2] = tmp;
                return Stream.of(a, Arguments.of(convertedValues));
            }
        }).flatMap(x -> x);
    }
}