package com.restassured.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restassured.client.BaseService;
import com.restassured.constants.ApiEndpoints;
import com.restassured.fixtures.BaseTest;
import com.restassured.models.request.OnDemandRequest;
import com.restassured.models.response.RequestOnDemandResponse;
import com.restassured.specifications.ResponseSpecs;
import com.restassured.validators.SchemaValidator;

import io.restassured.response.Response;

public class RequestOnDemandTest extends BaseTest {

        @Test(dataProvider = "positiveData", description = "Verify Request On Demand API", groups = { "sanity", "smoke",
                        "regression" })
        public void verifyRequestOnDemand(OnDemandRequest request) {

                Response response = new BaseService()
                                .get(ApiEndpoints.REQUEST_ON_DEMAND, request, ResponseSpecs.success());

                assertNotNull(response, "Response is null");

                SchemaValidator.validate(response, "ondemand");

                List<RequestOnDemandResponse> rodCommands = java.util.Arrays
                                .asList(response.as(RequestOnDemandResponse[].class));

                Assert.assertFalse(rodCommands.isEmpty());

                for (RequestOnDemandResponse command : rodCommands) {

                        Assert.assertEquals(command.getStatus(), "SUCCESS");
                        Assert.assertTrue(
                                        command.getMeterNo().startsWith("DL") || command.getMeterNo().startsWith("AS"));
                        Assert.assertTrue(command.getRequestId() > 0);
                        Assert.assertTrue(command.getCommandType() >= 1 && command.getCommandType() <= 6);
                        Assert.assertNotNull(command.getCreatedAt());
                }

        }

        @Test(dataProvider = "negativeData", groups = { "regression" }, enabled = true)
        public void verifyNegativeRequestOnDemand(OnDemandRequest request, int expectedStatusCode) {

                Response response = new BaseService()
                                .get(ApiEndpoints.REQUEST_ON_DEMAND, request, ResponseSpecs.status(expectedStatusCode));

                assertNotNull(response, "Response is null");

                if (expectedStatusCode == 200)
                        assertFalse(response.jsonPath().getList("$").isEmpty(), "Response should not be empty");

                if (expectedStatusCode == 404)
                        Assert.assertEquals(response.jsonPath().getString("detail"), "Not found");
        }

        @DataProvider(name = "positiveData")
        public Object[][] positiveData() {
                return new Object[][] {
                                // { new
                                // OnDemandRequest.Builder().requestId(20001).meterNo("DL0010001").commandType(2)
                                // .commandValue(7)
                                // .build() },
                                { OnDemandRequest.builder().build() },
                                // { OnDemandRequest.builder().requestId(20003).build() },
                                { OnDemandRequest.builder().meterNo("DL0010003").build() },
                                { OnDemandRequest.builder().commandType(4).build() },
                                { OnDemandRequest.builder().commandValue(2).build() },
                                { OnDemandRequest.builder().commandValue(4).meterNo("DL0010007").build()
                                }
                };
        }

        @DataProvider(name = "negativeData")
        public Object[][] negativeData() {
                return new Object[][] {
                                { OnDemandRequest.builder().meterNo("979").build(), 200 },
                                { OnDemandRequest.builder().commandType(8768).build(), 200 },
                                { OnDemandRequest.builder().commandValue(9696).build(), 200 },
                                { OnDemandRequest.builder().requestId(9687).build(), 404 },
                                { OnDemandRequest.builder().requestId(-3).build(), 404 }
                };
        }
}