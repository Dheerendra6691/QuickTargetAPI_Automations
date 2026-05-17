package com.restassured.wrappers;

import io.restassured.response.Response;

/* 
Wraps underlying HTTP client response into framework-owned abstraction. 
Prevents direct dependency on RestAssured across workflows, validators, and assertions. 
Helps future migration to other clients like WebClient or OkHttp without changing test cases.
*/
public final class ResponseWrapper {

    private final Response response;

    public ResponseWrapper(Response response) {

        this.response = response;
    }

    public int getStatusCode() {

        return response.statusCode();
    }

    public String getBody() {

        return response.asString();
    }

    public <T> T as(Class<T> clazz) {

        return response.as(clazz);
    }

    public Response raw() {

        return response;
    }
}