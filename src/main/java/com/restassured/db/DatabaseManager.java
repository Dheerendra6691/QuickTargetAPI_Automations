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

            HikariConfig config = new HikariConfig();

            config.setJdbcUrl(ConfigManager.get("db.url"));
            config.setUsername(ConfigManager.get("db.user"));
            config.setPassword(ConfigManager.get("db.password"));
            config.setDriverClassName(ConfigManager.get("db.driver"));

            config.setMaximumPoolSize(ConfigManager.getInt("db.pool.size"));
            config.setMinimumIdle(ConfigManager.getInt("db.pool.idle"));
            config.setIdleTimeout(ConfigManager.getLong("db.pool.timeout"));

            config.setConnectionTimeout(20000);
            config.setMaxLifetime(1800000);

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

            return DATA_SOURCE.getConnection();

        } catch (SQLException e) {

            LOGGER.error("Unable to get DB connection", e);

            throw new RuntimeException(e);
        }
    }
}