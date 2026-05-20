package com.restassured.reporting;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    private static final ThreadLocal<ExtentTest> METHOD_TEST = new ThreadLocal<>();
    private static final Map<String, ExtentTest> CLASS_TEST_MAP = new HashMap<>();

    private ExtentTestManager() {
    }

    /**
     * Dynamically sets up or reads the parent API Class node,
     * then appends the running test method as a child node beneath it.
     */
    public static synchronized void startTest(String className, String methodName) {
        // Fetch or create parent group node for the test API class file
        ExtentTest classParentNode = CLASS_TEST_MAP.get(className);
        if (classParentNode == null) {
            classParentNode = ExtentManager.getInstance().createTest(className);
            CLASS_TEST_MAP.put(className, classParentNode);
        }

        // Create actual test method execution node nested underneath it
        ExtentTest methodNode = classParentNode.createNode(methodName);
        METHOD_TEST.set(methodNode);
    }

    public static ExtentTest getTest() {
        return METHOD_TEST.get();
    }

    public static void unload() {
        METHOD_TEST.remove();
    }
}