package com.restassured.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.restassured.assertions.ApiAssertions;
import com.restassured.dataproviders.OnDemandDataProvider;
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

    @Test(dataProvider = "positiveOnDemandData", dataProviderClass = OnDemandDataProvider.class,

            description = "Verify Request On Demand API", groups = { "sanity", "smoke", "regression" })

    public void verifyRequestOnDemand(OnDemandRequest request) {

        List<RequestOnDemandResponse> commands = commandWorkflow.executeOnDemand(request);

        ApiAssertions.assertOnDemandResponse(commands);
    }

    @Test(dataProvider = "negativeOnDemandData", dataProviderClass = OnDemandDataProvider.class, description = "Verify negative Request On Demand API", groups = {
            "regression" })
    public void verifyNegativeRequestOnDemand(OnDemandRequest request, int expectedStatusCode) {

        commandWorkflow.executeNegativeOnDemand(request, expectedStatusCode);
    }

}