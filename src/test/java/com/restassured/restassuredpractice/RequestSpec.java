package com.restassured.restassuredpractice;

import static io.restassured.http.ContentType.JSON;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public final class RequestSpec {
    private static RequestSpecification reqSpecs;

    private static final String BASE_URL = "https://gorest.co.in";
    private static final String TOKEN = "04de44416a0e050dd1634f4e821e4b2ae207b5168bb145840162a9911f771ab8";

    private RequestSpec() {
    }

    public static RequestSpecification getRequestSpecs() {

        if (reqSpecs == null) {

            reqSpecs = new RequestSpecBuilder()
                    .setBaseUri(BASE_URL)
                    .addPathParam("module", "public")
                    .addPathParam("version", "v2")
                    .addHeader("Authorization", "Bearer " + TOKEN)
                    .setContentType(JSON)
                    .build();
        }

        return reqSpecs;
    }

}
