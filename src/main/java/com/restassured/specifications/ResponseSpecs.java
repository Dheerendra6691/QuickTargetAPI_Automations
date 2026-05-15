package com.restassured.specifications;

import static org.hamcrest.Matchers.lessThan;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public final class ResponseSpecs {

    private ResponseSpecs() {
    }

    public static ResponseSpecification success() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(5000L)) // Rest Assured’s responseTime() returns a long, not an int.
                .build();
    }

    public static ResponseSpecification status(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }
}