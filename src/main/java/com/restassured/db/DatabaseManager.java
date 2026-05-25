package com.restassured.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.restassured.config.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/*
Enterprise PostgreSQL Connection Manager.
*/
public final class DatabaseManager {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseManager.class);

    private static final HikariDataSource DATA_SOURCE;

    static {

        try {
            // HikariCP HikariConfig is a configuration class. Used for DB settings
            HikariConfig config = new HikariConfig();
            // JDBC URL used for connecting to PostgreSQL database
            config.setJdbcUrl(ConfigManager.get("db.url"));
            // Database username used for authentication
            config.setUsername(ConfigManager.get("db.user"));
            config.setPassword(ConfigManager.get("db.password"));
            // JDBC driver class name used for establishing database connection
            config.setDriverClassName(ConfigManager.get("db.driver"));
            // Pool can maintain maximum configured DB connections ,10 means 10 connections
            config.setMaximumPoolSize(ConfigManager.getInt("db.pool.size"));
            // Minimum idle DB connections maintained in pool
            config.setMinimumIdle(ConfigManager.getInt("db.pool.idle"));
            // Time after which unused idle connections can be removed
            config.setIdleTimeout(ConfigManager.getLong("db.pool.timeout"));
            // Maximum wait time to get DB connection from pool
            config.setConnectionTimeout(20000);
            // Maximum lifetime of DB connection before recreation
            config.setMaxLifetime(1800000);

            // HikariDataSource creates and manages the actual connection pool.
            // Creates DB Connection Pool and Keeps Connections Ready
            DATA_SOURCE = new HikariDataSource(config);

            LOGGER.info("PostgreSQL Pool Initialized Successfully");

        } catch (Exception e) {

            LOGGER.error("Failed to initialize DB Pool", e);

            throw new RuntimeException(e);
        }
    }

    private DatabaseManager() {
    }

    public static Connection getConnection() {

        try {
            // Get DB connection from Hikari pool
            return DATA_SOURCE.getConnection();

        } catch (SQLException e) {

            LOGGER.error("Unable to get DB connection", e.getMessage());

            throw new RuntimeException(e);
        }
    }
}