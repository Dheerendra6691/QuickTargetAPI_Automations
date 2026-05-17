package com.restassured.exceptions;
/* 
Custom exception for API execution failures. 
Provides meaningful error handling and debugging support. 
Helps quickly identify request or response-related issues.
*/

public class ApiException extends RuntimeException {

    private final int statusCode;

    public ApiException(
            String message,
            int statusCode) {

        super(message);

        this.statusCode = statusCode;
    }

    public ApiException(
            String message,
            Throwable cause) {

        super(message, cause);

        this.statusCode = -1;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
