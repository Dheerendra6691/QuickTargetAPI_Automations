package com.restassured.utils;

import java.io.FileInputStream;
import java.util.Properties;

public final class PropertyUtils {

    private static final Properties properties = new Properties();

    static {

        try {

            String env = System.getProperty(
                    "env",
                    "local");

            String path = "config/env/" +
                    env +
                    ".properties";

            FileInputStream file = new FileInputStream(
                    path);

            properties.load(file);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Config not loaded");
        }
    }

    private PropertyUtils() {

    }

    public static String get(
            String key) {

        return properties.getProperty(
                key);
    }
}