package com.restassured.config;

import com.restassured.utils.PropertyUtils;

public final class ConfigManager {

    private ConfigManager() {
    }

    public static String getBaseUrl() {

        return PropertyUtils.get("base.url");
    }
}