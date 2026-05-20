package com.restassured.restassuredpractice;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;

public final class BaseClient {
    private BaseClient() {
    }
    // https://gorest.co.in/public/v2/users

    private static final String BASE_URL = "https://gorest.co.in";
    private static final String TOKEN = "04de44416a0e050dd1634f4e821e4b2ae207b5168bb145840162a9911f771ab8";

    public static Response getRequest(String endpoint) {
        return given().baseUri(BASE_URL)
                .pathParam("module", "public")
                .pathParam("version", "v2")
                .pathParam("resource", endpoint)
                .headers("content-type", "application/json")

                .when().get("/{module}/{version}/{resource}");

    }

    public static Response postRequestWithMap(String endpoint) {
        String email = "piyush" + System.currentTimeMillis() + "@gmail.com";
        String name = "piyush" + System.currentTimeMillis();

        Map<String, Object> paylaod = new HashMap<>();
        paylaod.put("name", name);
        paylaod.put("email", email);
        paylaod.put("gender", "male");
        paylaod.put("status", "active");

        return given().log().all()
                .baseUri(BASE_URL)
                .pathParams(
                        "module", "public",
                        "version", "v2",
                        "resource", endpoint)

                .header("Authorization", "Bearer " + TOKEN)
                .header("Content-Type", "application/json")
                .body(paylaod)

                .when()
                .post("/{module}/{version}/{resource}");
    }

    public static Response postRequestWithBuilder(String endpoint, Object paylaod) {

        return given().log().all()
                .baseUri(BASE_URL)
                .pathParams(
                        "module", "public",
                        "version", "v2",
                        "resource", endpoint)

                .header("Authorization", "Bearer " + TOKEN)
                .header("Content-Type", "application/json")
                .body(paylaod)

                .when()
                .post("/{module}/{version}/{resource}");
    }

    public static Response postRequestWithRequestSpecs(String endpoint, Object paylaod) {

        return given()
                .spec(RequestSpec.getRequestSpecs())
                .pathParam("resource", endpoint)
                .body(paylaod)
                .post("/{module}/{version}/{resource}");
    }
}
