package com.restassured.auth;

import com.restassured.client.AuthService;
import com.restassured.factory.AuthFactory;

public final class TokenManager {

    private static volatile String token;

    private TokenManager() {
    }

    public static String getToken() {

        if (token == null) {

            synchronized (TokenManager.class) {

                if (token == null) {

                    AuthService authService = AuthFactory.getInstance();

                    token = authService.generateToken(
                            "vikas",
                            "Vikas@123");
                }
            }
        }

        return token;
    }

    public static synchronized void invalidate() {

        token = null;
    }
}