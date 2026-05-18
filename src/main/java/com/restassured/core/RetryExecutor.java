package com.restassured.core;

import java.util.Objects;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* 
Retries failed API requests based on configured retry policy. 
Handles transient failures like timeout or server issues. 
Helps improve test stability in unstable environments.
Retries failed operations like API calls, token generation, or DB actions 
with configurable attempts and wait time, improving framework stability 
against temporary failures.
*/
public final class RetryExecutor {

        private static final Logger LOGGER = LogManager.getLogger(
                        RetryExecutor.class);

        private static final long DEFAULT_WAIT_TIME = 2000;

        private RetryExecutor() {
        }

        public static <T> T execute(
                        String operationName,
                        Supplier<T> action,
                        int retryCount) {

                return execute(
                                operationName,
                                action,
                                retryCount,
                                DEFAULT_WAIT_TIME);
        }

        public static <T> T execute(
                        String operationName,
                        Supplier<T> action,
                        int retryCount,
                        long waitTimeInMillis) {

                validate(
                                operationName,
                                action,
                                retryCount);

                Exception lastException = null;

                for (int attempt = 1; attempt <= retryCount; attempt++) {

                        long startTime = System.currentTimeMillis();

                        try {

                                LOGGER.info(
                                                "Executing operation : {} | Attempt : {}/{}",
                                                operationName,
                                                attempt,
                                                retryCount);

                                T result = action.get();

                                LOGGER.info(
                                                "Operation successful : {} | Attempt : {} | Duration : {} ms",
                                                operationName,
                                                attempt,
                                                System.currentTimeMillis() - startTime);

                                return result;

                        } catch (Exception exception) {

                                lastException = exception;

                                LOGGER.warn(
                                                "Operation failed : {} | Attempt : {}/{} | Duration : {} ms | Reason : {}",
                                                operationName,
                                                attempt,
                                                retryCount,
                                                System.currentTimeMillis() - startTime,
                                                exception.getMessage());

                                if (attempt < retryCount) {

                                        sleep(
                                                        waitTimeInMillis);
                                }
                        }
                }

                LOGGER.error(
                                "Retry exhausted : {} | Total attempts : {}",
                                operationName,
                                retryCount,
                                lastException);

                throw new RuntimeException(
                                "Retry exhausted for operation : " + operationName,
                                lastException);
        }

        private static void validate(
                        String operationName,
                        Supplier<?> action,
                        int retryCount) {

                Objects.requireNonNull(
                                operationName,
                                "Operation name cannot be null");

                Objects.requireNonNull(
                                action,
                                "Retry action cannot be null");

                if (retryCount <= 0) {

                        throw new IllegalArgumentException(
                                        "Retry count must be greater than zero");
                }
        }

        private static void sleep(
                        long waitTimeInMillis) {

                try {

                        Thread.sleep(
                                        waitTimeInMillis);

                } catch (InterruptedException exception) {

                        Thread.currentThread()
                                        .interrupt();

                        throw new RuntimeException(
                                        "Retry execution interrupted",
                                        exception);
                }
        }
}