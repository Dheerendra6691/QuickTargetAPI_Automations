package com.restassured.validators;

import java.sql.ResultSet;

import org.testng.Assert;

import com.restassured.context.ScenarioContext;
import com.restassured.db.QueryExecutor;

/* 
Validates API response against backend database records. 
Ensures data consistency after API execution. 
Helps perform end-to-end verification.
*/
public final class DatabaseValidator {

    private DatabaseValidator() {
    }

    public static void validateCommand() {
        try {
            Integer requestId = ScenarioContext.get("requestId");

            Assert.assertNotNull(requestId, "Scenario requestId should not be null");

            String query = "select request_id from commands where request_id = " + requestId;

            ResultSet resultSet = QueryExecutor.execute(query);

            Assert.assertTrue(resultSet.next(), "Command not found in database");

            Assert.assertEquals(resultSet.getInt("request_id"),
                    requestId.intValue(),
                    "Database requestId mismatch");

        } catch (Exception exception) {
            throw new RuntimeException("Database validation failed", exception);
        }
    }
}