package com.restassured.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;

/* 
Converts Java objects into JSON payloads. 
Supports request body generation using Jackson library. 
Helps build dynamic and strongly typed API requests.

Utility class for converting Java request objects into
server-readable JSON payloads, which are ultimately sent
as bytes over HTTP. Ensures consistent serialization,
better debugging, framework independence, and reusable
request generation across API automation.
 */

public final class JacksonSerializer {

    private static final ObjectMapper objMapper = new ObjectMapper();

    private JacksonSerializer() {
    }

    public static String serialize(Object object) {

        try {
            return objMapper.writeValueAsString(object);
        } catch (Exception exception) {
            throw new RuntimeException("Serialization failed", exception);
        }
    }
}