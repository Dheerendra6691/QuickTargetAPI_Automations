package com.restassured.config;

/*
Identifies active execution environment dynamically.
Switches configurations based on selected environment.
Helps run the same API suite across multiple deployment stages.
*/
public final class EnvironmentManager {

    private static final String ENV_KEY = "env";
    private static final String DEFAULT_ENV = "qa";

    private EnvironmentManager() {
    }

    public static String getEnvironment() {

        String environment = System.getProperty(ENV_KEY);

        if (isBlank(environment)) {
            environment = System.getenv("ENV");
        }

        if (isBlank(environment)) {
            environment = DEFAULT_ENV;
        }

        return environment
                .trim()
                .toLowerCase();
    }

    public static String getConfigFile() {
        return "config-" + getEnvironment() + ".properties";
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
