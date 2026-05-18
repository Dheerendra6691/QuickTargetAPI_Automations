package com.restassured.workflows;

import com.restassured.client.AuthService;
import com.restassured.models.request.TokenRequest;

public class AuthWorkflow {

    private final AuthService authService = new AuthService();

    public String generateToken(TokenRequest request) {

        return authService.generateToken(request.getUsername(), request.getPassword());
    }
}