package com.restassured.fixtures;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.restassured.auth.TokenManager;
import com.restassured.filters.LoggingFilter;

import io.restassured.RestAssured;

/*
Base test setup class for all API tests.
Initializes configurations, contexts, and dependencies.
Helps standardize test execution lifecycle.
*/
public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void configureFramework() {

        RestAssured.filters(new LoggingFilter());
    }

    @BeforeClass(alwaysRun = true)
    public void setup() {

        TokenManager.getToken();
    }
}
