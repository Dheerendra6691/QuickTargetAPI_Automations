package com.restassured.validators;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.restassured.db.QueryExecutor;
import com.restassured.models.response.RequestOnDemandResponse;
import com.restassured.utils.FileUtils;

public final class DatabaseValidator {

    private static final String QUERY = FileUtils.readFile(
            "src/test/resources/sql/get_hes_command_request.sql");

    private DatabaseValidator() {
    }

    public static void validateCommand(
            RequestOnDemandResponse response) {

        List<Map<String, Object>> dbResults = QueryExecutor.executeQuery(
                QUERY,
                response.getCommandType());

        boolean recordFound = false;

        for (Map<String, Object> dbData : dbResults) {

            String dbMeterNo = dbData.get("meter_no").toString();

            int dbRequestId = ((Number) dbData.get("request_id")).intValue();

            if (dbMeterNo.equals(response.getMeterNo())
                    && dbRequestId == response.getRequestId()) {

                recordFound = true;

                Assert.assertEquals(
                        ((Number) dbData.get("command_type")).intValue(),
                        response.getCommandType());

                Assert.assertEquals(
                        dbData.get("command_value").toString(),
                        response.getCommandValue());

                Assert.assertEquals(
                        dbData.get("status").toString(),
                        response.getStatus());

                break;
            }
        }

        Assert.assertTrue(
                recordFound,
                "Matching DB record not found");
    }
}