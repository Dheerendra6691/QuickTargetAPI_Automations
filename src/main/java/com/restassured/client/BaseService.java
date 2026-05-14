package com.restassured.client;

import static io.restassured.RestAssured.given;

import java.beans.Introspector;
import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {

    private static final String BASE_URL = "http://127.0.0.1:8000";

    protected static String authToken;

    protected final RequestSpecification requestSpecification;

    public BaseService() {
        requestSpecification = given().baseUri(BASE_URL);
    }

    public void setToken(String token) {
        authToken = token;
    }

    public Response postForm(String endpoint, Object payload) {
        return requestSpecification
                .contentType(ContentType.URLENC)
                .formParams(toMap(payload))
                .post(endpoint);
    }

    public Response post(String endpoint, Object payload) {
        return requestSpecification
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(endpoint);
    }

    public Response get(String endpoint, Object payload) {
        return requestSpecification
                .header("Authorization", "Bearer " + authToken)
                .queryParams(toMap(payload))
                .get(endpoint);
    }

    public Response put(String endpoint, Object payload) {
        return requestSpecification
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .body(payload)
                .put(endpoint);
    }

    public Response delete(String endpoint) {
        return requestSpecification
                .header("Authorization", "Bearer " + authToken)
                .delete(endpoint);
    }

    private Map<String, Object> toMap(Object payload) {

        try {

            Map<String, Object> map = new HashMap<>();

            var properties = Introspector
                    .getBeanInfo(payload.getClass(), Object.class)
                    .getPropertyDescriptors();

            for (var property : properties) {

                Object value = property
                        .getReadMethod()
                        .invoke(payload);

                if (value != null) {
                    map.put(property.getName(), value);
                }
            }

            return map;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}