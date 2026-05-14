package com.restassured.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements
        ITestListener,
        IExecutionListener {

    private static final Logger LOGGER = LogManager.getLogger(
            TestListener.class);

    @Override
    public void onExecutionStart() {

        LOGGER.info(
                "Automation execution started");
    }

    @Override
    public void onExecutionFinish() {

        LOGGER.info(
                "Automation execution completed");
    }

    @Override
    public void onStart(
            ITestContext context) {

        LOGGER.info(
                "Test class started : {}",
                context.getName());
    }

    @Override
    public void onFinish(
            ITestContext context) {

        LOGGER.info(
                "Test class finished : {}",
                context.getName());
    }

    @Override
    public void onTestStart(
            ITestResult result) {

        String testName = result.getTestClass().getRealClass().getSimpleName()
                + "."
                + result.getMethod().getMethodName();

        ThreadContext.put(
                "testName",
                testName);

        LOGGER.info(
                "Test started");
    }

    @Override
    public void onTestSuccess(
            ITestResult result) {

        LOGGER.info(
                "Test passed");

        ThreadContext.clearAll();
    }

    @Override
    public void onTestFailure(
            ITestResult result) {

        LOGGER.error(
                "Test failed",
                result.getThrowable());

        ThreadContext.clearAll();
    }

    @Override
    public void onTestSkipped(
            ITestResult result) {

        LOGGER.warn(
                "Test skipped");

        ThreadContext.clearAll();
    }
}