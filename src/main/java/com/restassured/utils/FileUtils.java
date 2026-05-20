package com.restassured.utils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
Handles file reading, writing, and resource loading operations.
Supports payloads and test data files.
Helps externalize API test inputs.
*/
/*
Reads SQL files, payloads, and reusable test resources.
*/
public final class FileUtils {

    private static final Logger LOGGER = LogManager.getLogger(FileUtils.class);

    private FileUtils() {
    }

    public static String readFile(String filePath) {

        try {

            LOGGER.info("Reading File : {}", filePath);

            return Files.readString(
                    Paths.get(filePath),
                    StandardCharsets.UTF_8);

        } catch (Exception e) {

            LOGGER.error("Failed to read file : {}", filePath, e);

            throw new RuntimeException(e);
        }
    }
}