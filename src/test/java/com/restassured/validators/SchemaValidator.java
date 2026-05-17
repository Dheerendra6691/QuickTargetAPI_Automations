package com.restassured.validators;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.response.Response;

/* 
Validates response structure against JSON schema files. 
Ensures contract compatibility. 
Helps detect unexpected API changes early.
*/
public final class SchemaValidator {

        private static final String SCHEMA_PATH = "schema/";

        private SchemaValidator() {
        }

        public static void validate(Response response, String schemaName) {
                response.then().assertThat()
                                .body(matchesJsonSchemaInClasspath(SCHEMA_PATH + schemaName + "-response-schema.json"));
        }
}