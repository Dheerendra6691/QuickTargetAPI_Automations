package com.restassured.workflows;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import com.restassured.client.BaseService;
import com.restassured.config.ConfigManager;
import com.restassured.constants.ApiEndpoints;
import com.restassured.context.ScenarioContext;
import com.restassured.models.request.OnDemandRequest;
import com.restassured.models.response.RequestOnDemandResponse;
import com.restassured.specifications.ResponseSpecs;
import com.restassured.validators.DatabaseValidator;
import com.restassured.validators.SchemaValidator;

import io.restassured.response.Response;

/* 
Executes complete business workflows involving multiple API calls. 
Handles token generation, request chaining, and validations. 
Helps automate real end-to-end business scenarios.
*/
public class CommandWorkflow {

        private final BaseService service = new BaseService();

        public List<RequestOnDemandResponse> executeOnDemand(
                        OnDemandRequest request) {

                Response response = service.get(
                                ApiEndpoints.REQUEST_ON_DEMAND,
                                request,
                                ResponseSpecs.success());

                Assert.assertNotNull(
                                response,
                                "API response should not be null");

                SchemaValidator.validate(
                                response,
                                "ondemand");

                List<RequestOnDemandResponse> commands = Arrays.asList(
                                response.as(RequestOnDemandResponse[].class));

                Assert.assertFalse(
                                commands.isEmpty(),
                                "API response should not be empty");

                for (RequestOnDemandResponse command : commands) {

                        ScenarioContext.set("requestId", command.getRequestId());

                        ScenarioContext.set("meterNo", command.getMeterNo());

                        ScenarioContext.set("status", command.getStatus());

                        if (ConfigManager.getBoolean("db.validation.enabled")) {

                                DatabaseValidator.validateCommand();
                        }
                }

                return commands;
        }

        public void executeNegativeOnDemand(
                        OnDemandRequest request,
                        int expectedStatusCode) {

                Response response = service.get(
                                ApiEndpoints.REQUEST_ON_DEMAND,
                                request,
                                ResponseSpecs.status(expectedStatusCode));

                Assert.assertNotNull(response);

                if (expectedStatusCode == 404) {

                        Assert.assertEquals(response.jsonPath().getString("detail"), "Not found");
                }

                if (expectedStatusCode == 200) {

                        Assert.assertFalse(response.jsonPath().getList("$").isEmpty());
                }
        }
}