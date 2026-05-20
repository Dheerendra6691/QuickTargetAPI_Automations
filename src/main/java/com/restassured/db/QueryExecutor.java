package com.restassured.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
Enterprise-grade reusable SQL executor.
*/
public final class QueryExecutor {

    private static final Logger LOGGER = LogManager.getLogger(QueryExecutor.class);

    private QueryExecutor() {
    }

    public static List<Map<String, Object>> executeQuery(
            String query,
            Object... parameters) {

        List<Map<String, Object>> results = new ArrayList<>();

        LOGGER.info("Executing Query : {}", query);

        try (Connection connection = DatabaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                ResultSetMetaData metaData = resultSet.getMetaData();

                int columnCount = metaData.getColumnCount();

                while (resultSet.next()) {

                    Map<String, Object> row = new HashMap<>();

                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnLabel(i), resultSet.getObject(i));
                    }

                    results.add(row);
                }
            }

            LOGGER.info("Rows fetched : {}", results.size());

        } catch (Exception e) {

            LOGGER.error("Database query execution failed", e);

            throw new RuntimeException(e);
        }

        return results;
    }
}