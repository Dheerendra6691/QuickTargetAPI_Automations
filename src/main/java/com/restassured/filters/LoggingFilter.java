package com.restassured.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

/*
 * Logs request and response details automatically.
 * Captures headers, payloads, and status codes.
 * Helps debugging failed API executions efficiently.
 */
public class LoggingFilter implements Filter {

        private static final Logger LOGGER = LogManager.getLogger(LoggingFilter.class);

        @Override
        public Response filter(FilterableRequestSpecification requestSpec,
                        FilterableResponseSpecification responseSpec,
                        FilterContext context) {

                logRequest(requestSpec);

                Response response = context.next(requestSpec, responseSpec);

                logResponse(response);

                return response;
        }

        private void logRequest(FilterableRequestSpecification requestSpec) {

                LOGGER.info("Method : {}", requestSpec.getMethod());
                LOGGER.info("URI : {}", requestSpec.getURI());
                LOGGER.info("Headers : {}", requestSpec.getHeaders());

                if (!requestSpec.getQueryParams().isEmpty()) {
                        LOGGER.info("Query Params : {}", requestSpec.getQueryParams());
                }

                if (!requestSpec.getFormParams().isEmpty()) {
                        LOGGER.info("Form Params : {}", requestSpec.getFormParams());
                }

                Object body = requestSpec.getBody();

                if (body != null) {
                        LOGGER.info("Body : {}", body);
                }
        }

        private void logResponse(Response response) {

                LOGGER.info("Status Code : {}", response.statusCode());
                LOGGER.info("Response : {}", response.asPrettyString());
        }
}