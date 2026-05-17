package com.restassured.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/* 
Executes SQL queries and fetches database results. 
Supports validation of inserted or updated records. 
Helps perform end-to-end API and database verification.
*/
public final class QueryExecutor {

    private QueryExecutor() {
    }

    public static ResultSet execute(
            String query) {

        try {

            Connection connection = DatabaseManager.getConnection();

            Statement statement = connection.createStatement();

            return statement.executeQuery(
                    query);

        } catch (Exception e) {

            throw new RuntimeException(
                    e);
        }
    }
}