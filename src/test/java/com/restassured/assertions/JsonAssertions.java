package com.restassured.assertions;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public final class JsonAssertions {

    private JsonAssertions() {
    }

    public static void assertFieldExists(
            JsonPath jsonPath,
            String field) {

        Assert.assertNotNull(
                jsonPath.get(field),
                "Missing field : " + field);
    }

    public static void assertFieldEquals(
            JsonPath jsonPath,
            String field,
            Object expected) {

        Assert.assertEquals(
                jsonPath.get(field),
                expected);
    }
}