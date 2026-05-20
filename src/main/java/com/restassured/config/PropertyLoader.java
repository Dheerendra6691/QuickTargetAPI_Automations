package com.restassured.config;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
Reads property files and converts them into usable key-value configurations.
Supports externalized test settings.
Helps maintain flexible and easily configurable automation frameworks.
*/
public final class PropertyLoader {

    private static final Logger LOGGER = LogManager.getLogger(PropertyLoader.class);
    private static final String CONFIG_DIRECTORY = "dbconfig/";

    private PropertyLoader() {
    }

    public static Properties load(String fileName) {

        String fullPath = CONFIG_DIRECTORY + fileName;
        Properties properties = new Properties();

        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(fullPath)) {

            if (Objects.isNull(inputStream)) {
                throw new RuntimeException("Configuration file not found : " + fullPath);
            }

            properties.load(inputStream);

            LOGGER.info("Configuration loaded successfully : {}", fullPath);

            return properties;

        } catch (Exception exception) {

            LOGGER.error("Unable to load config file : {}", fullPath, exception);

            throw new RuntimeException(
                    "Unable to load config file : " + fullPath,
                    exception);
        }
    }
}