package com.restassured.specifications;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecs {

    private static final String BASE_URL = "http://127.0.0.1:8000";

    private RequestSpecs() {
    }

    public static RequestSpecification defaultSpec() {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }

    public static RequestSpecification authorized(String token) {
        return defaultSpec()
                .header("Authorization", "Bearer " + token);
    }

    public static RequestSpecification formUrlEncoded() {
        return defaultSpec()
                .contentType(ContentType.URLENC);
    }
}