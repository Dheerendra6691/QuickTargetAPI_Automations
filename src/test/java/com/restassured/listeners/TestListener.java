package com.restassured.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.restassured.context.ScenarioContext;
import com.restassured.reporting.ExtentLogger;
import com.restassured.reporting.ExtentManager;
import com.restassured.reporting.ExtentTestManager;

public class TestListener implements ITestListener, IExecutionListener {

        private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

        @Override
        public void onExecutionStart() {
                ExtentManager.getInstance();
                LOGGER.info("Automation execution started");
        }

        @Override
        public void onExecutionFinish() {
                try {
                        ExtentManager.getInstance().flush();
                        LOGGER.info("Automation execution completed successfully.");

                        // Automatically open the report inside your default browser on finish
                        ExtentManager.openReport();
                } catch (Exception e) {
                        LOGGER.error("Failed to flush or open Extent Report engine", e);
                }
        }

        @Override
        public void onStart(ITestContext context) {
                LOGGER.info("Test XML Tag execution started: {}", context.getName());
        }

        @Override
        public void onFinish(ITestContext context) {
                LOGGER.info("Test XML Tag execution finished: {}", context.getName());
        }

        @Override
        public void onTestStart(ITestResult result) {
                // Extracting both pieces so they group by the API Test File structure
                String className = result.getTestClass().getRealClass().getSimpleName();
                String methodName = result.getMethod().getMethodName();
                String logTestName = className + "." + methodName;

                ThreadContext.put("testName", logTestName);

                // Groups methods under their respective Parent API Class nodes dynamically
                ExtentTestManager.startTest(className, methodName);

                LOGGER.info("Test started: {}", logTestName);
                ExtentLogger.info("Test execution started");
        }

        @Override
        public void onTestSuccess(ITestResult result) {
                LOGGER.info("Test passed: {}", result.getMethod().getMethodName());
                ExtentLogger.pass("Test passed");
                clearContexts();
        }

        @Override
        public void onTestFailure(ITestResult result) {
                LOGGER.error("Test failed: {}", result.getMethod().getMethodName(), result.getThrowable());
                ExtentLogger.fail(result.getThrowable());
                clearContexts();
        }

        @Override
        public void onTestSkipped(ITestResult result) {
                LOGGER.warn("Test skipped: {}", result.getMethod().getMethodName());
                ExtentLogger.warning("Test skipped");
                clearContexts();
        }

        private void clearContexts() {
                try {
                        ThreadContext.remove("testName");
                        ScenarioContext.clear();
                        ExtentTestManager.unload();
                } catch (Exception exception) {
                        LOGGER.error("Exception occurred while clearing thread-local contexts", exception);
                }
        }
}