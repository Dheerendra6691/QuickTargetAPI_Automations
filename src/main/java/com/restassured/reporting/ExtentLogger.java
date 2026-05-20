package com.restassured.reporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentLogger {

    private ExtentLogger() {
    }

    private static ExtentTest getExtentTest() {

        return ExtentTestManager.getTest();
    }

    public static void info(String message) {

        ExtentTest test = getExtentTest();

        if (test != null) {
            test.log(Status.INFO, message);
        }
    }

    public static void pass(String message) {

        ExtentTest test = getExtentTest();

        if (test != null) {
            test.log(Status.PASS, message);
        }
    }

    public static void fail(String message) {

        ExtentTest test = getExtentTest();

        if (test != null) {
            test.log(Status.FAIL, message);
        }
    }

    public static void fail(Throwable throwable) {

        ExtentTest test = getExtentTest();

        if (test != null) {
            test.fail(throwable);
        }
    }

    public static void warning(String message) {

        ExtentTest test = getExtentTest();

        if (test != null) {
            test.log(Status.WARNING, message);
        }
    }
}