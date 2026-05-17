package com.restassured.exceptions;
/* 
Custom exception for retry execution failures. 
Triggered when maximum retry attempts are exhausted. 
Helps track unstable API behavior during automation.
*/

public class RetryException extends RuntimeException {

    public RetryException(
            String message) {

        super(message);
    }

    public RetryException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}