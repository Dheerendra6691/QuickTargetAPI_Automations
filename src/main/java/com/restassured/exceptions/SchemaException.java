package com.restassured.exceptions;
/* 
Custom exception for schema validation failures. 
Triggered when API response mismatches expected schema. 
Helps ensure response contract compliance.
*/

public class SchemaException extends RuntimeException {

    public SchemaException(String message) {

        super(message);
    }

    public SchemaException(String message, Throwable cause) {

        super(message, cause);
    }
}