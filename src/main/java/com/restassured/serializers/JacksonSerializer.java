package com.restassured.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;

/* 
Converts Java objects into JSON payloads. 
Supports request body generation using Jackson library. 
Helps build dynamic and strongly typed API requests.
*/

public final class JacksonSerializer {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JacksonSerializer() {
    }

    public static String serialize(Object object) {

        try {
            return MAPPER.writeValueAsString(object);
        } catch (Exception exception) {
            throw new RuntimeException(
                    "Serialization failed",
                    exception);
        }
    }
}