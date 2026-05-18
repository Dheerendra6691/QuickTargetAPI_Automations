package com.restassured.deserializers;

import com.fasterxml.jackson.databind.ObjectMapper;

/* 
Converts JSON API responses into Java objects. 
Supports response parsing and field extraction. 
Helps validate structured API responses efficiently.
*/

public final class JacksonDeserializer {

    private static final ObjectMapper objMapper = new ObjectMapper();

    private JacksonDeserializer() {
    }

    // <T> declares a generic type parameter. T-User or Employee
    // T = generic return type (User, Employee, etc.)
    // clazz = runtime class info (User.class, Employee.class)
    public static <T> T deserialize(String json, Class<T> clazz) {

        try {

            return objMapper.readValue(json, clazz);

        } catch (Exception exception) {

            throw new RuntimeException(
                    "Deserialization failed",
                    exception);
        }
    }
}