package com.restassured.fixtures;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.restassured.client.AuthService;
import com.restassured.client.BaseService;
import com.restassured.filters.LoggingFilter;
import com.restassured.listeners.TestListener;

import io.restassured.RestAssured;
/*
Base test setup class for all API tests.
Initializes configurations, contexts, and dependencies.
Helps standardize test execution lifecycle.
*/
@Listeners({ TestListener.class, io.qameta.allure.testng.AllureTestNg.class })
public class BaseTest {

    protected AuthService authService;
    protected BaseService baseService;

    @BeforeSuite(alwaysRun = true)
    public void configureFramework() {

        RestAssured.filters(
                new LoggingFilter());
    }

    @BeforeClass(alwaysRun = true)
    public void setup() {

        authService = new AuthService();

        baseService = new BaseService();

        authService.generateToken(
                "vikas",
                "Vikas@123");
    }
}
