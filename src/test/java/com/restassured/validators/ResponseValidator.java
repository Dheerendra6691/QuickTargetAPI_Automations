package com.restassured.validators;

import org.testng.Assert;

import com.restassured.wrappers.ResponseWrapper;

import io.restassured.response.Response;

/* 
Validates business fields, status codes, and response content. 
Ensures functional correctness. 
Helps verify API contract and behavior.
*/
public final class ResponseValidator {

    private ResponseValidator() {
    }

    public static void validateSuccess(ResponseWrapper response) {
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.getBody().isBlank());
    }

    public static void validateStatus(Response response, int expectedStatus) {
        Assert.assertEquals(response.statusCode(), expectedStatus);
    }
}