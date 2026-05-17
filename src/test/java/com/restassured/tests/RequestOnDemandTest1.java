package com.restassured.tests;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restassured.assertions.ApiAssertions;
import com.restassured.fixtures.BaseTest;
import com.restassured.models.request.OnDemandRequest;
import com.restassured.models.response.RequestOnDemandResponse;
import com.restassured.workflows.CommandWorkflow;

/* 
Contains actual API test scenarios for endpoint validation. 
Executes requests, assertions, and backend checks. 
Helps verify functional and regression coverage.
*/
public class RequestOnDemandTest1 extends BaseTest {

    private final CommandWorkflow commandWorkflow = new CommandWorkflow();

    @Test(dataProvider = "positiveData", description = "Verify Request On Demand API", groups = { "sanity", "smoke",
            "regression" })
    public void verifyRequestOnDemand(OnDemandRequest request) {

        List<RequestOnDemandResponse> commands = commandWorkflow.executeOnDemand(request);

        ApiAssertions.assertOnDemandResponse(commands);
    }

    @Test(dataProvider = "negativeData", description = "Verify negative Request On Demand API", groups = {
            "regression" })
    public void verifyNegativeRequestOnDemand(OnDemandRequest request, int expectedStatusCode) {

        commandWorkflow.executeNegativeOnDemand(request, expectedStatusCode);
    }

    @DataProvider(name = "positiveData")
    public Object[][] positiveData() {

        return new Object[][] {
                { new OnDemandRequest.Builder().build() },
                { new OnDemandRequest.Builder().meterNo("DL0010003").build() },
                { new OnDemandRequest.Builder().commandType(4).build() },
                { new OnDemandRequest.Builder().commandValue(2).build() },
                { new OnDemandRequest.Builder().commandValue(4).meterNo("DL0010007").build() }
        };
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