package com.restassured.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
/*
Executes HTTP requests using RestAssured methods.
Supports GET, POST, PUT, DELETE operations.
Helps centralize API execution logic and simplifies test implementation.
*/
public final class ApiExecutor {

        private static final Logger LOGGER = LogManager.getLogger(ApiExecutor.class);

        private ApiExecutor() {
        }

        public static Response get(RequestSpecification requestSpec, String endpoint,
                        ResponseSpecification responseSpec) {

                LOGGER.info(
                                "Executing GET : {}",
                                endpoint);

                return requestSpec
                                .when()
                                .get(endpoint)
                                .then()
                                .spec(responseSpec)
                                .extract()
                                .response();
        }

        public static Response post(
                        RequestSpecification requestSpec,
                        String endpoint,
                        Object body,
                        ResponseSpecification responseSpec) {

                LOGGER.info(
                                "Executing POST : {}",
                                endpoint);

                return requestSpec
                                .body(body)
                                .when()
                                .post(endpoint)
                                .then()
                                .spec(responseSpec)
                                .extract()
                                .response();
        }
}
