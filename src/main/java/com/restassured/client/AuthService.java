package com.restassured.client;

import com.restassured.constants.ApiEndpoints;
import com.restassured.models.request.TokenRequest;

import io.restassured.response.Response;
/*
Handles authentication-related API calls such as token generation.
Provides authenticated access for secured endpoints.
Helps centralize auth logic and simplifies token reuse across test cases.
*/
public class AuthService extends BaseService {

    public String generateToken(String username, String password) {

        TokenRequest request = new TokenRequest.Builder()
                .username(username)
                .password(password)
                .build();

        Response response = postForm(ApiEndpoints.TOKEN, request);

        response.prettyPrint();

        if (response.statusCode() != 200) {
            throw new RuntimeException(response.asPrettyString());
        }

        String token = response.jsonPath().getString("access_token");

        setToken(token);

        return token;
    }
}
