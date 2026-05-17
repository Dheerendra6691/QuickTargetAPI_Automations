package com.restassured.config;

import java.util.Properties;
/*
Loads framework configuration values from property files.
Provides environment-specific settings like URLs, credentials, and timeouts.
Helps execute tests seamlessly across QA, UAT, and production environments.
*/
public final class ConfigManager {

    private static volatile Properties properties;

    private ConfigManager() {
    }

    private static Properties getProperties() {

        if (properties == null) {

            synchronized (ConfigManager.class) {

                if (properties == null) {

                    String fileName = EnvironmentManager.getConfigFile();

                    properties = PropertyLoader.load(fileName);

                    overrideWithSystemProperties();
                    overrideWithEnvironmentVariables();
                }
            }
        }

        return properties;
    }

    private static void overrideWithSystemProperties() {

        Properties systemProperties = System.getProperties();

        for (String key : systemProperties.stringPropertyNames()) {

            if (systemProperties.getProperty(key) != null) {

                properties.setProperty(
                        key,
                        systemProperties.getProperty(key));
            }
        }
    }

    private static void overrideWithEnvironmentVariables() {

        System.getenv()
                .forEach((key, value) -> properties.setProperty(
                        key.toLowerCase(),
                        value));
    }

    public static String get(String key) {

        String value = getProperties().getProperty(key);

        if (value == null) {

            throw new RuntimeException(
                    "Missing config key : " + key);
        }

        return value.trim();
    }

    public static String get(String key,
            String defaultValue) {

        return getProperties()
                .getProperty(key, defaultValue)
                .trim();
    }

    public static int getInt(String key) {

        return Integer.parseInt(
                get(key));
    }

    public static long getLong(String key) {

        return Long.parseLong(
                get(key));
    }

    public static double getDouble(String key) {

        return Double.parseDouble(
                get(key));
    }

    public static boolean getBoolean(String key) {

        return Boolean.parseBoolean(
                get(key));
    }

    public static void reload() {

        synchronized (ConfigManager.class) {
            properties = null;
        }
    }
}
