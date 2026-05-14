package com.restassured.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restassured.client.BaseService;
import com.restassured.constants.ApiEndpoints;
import com.restassured.fixtures.BaseTest;
import com.restassured.models.request.OnDemandRequest;
import com.restassured.validators.SchemaValidator;

import io.restassured.response.Response;

public class RequestOnDemandTest extends BaseTest {

    @Test(dataProvider = "positiveData", description = "Verify Request On Demand API")
    public void verifyRequestOnDemand(OnDemandRequest request) {

        Response response = new BaseService().get(ApiEndpoints.REQUEST_ON_DEMAND, request);

        response.prettyPrint();

        Assert.assertNotNull(response, "Response is null");
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code");

        SchemaValidator.validate(response, "ondemand");
    }

    @DataProvider(name = "positiveData")
    public Object[][] positiveData() {

        return new Object[][] {
                { new OnDemandRequest.Builder().requestId(20001).meterNo("DL0010003").commandType(4).commandValue(2)
                        .build() },
                { new OnDemandRequest.Builder().build() },
                { new OnDemandRequest.Builder().requestId(20003).build() },
                { new OnDemandRequest.Builder().meterNo("DL0010003").build() },
                { new OnDemandRequest.Builder().commandType(4).build() },
                { new OnDemandRequest.Builder().commandValue(2).build() },
                { new OnDemandRequest.Builder().requestId(20007).meterNo("DL0010007").build() }
        };
    }

    @Test(dataProvider = "negativeData")
    public void verifyNegativeRequestOnDemand(OnDemandRequest request, int expectedStatusCode) {

        Response response = new BaseService().get(ApiEndpoints.REQUEST_ON_DEMAND, request);

        response.prettyPrint();

        Assert.assertNotNull(response, "Response is null");
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Unexpected status code");

        if (expectedStatusCode == 200) {
            Assert.assertFalse(response.jsonPath().getList("$").isEmpty(), "Response should not be empty");
        }

        if (expectedStatusCode == 404) {
            Assert.assertEquals(response.jsonPath().getString("detail"), "Not found");
        }
    }

    @DataProvider(name = "negativeData")
    public Object[][] negativeData() {

        return new Object[][] {
                { new OnDemandRequest.Builder().meterNo("979").build(), 200 },
                { new OnDemandRequest.Builder().commandType(8768).build(), 200 },
                { new OnDemandRequest.Builder().commandValue(9696).build(), 200 },
                { new OnDemandRequest.Builder().requestId(9687).build(), 404 },
                { new OnDemandRequest.Builder().requestId(-3).build(), 404 }
        };
    }
}