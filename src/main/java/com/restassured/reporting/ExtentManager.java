package com.restassured.reporting;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extentReports;
    private static String reportPath;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getInstance() {
        if (extentReports == null) {
            initializeReport();
        }
        return extentReports;
    }

    private static void initializeReport() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        reportPath = System.getProperty("user.dir")
                + File.separator + "test-output"
                + File.separator + "extent-report"
                + File.separator + "ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config().setReportName("API Automation Execution Report");
        sparkReporter.config().setDocumentTitle("RestAssured Automation Report");
        sparkReporter.config().setTimelineEnabled(true);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        addSystemInformation();
    }

    private static void addSystemInformation() {
        extentReports.setSystemInfo("Framework", "RestAssured + TestNG");
        extentReports.setSystemInfo("Project", "QuickTarget API Automation");
        extentReports.setSystemInfo("Environment", System.getProperty("env", "QA"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
        extentReports.setSystemInfo("User", System.getProperty("user.name"));
        extentReports.setSystemInfo("Execution Time", new Date().toString());
        extentReports.setSystemInfo("Available Processors", String.valueOf(Runtime.getRuntime().availableProcessors()));
    }

    public static void openReport() {
        try {
            if (reportPath == null) return;

            File reportFile = new File(reportPath);
            if (reportFile.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(reportFile.toURI());
            } else if (!Desktop.isDesktopSupported()) {
                System.out.println("[INFO] Headless Execution Environment. Report located at: " + reportPath);
            }
        } catch (Exception exception) {
            System.err.println("[ERROR] Failed to auto-open Extent Report.");
            exception.printStackTrace();
        }
    }
}