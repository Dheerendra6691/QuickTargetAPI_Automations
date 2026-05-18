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
            // Synchronized in Java is used to make thread-safe code by allowing only one
            // thread at a time to access shared resources, preventing race conditions and
            // data inconsistency.
            synchronized (AuthFactory.class) {

                if (instance == null) {

                    instance = new AuthService();
                }
            }
        }

        return instance;
    }
}
