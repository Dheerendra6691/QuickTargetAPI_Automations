package com.restassured.deserializers;

import com.fasterxml.jackson.databind.ObjectMapper;

/* 
Converts JSON API responses into Java objects. 
Supports response parsing and field extraction. 
Helps validate structured API responses efficiently.
*/

public final class JacksonDeserializer {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JacksonDeserializer() {
    }

    public static <T> T deserialize(String json, Class<T> clazz) {

        try {

            return MAPPER.readValue(json, clazz);

        } catch (Exception exception) {

            throw new RuntimeException(
                    "Deserialization failed",
                    exception);
        }
    }
}