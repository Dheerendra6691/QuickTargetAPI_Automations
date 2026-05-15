package com.restassured.client;

import java.beans.Introspector;
import java.util.HashMap;
import java.util.Map;

import com.restassured.specifications.RequestSpecs;

import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class BaseService {

    protected static String authToken;

    public void setToken(String token) {
        authToken = token;
    }

    public Response postForm(String endpoint, Object payload) {
        return RequestSpecs.formUrlEncoded()
                .formParams(toMap(payload))
                .post(endpoint);
    }

    public Response post(String endpoint, Object payload) {
        return RequestSpecs.authorized(authToken)
                .body(payload)
                .post(endpoint);
    }

    public Response put(String endpoint, Object payload) {
        return RequestSpecs.authorized(authToken)
                .body(payload)
                .put(endpoint);
    }

    public Response delete(String endpoint) {
        return RequestSpecs.authorized(authToken)
                .delete(endpoint);
    }

    public Response get(String endpoint, Object payload) {
        return RequestSpecs.authorized(authToken)
                .queryParams(toMap(payload))
                .get(endpoint);
    }

    public Response get(String endpoint, Object payload, ResponseSpecification spec) {
        return RequestSpecs.authorized(authToken)
                .queryParams(toMap(payload))
                .get(endpoint)
                .then()
                .log().ifValidationFails()
                .spec(spec)
                .extract()
                .response();
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

                if (value != null)
                    map.put(property.getName(), value);
            }

            return map;

        } catch (Exception e) {
            throw new RuntimeException("Payload conversion failed", e);
        }
    }
}