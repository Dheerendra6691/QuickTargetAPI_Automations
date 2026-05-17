package com.restassured.db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.restassured.config.ConfigManager;

/* 
Creates and manages database connections for backend validations. 
Supports execution of SQL queries. 
Helps verify API data persistence and backend integrity.
*/
public final class DatabaseManager {

    private DatabaseManager() {
    }

    public static Connection getConnection() {

        try {

            return DriverManager.getConnection(
                    ConfigManager.get("db.url"),
                    ConfigManager.get("db.user"),
                    ConfigManager.get("db.password"));

        } catch (Exception e) {

            throw new RuntimeException(
                    e);
        }
    }
}