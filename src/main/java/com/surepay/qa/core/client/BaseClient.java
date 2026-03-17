package com.surepay.qa.core.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class BaseClient {

    protected Response get(String path, Map<String, ?> queryParams) {
        Map<String, ?> filteredQueryParams = queryParams.entrySet()
                .stream()
                .filter(entry -> Objects.nonNull(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return RestAssured
                .given()
                .spec(RequestSpecs.defaultSpec())
                .queryParams(filteredQueryParams)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    protected Response get(String path) {
        return RestAssured
                .given()
                .spec(RequestSpecs.defaultSpec())
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }
}