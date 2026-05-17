package com.restassured.config;

import java.io.InputStream;
import java.util.Properties;

/* 
Reads property files and converts them into usable key-value configurations. 
Supports externalized test settings. 
Helps maintain flexible and easily configurable automation frameworks.
*/
public final class PropertyLoader {

    private PropertyLoader() {
    }

    public static Properties load(String fileName) {

        try {

            InputStream inputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("config/" + fileName);

            if (inputStream == null) {

                throw new RuntimeException("Configuration file not found : " + fileName);
            }

            Properties properties = new Properties();

            properties.load(inputStream);

            return properties;

        } catch (Exception exception) {

            throw new RuntimeException("Unable to load config file : " + fileName, exception);
        }
    }
}