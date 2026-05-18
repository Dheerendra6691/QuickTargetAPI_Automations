package com.restassured.assertions;

import java.util.List;

import org.testng.Assert;

import com.restassured.models.response.RequestOnDemandResponse;

/*
Centralized assertion layer for API response validation.
Validates dynamic response fields safely.
Helps support parallel execution and environment-independent testing.
*/
public final class ApiAssertions {

        private ApiAssertions() {
        }

        public static void assertOnDemandResponse(List<RequestOnDemandResponse> responses) {

                Assert.assertNotNull(responses, "Response list is null");

                Assert.assertFalse(responses.isEmpty(), "Response list is empty");

                for (RequestOnDemandResponse response : responses) {

                        Assert.assertNotNull(response, "Response object is null");

                        Assert.assertTrue(response.getRequestId() > 0,
                                        "Invalid request id : " + response.getRequestId());

                        Assert.assertNotNull(response.getMeterNo(), "Meter number is null");

                        Assert.assertNotNull(response.getStatus(), "Status is null");

                        Assert.assertTrue(response.getCommandType() > 0, "Invalid command type");
                }
        }
}