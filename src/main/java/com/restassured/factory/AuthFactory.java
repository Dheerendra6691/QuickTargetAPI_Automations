package com.restassured.factory;

import com.restassured.client.AuthService;

/* 
Creates authentication service instances dynamically. 
Implements factory design pattern for auth handling. 
Helps support multiple authentication mechanisms easily.
*/

public final class AuthFactory {

    private static volatile AuthService instance;

    private AuthFactory() {
    }

    public static AuthService getInstance() {

        if (instance == null) {

            synchronized (AuthFactory.class) {

                if (instance == null) {

                    instance = new AuthService();
                }
            }
        }

        return instance;
    }
}
